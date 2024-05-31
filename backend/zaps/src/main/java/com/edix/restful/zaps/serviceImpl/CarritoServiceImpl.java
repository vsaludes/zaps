package com.edix.restful.zaps.serviceImpl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.restful.zaps.modelo.dto.ProductoDTO;
import com.edix.restful.zaps.modelo.entities.Carrito;
import com.edix.restful.zaps.modelo.entities.CarritoProducto;
import com.edix.restful.zaps.modelo.entities.Producto;
import com.edix.restful.zaps.modelo.entities.Usuario;
import com.edix.restful.zaps.repository.CarritoProductoRepository;
import com.edix.restful.zaps.repository.CarritoRepository;
import com.edix.restful.zaps.repository.ProductoRepository;
import com.edix.restful.zaps.repository.UsuarioRepository;
import com.edix.restful.zaps.service.CarritoService;

import jakarta.transaction.Transactional;

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
    @Autowired
    private CarritoProductoRepository carritoProductoRepository;

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
    public boolean crearCarrito(Carrito carrito) {
        try {
            carritoRepository.save(carrito);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean eliminarCarrito(int idCarrito) {
        if (carritoRepository.existsById(idCarrito)) {
            carritoRepository.deleteById(idCarrito);
            return true;
        }
        return false;
    }
    
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

        if (producto.getStock() < cantidad) {
            return false; 
        }
        
        carrito.agregarProducto(producto, cantidad);
        carritoRepository.save(carrito);
        return true;
    }

    @Override
    @Transactional
    public boolean eliminarProductoDelCarrito(int idCarrito, int idProducto, int cantidad) {
        Carrito carrito = carritoRepository.findById(idCarrito).orElse(null);
        if (carrito == null) {
            return false;
        }

        List<CarritoProducto> carritoProductos = carrito.getCarritoProducto();
        for (CarritoProducto carritoProducto : carritoProductos) {
            if (carritoProducto.getProducto().getIdProducto() == idProducto) {
                int cantidadActual = carritoProducto.getCantidad();
                if (cantidadActual == cantidad) {
                	if (carritoProductos.size() == 1) {
                        carrito.getCarritoProducto().remove(carritoProducto);
                    carritoProductoRepository.deleteByProductoIdProducto(idProducto);}
                } else {
                    int nuevaCantidad = cantidadActual - cantidad;
                    carritoProducto.setCantidad(nuevaCantidad);
                    BigDecimal nuevoSubtotal = carritoProducto.getProducto().getPrecio().multiply(BigDecimal.valueOf(nuevaCantidad));
                    carritoProducto.setSubtotal(nuevoSubtotal);
                    carritoRepository.save(carrito);
                }
                
                return true;
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

	@Override
	public Carrito buscarCarritoPorIdUsuario(int idUsuario) {
		
		return carritoRepository.findByUsuarioId(idUsuario);
	}
}