package tp.react.back.tpreactback.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PedidoDetalleInstrumentoCount {

    private String instrumento;
    private Long cantidadPedidos;
}
