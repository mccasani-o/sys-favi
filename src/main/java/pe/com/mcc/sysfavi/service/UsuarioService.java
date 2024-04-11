package pe.com.mcc.sysfavi.service;

import pe.com.mcc.sysfavi.model.request.LoginRequest;
import pe.com.mcc.sysfavi.model.request.RegistroRequest;
import pe.com.mcc.sysfavi.model.response.LoginResponse;
import pe.com.mcc.sysfavi.model.response.UsuarioResponse;

public interface UsuarioService {
    void registrarUsuario(RegistroRequest request);

    LoginResponse loginUsuario(LoginRequest request);

    UsuarioResponse usuarioLogeado();
}
