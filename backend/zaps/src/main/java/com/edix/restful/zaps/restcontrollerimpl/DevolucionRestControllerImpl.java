package com.edix.restful.zaps.restcontrollerimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edix.restful.zaps.modelo.entities.Devolucion;
import com.edix.restful.zaps.restcontroller.DevolucionController;
import com.edix.restful.zaps.service.DevolucionService;

import java.util.List;

@RestController
@RequestMapping("/api/devoluciones")
public class DevolucionRestControllerImpl implements DevolucionController {

    @Autowired
    private DevolucionService devolucionService;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Devolucion> buscarDevolucionPorId(@PathVariable int id) {
        Devolucion devolucion = devolucionService.buscarDevolucionPorId(id);
        if (devolucion != null) {
            return ResponseEntity.ok(devolucion);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Devolucion>> buscarTodasDevoluciones() {
        List<Devolucion> devoluciones = devolucionService.buscarTodasDevoluciones();
        return ResponseEntity.ok(devoluciones);
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> modificarDevolucion(@RequestBody Devolucion devolucion) {
        boolean result = devolucionService.modificarDevolucion(devolucion);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> crearDevolucion(@RequestBody Devolucion devolucion) {
        boolean result = devolucionService.crearDevolucion(devolucion);
        if (result) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDevolucion(@PathVariable int id) {
        boolean result = devolucionService.eliminarDevolucion(id);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
