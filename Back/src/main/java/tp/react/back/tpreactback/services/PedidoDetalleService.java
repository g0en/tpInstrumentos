package tp.react.back.tpreactback.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp.react.back.tpreactback.modelo.PedidoDetalle;
import tp.react.back.tpreactback.repository.IPedidoDetalleRepository;

import java.util.List;

@Service
public class PedidoDetalleService {

    @Autowired
    private IPedidoDetalleRepository pedidoDetalleRepos;

    public void borrarPedidoDetalle(long id){
        pedidoDetalleRepos.deleteById(id);
    }
    public PedidoDetalle CargarPedidoDetalle(PedidoDetalle pedidoDetalle){
        return pedidoDetalleRepos.save(pedidoDetalle);
    }
    public PedidoDetalle modificarPedidoDetalle(PedidoDetalle pedidoDetalle){
        PedidoDetalle pedidoDetalleNuevo = pedidoDetalleRepos.findById(pedidoDetalle.getId()).orElse(null);

        if(pedidoDetalle.getId() != 0 ){
            pedidoDetalleNuevo.setId(pedidoDetalle.getId());
        }

        if(pedidoDetalle.getCantidad() != 0 ){
            pedidoDetalleNuevo.setCantidad(pedidoDetalle.getCantidad());
        }

        if(pedidoDetalle.getInstrumento() != null){
            pedidoDetalleNuevo.setInstrumento(pedidoDetalle.getInstrumento());
        }



        if(pedidoDetalle.getSubtotal() != 0 ){
            pedidoDetalleNuevo.setSubtotal(pedidoDetalle.getSubtotal());
        }

        return pedidoDetalleRepos.save(pedidoDetalleNuevo);
    }

    public PedidoDetalle traerPedidoDetalle(long id){
        return pedidoDetalleRepos.findById(id).orElse(null);
    }

    public List<PedidoDetalle> traerListaPedidoDetalle(){
        List<PedidoDetalle> listaPedidoDetalle = pedidoDetalleRepos.findAll();
        return listaPedidoDetalle;
    }

}
