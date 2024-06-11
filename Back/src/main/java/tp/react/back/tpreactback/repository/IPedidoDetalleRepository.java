package tp.react.back.tpreactback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tp.react.back.tpreactback.modelo.PedidoDetalle;

@Repository
public interface IPedidoDetalleRepository extends JpaRepository<PedidoDetalle, Long> {
}
