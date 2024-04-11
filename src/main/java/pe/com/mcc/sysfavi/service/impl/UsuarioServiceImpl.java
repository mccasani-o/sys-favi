package pe.com.mcc.sysfavi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.com.mcc.sysfavi.config.JwtService;
import pe.com.mcc.sysfavi.model.entity.Role;
import pe.com.mcc.sysfavi.model.entity.UsuarioEntity;
import pe.com.mcc.sysfavi.model.request.LoginRequest;
import pe.com.mcc.sysfavi.model.request.RegistroRequest;
import pe.com.mcc.sysfavi.model.response.LoginResponse;
import pe.com.mcc.sysfavi.model.response.UsuarioResponse;
import pe.com.mcc.sysfavi.repository.UsuarioRepository;
import pe.com.mcc.sysfavi.service.UsuarioService;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public void registrarUsuario(RegistroRequest request) {
        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .usuario(request.getUsuario())
                .clave(this.passwordEncoder.encode(request.getClave()))
                .nombre(request.getNombre())
                .estado("1")
                .mfaEnabled(false)
                .roles(Role.ADMIN)
                .build();
        this.usuarioRepository.save(usuarioEntity);
    }

    @Override
    public LoginResponse loginUsuario(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsuario(),
                request.getClave()));
        var usuario = this.usuarioRepository.findByUsuario(request.getUsuario()).orElseThrow();
        var jwtToken = jwtService.generateToken(usuario);
        return LoginResponse.builder().jwt(jwtToken).nombreUsuario(usuario.getNombre()).build();
    }

    @Override
    public UsuarioResponse usuarioLogeado() {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UsuarioEntity entityUser = (UsuarioEntity) authenticationToken.getPrincipal();
        return UsuarioResponse.builder().usuario(entityUser.getUsuario())
                .clave(entityUser.getClave())
                .nombre(entityUser.getNombre())
                .estado(entityUser.getEstado())
                .mfaEnabled(entityUser.isMfaEnabled())
                .roles(entityUser.getRoles().name())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.usuarioRepository.findByUsuario(username).orElseThrow();
    }
}
