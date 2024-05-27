package com.edix.restful.zaps.restcontrollerimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.edix.restful.zaps.modelo.entities.Carrito;
import com.edix.restful.zaps.modelo.entities.CarritoProducto;
import com.edix.restful.zaps.modelo.entities.Pedido;
import com.edix.restful.zaps.restcontroller.PedidoController;
import com.edix.restful.zaps.service.CarritoService;
import com.edix.restful.zaps.service.PedidoService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoRestControllerImpl implements PedidoController {

    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private CarritoService carritoService;
    

    @Override
    @GetMapping("/{idPedido}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable int idPedido) {
        Pedido pedido = pedidoService.buscarPedidoPorId(idPedido);
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
    @PostMapping("/procesar/{idCarrito}")
    public ResponseEntity<Void> procesarPedido(@PathVariable int idCarrito) {
        Carrito carrito = carritoService.buscarCarritoPorId(idCarrito);
        if (carrito == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        
        BigDecimal precioTotal = calcularPrecioTotalCarrito(carrito);
        
        boolean result = pedidoService.procesarPedido(idCarrito, precioTotal);
        if (result) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    private BigDecimal calcularPrecioTotalCarrito(Carrito carrito) {
        BigDecimal precioTotal = BigDecimal.ZERO;
        for (CarritoProducto carritoProducto : carrito.getCarritoProducto()) {
            precioTotal = precioTotal.add(carritoProducto.getSubtotal());
        }
        return precioTotal;
    }
    
    @Override
    @PostMapping("/cancelar/{idPedido}")
    public ResponseEntity<Void> cancelarPedido(@PathVariable int idPedido) {
        boolean result = pedidoService.cancelarPedido(idPedido);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    @PostMapping("/entregar/{idPedido}")
    public ResponseEntity<Void> marcarPedidoEntregado(@PathVariable int idPedido) {
        boolean result = pedidoService.marcarPedidoEntregado(idPedido);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Pedido>> buscarTodosPedidos() {
        try {
            List<Pedido> pedidos = pedidoService.buscarTodos();
            if (pedidos.isEmpty()) {
                return ResponseEntity.noContent().build(); 
            } else {
                return ResponseEntity.ok(pedidos); 
            }
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
        }
    }
    @Override
    @DeleteMapping("/{idPedido}")
    public ResponseEntity<Void> eliminarPedidoPorId(@PathVariable int idPedido) {
        boolean result = pedidoService.eliminarPedidoPorId(idPedido);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}