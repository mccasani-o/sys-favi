package pe.com.mcc.sysfavi.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private String jwt;
    private String nombreUsuario;
}
