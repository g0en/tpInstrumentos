package tp.react.back.tpreactback.modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;


import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pedido extends EntityId{

private Date fecha;
private Double totalPedido;
private String titulo;

@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
@JsonManagedReference
private List<PedidoDetalle> pedidoDetalle;



}
