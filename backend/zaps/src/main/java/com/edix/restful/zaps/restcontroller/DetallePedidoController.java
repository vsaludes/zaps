package com.edix.restful.zaps.restcontroller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.edix.restful.zaps.modelo.entities.DetallePedido;
import java.util.List;

public interface DetallePedidoController {

    ResponseEntity<DetallePedido> buscarDetallePedidoPorId(@PathVariable int idPedido);

    ResponseEntity<List<DetallePedido>> buscarTodosDetallesPedido();

    ResponseEntity<Void> modificarDetallePedido(@RequestBody DetallePedido detallePedido);

    ResponseEntity<Void> crearDetallePedido(@RequestBody DetallePedido detallePedido);

    ResponseEntity<Void> eliminarDetallePedido(@PathVariable int idPedido);
}
