package com.edix.restful.zaps.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edix.restful.zaps.modelo.entities.Carrito;
import com.edix.restful.zaps.modelo.entities.ListaDeseo;
import com.edix.restful.zaps.modelo.entities.Producto;
import com.edix.restful.zaps.modelo.entities.Usuario;
import com.edix.restful.zaps.modelo.entities.Valoracion;
import com.edix.restful.zaps.repository.ProductoRepository;
import com.edix.restful.zaps.repository.UsuarioRepository;
import com.edix.restful.zaps.repository.ValoracionRepository;
import com.edix.restful.zaps.service.CarritoService;
import com.edix.restful.zaps.service.ListaDeseoService;
import com.edix.restful.zaps.service.ProductoService;
import com.edix.restful.zaps.service.UsuarioService;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private ValoracionRepository valoracionRepository;
    
    @Autowired
    private ListaDeseoService listaDeseoService;
    
    @Autowired
    private CarritoService carritoService;
    
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario buscarUsuarioPorId(int idUsuario) {
        return usuarioRepository.findById(idUsuario).orElse(null);
    }

    @Override
    public Optional<Usuario> buscarUsuarioPorNombre(String username) {
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
    public boolean agregarAListaDeseos (int idUsuario, int idProducto) {
     
    	Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
    	Producto producto = productoService.buscarProductoId(idProducto);
    	
        if (usuario != null && producto != null) {
            ListaDeseo listaDeseo = usuario.getListaDeseo();
            listaDeseo.getProducto().add(producto);
            listaDeseoService.guardarListaDeseo(listaDeseo);
            return true;
        }
        return false;
    }
    	
        /*ListaDeseo listaDeseo = new ListaDeseo();
        listaDeseo.setUsuario(usuario);
        listaDeseo.setProducto(List.of(producto)); 
        if (usuario.getListaDeseo().add(listaDeseo)) {
            usuarioRepository.save(usuario); 
            return true;
        }
        return false;
    }
*/

    @Override
    public boolean eliminarDeListaDeseos(int idUsuario, int idProducto) {
    	
    	Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
    	Producto producto = productoService.buscarProductoId(idProducto);
    	
    	if (usuario != null && producto != null) {
    	ListaDeseo listaDeseo = usuario.getListaDeseo();
    	listaDeseo.getProducto().remove(producto);
        return listaDeseoService.guardarListaDeseo(listaDeseo);
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

	@Override
	public boolean crearUsuario(Usuario usuario) {
		if (usuarioRepository.existsByUsername(usuario.getUsername()) || usuarioRepository.existsByEmail(usuario.getEmail())) {
		return false;
	}
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuarioRepository.save(usuario);
        
        ListaDeseo listaDeseo = new ListaDeseo();
        listaDeseo.setUsuario(usuario);
        listaDeseoService.crearListaDeseo(listaDeseo);
        
        Carrito carrito = new Carrito();
        carrito.setUsuario(usuario);
        carritoService.crearCarrito(carrito);
        
        return true;
    }
	
	@Override
    public boolean eliminarUsuario(int idUsuario) {
        try {
            if (usuarioRepository.existsById(idUsuario)) {
                usuarioRepository.deleteById(idUsuario);
                return true; 
            } else {
                return false;
            }
        } catch (Exception e) {
            
            return false;
        }
    }
	
}
