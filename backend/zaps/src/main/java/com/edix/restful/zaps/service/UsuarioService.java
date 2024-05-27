package com.edix.restful.zaps.service;

import java.util.List;
import java.util.Optional;

import com.edix.restful.zaps.modelo.entities.Producto;
import com.edix.restful.zaps.modelo.entities.Usuario;
import com.edix.restful.zaps.modelo.entities.Valoracion;

public interface UsuarioService {
      
    Usuario buscarUsuarioPorId(int idUsuario);    
    
    Optional<Usuario> buscarUsuarioPorNombre(String username);
    
    Usuario buscarUsuarioPorEmail(String email);
    
    List<Usuario> buscarTodosUsuarios();
    
    boolean modificarUsuario(Usuario usuario);
       
    boolean agregarAListaDeseos(Usuario usuario, Producto producto);
    
    boolean eliminarDeListaDeseos(Usuario usuario, Producto producto);
    
    boolean agregarValoracionAUsuario(Usuario usuario, Valoracion valoracion);
    
    boolean eliminarValoracionDeUsuario(Usuario usuario, Valoracion valoracion);
    
    boolean enviarNotificacionProducto(Usuario usuario, Producto producto);
        
}