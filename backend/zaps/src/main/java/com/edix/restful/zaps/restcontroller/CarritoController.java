package com.edix.restful.zaps.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.edix.restful.zaps.modelo.dto.CarritoDTO;
import com.edix.restful.zaps.modelo.dto.CrearCarritoDTO;
import com.edix.restful.zaps.modelo.entities.Carrito;
import java.util.List;

public interface CarritoController {

    ResponseEntity<Carrito> buscarCarritoPorId(@PathVariable int id);

    ResponseEntity<List<Carrito>> buscarTodosCarritos();

    ResponseEntity<Void> modificarCarrito(@RequestBody Carrito carrito);

    ResponseEntity<Void> crearCarrito(@RequestBody CrearCarritoDTO crearCarritoDTO);

    ResponseEntity<Void> eliminarCarrito(@PathVariable int id);
}
