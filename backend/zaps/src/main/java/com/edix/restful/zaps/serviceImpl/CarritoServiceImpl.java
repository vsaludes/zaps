package com.edix.restful.zaps.serviceImpl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.restful.zaps.modelo.dto.ProductoDTO;
import com.edix.restful.zaps.modelo.entities.Carrito;
import com.edix.restful.zaps.modelo.entities.CarritoProducto;
import com.edix.restful.zaps.modelo.entities.Producto;
import com.edix.restful.zaps.modelo.entities.Usuario;
import com.edix.restful.zaps.repository.CarritoRepository;
import com.edix.restful.zaps.repository.ProductoRepository;
import com.edix.restful.zaps.repository.UsuarioRepository;
import com.edix.restful.zaps.service.CarritoService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Carrito buscarCarritoPorId(int idCarrito) {
        return carritoRepository.findById(idCarrito).orElse(null);
    }

    @Override
    public List<Carrito> buscarTodosCarritos() {
        return carritoRepository.findAll();
    }

    @Override
    public boolean modificarCarrito(Carrito carrito) {
        if (carritoRepository.existsById(carrito.getIdCarrito())) {
            carritoRepository.save(carrito);
            return true;
        }
        return false;
    }

    @Override
    public boolean crearCarrito(int idUsuario) {
        // Verificar si ya existe un carrito para el usuario
        Carrito carritoExistente = carritoRepository.findByUsuarioId(idUsuario);
        if (carritoExistente != null) {
            return false; // Ya existe un carrito para este usuario
        }
        
        // Si no existe, crear un nuevo carrito
        Carrito nuevoCarrito = new Carrito();
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
        if (usuario == null) {
            return false; // El usuario no existe
        }
        nuevoCarrito.setUsuario(usuario);
        
        // Guardar el nuevo carrito en la base de datos
        carritoRepository.save(nuevoCarrito);
        return true;
    }

    @Override
    public boolean eliminarCarrito(int idCarrito) {
        if (carritoRepository.existsById(idCarrito)) {
            carritoRepository.deleteById(idCarrito);
            return true;
        }
        return false;
    }
    
    /*@Override
    public boolean agregarProductoAlCarrito(int idCarrito, ProductoDTO productoDTO) {
        Carrito carrito = carritoRepository.findById(idCarrito).orElse(null);
        if (carrito != null) {
            List<Producto> productos = carrito.getProductos();
            for (Producto p : productos) {
                if (p.getIdProducto() == productoDTO.getIdProducto()) {
                    p.setCantidad(p.getCantidad() + productoDTO.getCantidad());
                    
                    actualizarSubtotal(carrito);
                    carritoRepository.save(carrito);
                    return true;
                }
            }
            Producto nuevoProducto = new Producto();
            nuevoProducto.setIdProducto(productoDTO.getIdProducto());
            nuevoProducto.setCantidad(productoDTO.getCantidad());
            productos.add(nuevoProducto);
            actualizarSubtotal(carrito);
            carritoRepository.save(carrito);
            return true;
        }
        return false;
    }*/
    @Override
    public boolean agregarProductoAlCarrito(int idCarrito, int idProducto, int cantidad) {
        Carrito carrito = carritoRepository.findById(idCarrito).orElse(null);
        if (carrito == null) {
            return false;
        }

        Producto producto = productoRepository.findById(idProducto).orElse(null);
        if (producto == null) {
            return false;
        }

        carrito.agregarProducto(producto, cantidad);
        carritoRepository.save(carrito);
        return true;
    }

    @Override
    public boolean eliminarProductoDelCarrito(int idCarrito, Producto producto) {
        Carrito carrito = carritoRepository.findById(idCarrito).orElse(null);
        if (carrito != null) {
            List<Producto> productos = carrito.getProductos();
            for (int i = 0; i < productos.size(); i++) {
                if (productos.get(i).getIdProducto() == producto.getIdProducto()) {
                    productos.remove(i);
                    carritoRepository.save(carrito);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public BigDecimal calcularTotalCarrito(int idCarrito) {
        Carrito carrito = carritoRepository.findById(idCarrito).orElse(null);
        if (carrito != null) {
            BigDecimal total = BigDecimal.ZERO;
            for (CarritoProducto cp : carrito.getCarritoProducto()) {
                total = total.add(cp.getSubtotal());
            }
            return total;
        }
        return BigDecimal.ZERO;
    }
}