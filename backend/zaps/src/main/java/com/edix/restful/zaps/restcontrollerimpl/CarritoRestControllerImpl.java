package com.edix.restful.zaps.restcontrollerimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edix.restful.zaps.modelo.entities.Carrito;
import com.edix.restful.zaps.restcontroller.CarritoController;
import com.edix.restful.zaps.service.CarritoService;

import java.util.List;

@RestController
@RequestMapping("/api/carritos")
public class CarritoRestControllerImpl implements CarritoController {

    @Autowired
    private CarritoService carritoService;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Carrito> buscarCarritoPorId(@PathVariable int id) {
        Carrito carrito = carritoService.buscarCarritoPorId(id);
        if (carrito != null) {
            return ResponseEntity.ok(carrito);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Carrito>> buscarTodosCarritos() {
        List<Carrito> carritos = carritoService.buscarTodosCarritos();
        return ResponseEntity.ok(carritos);
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> modificarCarrito(@RequestBody Carrito carrito) {
        boolean result = carritoService.modificarCarrito(carrito);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> crearCarrito(@RequestBody Carrito carrito) {
        boolean result = carritoService.crearCarrito(carrito);
        if (result) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarrito(@PathVariable int id) {
        boolean result = carritoService.eliminarCarrito(id);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
