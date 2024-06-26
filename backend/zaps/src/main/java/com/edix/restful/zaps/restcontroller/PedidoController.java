package com.edix.restful.zaps.restcontroller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import com.edix.restful.zaps.modelo.entities.Pedido;
import java.util.List;

public interface PedidoController {

    ResponseEntity<Pedido> buscarPedidoPorId(int idPedido);

    ResponseEntity<List<Pedido>> buscarPedidoPorUsuario(int idUsuario);

    ResponseEntity<Object> procesarPedido(int idCarrito);

    ResponseEntity<String> cancelarPedido(int idPedido);

    ResponseEntity<String> marcarPedidoEntregado(int idPedido);
    
    ResponseEntity<List<Pedido>> buscarTodosPedidos();
    
    ResponseEntity<Void> eliminarPedidoPorId(int idPedido);
}
