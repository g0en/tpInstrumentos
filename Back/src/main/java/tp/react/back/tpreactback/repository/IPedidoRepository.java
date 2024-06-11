package tp.react.back.tpreactback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tp.react.back.tpreactback.modelo.Pedido;

@Repository
public interface IPedidoRepository extends JpaRepository<Pedido, Long> {
}
