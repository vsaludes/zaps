package com.edix.restful.zaps.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.restful.zaps.modelo.entities.ListaDeseo;
import com.edix.restful.zaps.modelo.entities.Producto;
import com.edix.restful.zaps.modelo.entities.Usuario;
import com.edix.restful.zaps.modelo.entities.Valoracion;
import com.edix.restful.zaps.repository.ProductoRepository;
import com.edix.restful.zaps.repository.UsuarioRepository;
import com.edix.restful.zaps.repository.ValoracionRepository;
import com.edix.restful.zaps.service.UsuarioService;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private ValoracionRepository valoracionRepository;

    @Override
    public Usuario buscarUsuarioPorId(int idUsuario) {
        return usuarioRepository.findById(idUsuario).orElse(null);
    }

    @Override
    public Usuario buscarUsuarioPorNombre(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public List<Usuario> buscarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public boolean modificarUsuario(Usuario usuario) {
        if (usuarioRepository.existsById(usuario.getIdUsuario())) {
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }
/*
    @Override
    public boolean agregarAListaDeseos(Usuario usuario, Producto producto) {
        if (usuario.getListaDeseo().add(producto)) {
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }*/
    
    @Override
    public boolean agregarAListaDeseos(Usuario usuario, Producto producto) {
     
        ListaDeseo listaDeseo = new ListaDeseo();
        listaDeseo.setUsuario(usuario);
        listaDeseo.setProducto(List.of(producto)); 
        if (usuario.getListaDeseo().add(listaDeseo)) {
            usuarioRepository.save(usuario); 
            return true;
        }
        return false;
    }


    @Override
    public boolean eliminarDeListaDeseos(Usuario usuario, Producto producto) {
        if (usuario.getListaDeseo().remove(producto)) {
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }

    @Override
    public boolean agregarValoracionAUsuario(Usuario usuario, Valoracion valoracion) {
        if (usuario.getValoracion().add(valoracion)) {
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarValoracionDeUsuario(Usuario usuario, Valoracion valoracion) {
        if (usuario.getValoracion().remove(valoracion)) {
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }

    @Override
    public boolean enviarNotificacionProducto(Usuario usuario, Producto producto) {
        // Implementar lógica de notificación
        return true;
    }
}
