package pe.com.mcc.sysfavi.model.response;

import lombok.Builder;
import lombok.Data;
import pe.com.mcc.sysfavi.model.entity.RoleEntity;

import java.util.HashSet;
import java.util.Set;

@Builder
@Data
public class UsuarioResponse {
    private String usuario;
    private String clave;
    private String nombre;
    private String estado;
    private boolean mfaEnabled;
    private Set<RoleEntity> roles;
}
