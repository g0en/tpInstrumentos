package tp.react.back.tpreactback.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp.react.back.tpreactback.modelo.Usuario;
import tp.react.back.tpreactback.repository.IUsuarioRepository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UsuarioService {
    @Autowired
    private IUsuarioRepository usuarioRepository;

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario existeEnBase(String nombreUsuario, String clave){
        if(this.usuarioRepository.existsByNombreUsuarioAndClave(nombreUsuario, MD5Encriptador(clave))){
            return this.usuarioRepository.findByNombreUsuario(nombreUsuario);
        }
        return null;
    }

    private String MD5Encriptador(String clave){
        String encriptado = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(clave.getBytes());
            StringBuilder sb = new StringBuilder();
            for(byte b : digest){
                sb.append(String.format("%02x",b));
            }
            encriptado = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return encriptado;
    }
}
