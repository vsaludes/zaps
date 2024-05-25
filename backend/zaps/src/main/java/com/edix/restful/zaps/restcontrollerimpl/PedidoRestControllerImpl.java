package com.edix.restful.zaps.restcontrollerimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edix.restful.zaps.modelo.entities.Pedido;
import com.edix.restful.zaps.restcontroller.PedidoController;
import com.edix.restful.zaps.service.PedidoService;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoRestControllerImpl implements PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable int id) {
        Pedido pedido = pedidoService.buscarPedidoPorId(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Pedido>> buscarPedidoPorUsuario(@PathVariable int idUsuario) {
        List<Pedido> pedidos = pedidoService.buscarPedidoPorUsuario(idUsuario);
        return ResponseEntity.ok(pedidos);
    }

    @Override
    @PutMapping("/procesar/{id}")
    public ResponseEntity<Void> procesarPedido(@PathVariable int id) {
        boolean result = pedidoService.procesarPedido(id);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    @PutMapping("/cancelar/{id}")
    public ResponseEntity<Void> cancelarPedido(@PathVariable int id) {
        boolean result = pedidoService.cancelarPedido(id);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    @PutMapping("/entregar/{id}")
    public ResponseEntity<Void> marcarPedidoEntregado(@PathVariable int id) {
        boolean result = pedidoService.marcarPedidoEntregado(id);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
