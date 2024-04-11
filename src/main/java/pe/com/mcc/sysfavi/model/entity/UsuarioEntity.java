package pe.com.mcc.sysfavi.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_USUARIOS")
public class UsuarioEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUsuario;
    @Column(unique = true)
    private String usuario;
    private String clave;
    private String nombre;
    private String estado;
    private boolean mfaEnabled;
    @Enumerated(EnumType.STRING)
    private Role roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.getAuthorities();
    }

    @Override
    public String getPassword() {
        return this.clave;
    }

    @Override
    public String getUsername() {
        return this.usuario;
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
}
