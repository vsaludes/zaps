package com.edix.restful.zaps.restcontrollerimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edix.restful.zaps.modelo.entities.DetallePedido;
import com.edix.restful.zaps.restcontroller.DetallePedidoController;
import com.edix.restful.zaps.service.DetallePedidoService;

import java.util.List;

@RestController
@RequestMapping("/api/detallespedido")
public class DetallePedidoRestControllerImpl implements DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;

    @Override
    @GetMapping("/{idDetallePedido}")
    public ResponseEntity<DetallePedido> buscarDetallePedidoPorId(@PathVariable int id) {
        DetallePedido detallePedido = detallePedidoService.buscarDetallePedidoPorId(id);
        if (detallePedido != null) {
            return ResponseEntity.ok(detallePedido);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<List<DetallePedido>> buscarTodosDetallesPedido() {
        List<DetallePedido> detallesPedido = detallePedidoService.buscarTodosDetallesPedido();
        return ResponseEntity.ok(detallesPedido);
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> modificarDetallePedido(@RequestBody DetallePedido detallePedido) {
        boolean result = detallePedidoService.modificarDetallePedido(detallePedido);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> crearDetallePedido(@RequestBody DetallePedido detallePedido) {
        boolean result = detallePedidoService.crearDetallePedido(detallePedido);
        if (result) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    @DeleteMapping("/{idDetallePedido}")
    public ResponseEntity<Void> eliminarDetallePedido(@PathVariable int id) {
        boolean result = detallePedidoService.eliminarDetallePedido(id);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
