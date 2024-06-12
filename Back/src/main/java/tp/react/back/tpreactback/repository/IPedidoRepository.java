package tp.react.back.tpreactback.repository;

import jakarta.persistence.TemporalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.stereotype.Repository;
import tp.react.back.tpreactback.modelo.Pedido;

import java.util.Date;
import java.util.List;

@Repository
public interface IPedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findAllByFechaBetween(@Temporal(TemporalType.DATE) Date fechaDesde, @Temporal(TemporalType.DATE) Date fechaHasta);
}
