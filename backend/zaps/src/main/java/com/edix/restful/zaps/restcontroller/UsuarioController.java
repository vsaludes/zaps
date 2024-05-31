package com.edix.restful.zaps.restcontroller;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RequestBody;

import com.edix.restful.zaps.modelo.entities.Producto;
import com.edix.restful.zaps.modelo.entities.Usuario;
import com.edix.restful.zaps.modelo.entities.Valoracion;

import java.security.Principal;
import java.util.List;

public interface UsuarioController {

    ResponseEntity<Usuario> buscarUsuarioPorId(int id);

    ResponseEntity<Usuario> buscarUsuarioPorNombre(String username);

    ResponseEntity<Usuario> buscarUsuarioPorEmail(String email);

    ResponseEntity<List<Usuario>> buscarTodosUsuarios();

    ResponseEntity<Void> modificarUsuario(@Validated @RequestBody Usuario usuario);

    ResponseEntity<Void> agregarAListaDeseos(int id, @RequestBody Producto producto);

    ResponseEntity<Void> eliminarDeListaDeseos(int id, @RequestBody Producto producto);

    ResponseEntity<Void> agregarValoracionAUsuario(int id, @RequestBody Valoracion valoracion);

    ResponseEntity<Void> eliminarValoracionDeUsuario(int id, @RequestBody Valoracion valoracion);

    ResponseEntity<Void> enviarNotificacionProducto(int id, @RequestBody Producto producto);

    ResponseEntity<Usuario> getCurrentUser(Principal principal);
}
