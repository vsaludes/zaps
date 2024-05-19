package com.edix.restful.zaps.restcontrollerimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edix.restful.zaps.modelo.entities.CuponDescuento;
import com.edix.restful.zaps.restcontroller.CuponDescuentoController;
import com.edix.restful.zaps.service.CuponDescuentoService;

import java.util.List;

@RestController
@RequestMapping("/api/cupones")
public class CuponDescuentoRestControllerImpl implements CuponDescuentoController {

    @Autowired
    private CuponDescuentoService cuponDescuentoService;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CuponDescuento> buscarCuponPorId(@PathVariable int id) {
        CuponDescuento cupon = cuponDescuentoService.buscarCuponPorId(id);
        if (cupon != null) {
            return ResponseEntity.ok(cupon);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<List<CuponDescuento>> buscarTodosCupones() {
        List<CuponDescuento> cupones = cuponDescuentoService.buscarTodosCupones();
        return ResponseEntity.ok(cupones);
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> modificarCupon(@RequestBody CuponDescuento cupon) {
        boolean result = cuponDescuentoService.modificarCupon(cupon);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> crearCupon(@RequestBody CuponDescuento cupon) {
        boolean result = cuponDescuentoService.crearCupon(cupon);
        if (result) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCupon(@PathVariable int id) {
        boolean result = cuponDescuentoService.eliminarCupon(id);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
