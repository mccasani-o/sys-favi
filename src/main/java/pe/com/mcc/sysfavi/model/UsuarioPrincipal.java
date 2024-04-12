package pe.com.mcc.sysfavi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pe.com.mcc.sysfavi.model.entity.UsuarioEntity;

import java.util.Collection;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPrincipal implements UserDetails {


    private  UsuarioEntity usuarioEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.usuarioEntity.getRoles().getAuthorities();
    }

    @Override
    public String getPassword() {
        return usuarioEntity.getClave();
    }

    @Override
    public String getUsername() {
        return this.usuarioEntity.getUsuario();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static UsuarioPrincipal buildUsuarioPrincipal(UsuarioEntity usuario) {
        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .idUsuario(usuario.getIdUsuario())
                .usuario(usuario.getUsuario())
                .clave(usuario.getClave())
                .nombre(usuario.getNombre())
                .estado(usuario.getEstado())
                .mfaEnabled(usuario.isMfaEnabled())
                .roles(usuario.getRoles())
                .build();

        return new UsuarioPrincipal(usuarioEntity);
    }
}
