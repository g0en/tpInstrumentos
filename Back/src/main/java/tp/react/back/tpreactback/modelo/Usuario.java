package tp.react.back.tpreactback.modelo;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Usuario extends EntityId{
    private String nombreUsuario;
    private String clave;
    private String rol;
}
