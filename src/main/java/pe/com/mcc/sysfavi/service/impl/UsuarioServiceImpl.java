package pe.com.mcc.sysfavi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.com.mcc.sysfavi.config.JwtService;
import pe.com.mcc.sysfavi.model.UsuarioPrincipal;
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
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService detailsService;

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
        UserDetails userDetails = this.detailsService.loadUserByUsername(request.getUsuario());
        var jwtToken = jwtService.generateToken(userDetails);
        return LoginResponse.builder().jwt(jwtToken).nombreUsuario(userDetails.getUsername()).build();
    }

    @Override
    public UsuarioResponse usuarioLogeado() {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authenticationToken.getPrincipal();

        return UsuarioResponse.builder().usuario(usuarioPrincipal.getUsuarioEntity().getUsuario())
                .clave(usuarioPrincipal.getUsuarioEntity().getClave())
                .nombre(usuarioPrincipal.getUsuarioEntity().getNombre())
                .estado(usuarioPrincipal.getUsuarioEntity().getEstado())
                .mfaEnabled(usuarioPrincipal.getUsuarioEntity().isMfaEnabled())
                .roles(usuarioPrincipal.getUsuarioEntity().getRoles().name())
                .build();
    }


}
