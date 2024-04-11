package pe.com.mcc.sysfavi.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UsuarioResponse {
    private String usuario;
    private String clave;
    private String nombre;
    private String estado;
    private boolean mfaEnabled;
    private String roles;
}
