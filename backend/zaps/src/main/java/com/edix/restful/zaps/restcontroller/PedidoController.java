package com.edix.restful.zaps.restcontroller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import com.edix.restful.zaps.modelo.entities.Pedido;
import java.util.List;

public interface PedidoController {

    ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable int id);

    ResponseEntity<List<Pedido>> buscarPedidoPorUsuario(@PathVariable int idUsuario);

    ResponseEntity<Void> procesarPedido(@PathVariable int id);

    ResponseEntity<Void> cancelarPedido(@PathVariable int id);

    ResponseEntity<Void> marcarPedidoEntregado(@PathVariable int id);
}
