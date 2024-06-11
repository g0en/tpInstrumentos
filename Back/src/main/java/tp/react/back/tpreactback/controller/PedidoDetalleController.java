package tp.react.back.tpreactback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tp.react.back.tpreactback.modelo.PedidoDetalle;
import tp.react.back.tpreactback.services.PedidoDetalleService;

import java.util.List;

@RestController
@RequestMapping("/PedidoDetalle")
public class PedidoDetalleController {

    @Autowired
    private PedidoDetalleService pedidoDetalleServ;

    @GetMapping("/traer-lista")
    public List<PedidoDetalle> getPedidoDetalle(){
        return pedidoDetalleServ.traerListaPedidoDetalle();
    }

    @GetMapping("/traer/{id}")
    public PedidoDetalle getPedidoDetalle(@PathVariable long id){
        return pedidoDetalleServ.traerPedidoDetalle(id);
    }

    @PostMapping("/cargar")
    public PedidoDetalle cargarPedidoDetalle(@RequestBody PedidoDetalle pedidoDetalle){
        return pedidoDetalleServ.CargarPedidoDetalle(pedidoDetalle);
    }

    @DeleteMapping("/borrar/{id}")
    public void borrarPedidoDetalle(@PathVariable long id){
        pedidoDetalleServ.borrarPedidoDetalle(id);
    }
    @PutMapping("/modificar")
    public PedidoDetalle modificarPedidoDetalle(@RequestBody PedidoDetalle pedidoDetalle){
        return pedidoDetalleServ.modificarPedidoDetalle(pedidoDetalle);
    }



}
