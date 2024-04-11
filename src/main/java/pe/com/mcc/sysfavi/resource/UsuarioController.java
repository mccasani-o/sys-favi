package pe.com.mcc.sysfavi.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.mcc.sysfavi.model.request.LoginRequest;
import pe.com.mcc.sysfavi.model.request.RegistroRequest;
import pe.com.mcc.sysfavi.model.response.LoginResponse;
import pe.com.mcc.sysfavi.model.response.UsuarioResponse;
import pe.com.mcc.sysfavi.service.UsuarioService;

import java.net.URI;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService service;

    @PostMapping("/registro")
    public ResponseEntity<Void> registro(@RequestBody RegistroRequest request) {
        this.service.registrarUsuario(request);
        return ResponseEntity.created(this.getUri()).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok().body(this.service.loginUsuario(request));
    }

    @GetMapping("/usuario-logeado")
    public ResponseEntity<UsuarioResponse> usuarioLogeado() {
        return ResponseEntity.ok().body(this.service.usuarioLogeado());
    }

    private URI getUri() {
        return URI.create(fromCurrentContextPath().path("/api/v1/auth/register").toUriString());
    }

}
