package com.edix.restful.zaps.restcontroller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.edix.restful.zaps.modelo.entities.Valoracion;
import java.util.List;

public interface ValoracionController {

    ResponseEntity<Valoracion> buscarValoracionPorId(@PathVariable int id);

    ResponseEntity<List<Valoracion>> buscarTodasValoraciones();

    ResponseEntity<Void> modificarValoracion(@RequestBody Valoracion valoracion);

    ResponseEntity<Void> crearValoracion(@RequestBody Valoracion valoracion);

    ResponseEntity<Void> eliminarValoracion(@PathVariable int id);
}
