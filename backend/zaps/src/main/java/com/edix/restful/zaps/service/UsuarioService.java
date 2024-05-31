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
       
    boolean agregarAListaDeseos(int idUsuario, int idProducto);
    
    boolean eliminarDeListaDeseos(int idUsuario, int idProducto);
    
    boolean agregarValoracionAUsuario(Usuario usuario, Valoracion valoracion);
    
    boolean eliminarValoracionDeUsuario(Usuario usuario, Valoracion valoracion);
    
    boolean enviarNotificacionProducto(Usuario usuario, Producto producto);
    
    boolean crearUsuario(Usuario usuario);
        
    boolean eliminarUsuario(int idUsuario);
}