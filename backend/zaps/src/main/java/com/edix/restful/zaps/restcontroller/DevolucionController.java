package com.edix.restful.zaps.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.edix.restful.zaps.modelo.entities.Devolucion;
import java.util.List;

public interface DevolucionController {

    ResponseEntity<Devolucion> buscarDevolucionPorId(@PathVariable int id);

    ResponseEntity<List<Devolucion>> buscarTodasDevoluciones();

    ResponseEntity<Void> modificarDevolucion(@RequestBody Devolucion devolucion);

    ResponseEntity<Void> crearDevolucion(@RequestBody Devolucion devolucion);

    ResponseEntity<Void> eliminarDevolucion(@PathVariable int id);
}
