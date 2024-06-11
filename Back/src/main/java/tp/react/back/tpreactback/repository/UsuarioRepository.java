package tp.react.back.tpreactback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tp.react.back.tpreactback.modelo.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByNombreUsuario(String nombreUsuario);
}
