package tp.react.back.tpreactback.repository;

import jakarta.persistence.TemporalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.stereotype.Repository;
import tp.react.back.tpreactback.modelo.Pedido;
import tp.react.back.tpreactback.modelo.PedidoCount;

import java.util.Date;
import java.util.List;

@Repository
public interface IPedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findAllByFechaBetween(@Temporal(TemporalType.DATE) Date fechaDesde, @Temporal(TemporalType.DATE) Date fechaHasta);

    @Query(value = "SELECT DATE_FORMAT(p.fecha, '%Y-%m') AS anio_mes, COUNT(*) AS total_pedidos " +
            "FROM Pedido p " +
            "GROUP BY DATE_FORMAT(p.fecha, '%Y-%m')", nativeQuery = true)
    List<Object[]> countPedidosGroupedByMesYAnio();



    @Query(value = "SELECT i.instrumento AS instrumento, COUNT(pd.pedido_id) AS cantidad_pedidos " +
            "FROM pedido_detalle pd " +
            "JOIN instrumento i ON pd.instrumento_id = i.id " +
            "GROUP BY i.instrumento", nativeQuery = true)
    List<Object[]> countPedidosDetalleGroupedByInstrumento();

}
