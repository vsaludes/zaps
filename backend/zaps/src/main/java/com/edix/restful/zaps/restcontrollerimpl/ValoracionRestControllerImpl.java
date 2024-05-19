package com.edix.restful.zaps.restcontrollerimpl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edix.restful.zaps.modelo.entities.Valoracion;
import com.edix.restful.zaps.restcontroller.ValoracionController;
import com.edix.restful.zaps.service.ValoracionService;

import java.util.List;

@RestController
@RequestMapping("/api/valoraciones")
public class ValoracionRestControllerImpl implements ValoracionController {

    @Autowired
    private ValoracionService valoracionService;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Valoracion> buscarValoracionPorId(@PathVariable int id) {
        Valoracion valoracion = valoracionService.buscarValoracionPorId(id);
        if (valoracion != null) {
            return ResponseEntity.ok(valoracion);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Valoracion>> buscarTodasValoraciones() {
        List<Valoracion> valoraciones = valoracionService.buscarTodasValoraciones();
        return ResponseEntity.ok(valoraciones);
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> modificarValoracion(@RequestBody Valoracion valoracion) {
        boolean result = valoracionService.modificarValoracion(valoracion);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> crearValoracion(@RequestBody Valoracion valoracion) {
        boolean result = valoracionService.crearValoracion(valoracion);
        if (result) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarValoracion(@PathVariable int id) {
        boolean result = valoracionService.eliminarValoracion(id);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
