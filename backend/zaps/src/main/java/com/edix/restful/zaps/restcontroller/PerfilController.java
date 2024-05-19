package com.edix.restful.zaps.restcontroller;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.edix.restful.zaps.modelo.entities.Perfil;
import java.util.List;

public interface PerfilController {

    ResponseEntity<Perfil> buscarPerfilPorId(@PathVariable int id);

    ResponseEntity<List<Perfil>> buscarTodosPerfiles();

    ResponseEntity<Void> modificarPerfil(@RequestBody Perfil perfil);

    ResponseEntity<Void> crearPerfil(@RequestBody Perfil perfil);

    ResponseEntity<Void> eliminarPerfil(@PathVariable int id);
}
