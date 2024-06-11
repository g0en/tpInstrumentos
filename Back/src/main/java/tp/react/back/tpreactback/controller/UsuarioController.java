package tp.react.back.tpreactback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tp.react.back.tpreactback.repository.UsuarioRepository;

@RestController
@RequestMapping("/Usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(String nombreUsuario, String clave) {
        return ResponseEntity.ok(u)
    }
}
