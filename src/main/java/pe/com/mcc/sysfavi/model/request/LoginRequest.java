package pe.com.mcc.sysfavi.model.request;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class LoginRequest {
    private String usuario;
    private String clave;
}
