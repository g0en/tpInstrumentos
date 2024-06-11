package tp.react.back.tpreactback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tp.react.back.tpreactback.modelo.Pedido;
import tp.react.back.tpreactback.services.PedidoService;

@RestController
@RequestMapping("/Pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoServ;

    @PostMapping("/cargar")
    public Pedido guardarPedido(@RequestBody Pedido pedido){
        return pedidoServ.cargarPedido(pedido);
    }

    @GetMapping("/traer/{id}")
    public Pedido getPedido(@PathVariable long id){
        return pedidoServ.traerPedido(id);
    }

    @GetMapping("/traer-lista")
    public Iterable<Pedido> getPedidos(){
        return pedidoServ.traerListaPedidos();
    }

    @DeleteMapping("/borrar/{id}")
    public void borrarPedido(@PathVariable long id){
        pedidoServ.borrarPedido(id);
    }

    @PutMapping("/modificar")
    public Pedido modificarPedido(@RequestBody Pedido pedido){
        return pedidoServ.modificarPedido(pedido);
    }

}
