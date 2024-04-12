package pe.com.mcc.sysfavi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.com.mcc.sysfavi.model.UsuarioPrincipal;
import pe.com.mcc.sysfavi.model.entity.UsuarioEntity;
import pe.com.mcc.sysfavi.repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuarioEntity= this.usuarioRepository.findByUsuario(username).orElseThrow();
        return  UsuarioPrincipal.buildUsuarioPrincipal(usuarioEntity) ;
    }
}
