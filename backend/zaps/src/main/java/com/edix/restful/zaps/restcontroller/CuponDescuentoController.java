package com.edix.restful.zaps.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.edix.restful.zaps.modelo.entities.CuponDescuento;
import java.util.List;

public interface CuponDescuentoController {

    ResponseEntity<CuponDescuento> buscarCuponPorId(@PathVariable int id);

    ResponseEntity<List<CuponDescuento>> buscarTodosCupones();

    ResponseEntity<Void> modificarCupon(@RequestBody CuponDescuento cupon);

    ResponseEntity<Void> crearCupon(@RequestBody CuponDescuento cupon);

    ResponseEntity<Void> eliminarCupon(@PathVariable int id);
}
