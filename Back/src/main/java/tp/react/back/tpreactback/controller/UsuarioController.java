package tp.react.back.tpreactback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tp.react.back.tpreactback.modelo.Usuario;
import tp.react.back.tpreactback.services.UsuarioService;

@RestController
@RequestMapping("/Usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String nombreUsuario, @RequestParam String clave) {
        Usuario usuario = this.usuarioService.existeEnBase(nombreUsuario, clave);
        return ResponseEntity.ok(usuario);
    }
}
