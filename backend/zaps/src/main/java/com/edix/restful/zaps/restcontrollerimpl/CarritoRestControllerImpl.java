package com.edix.restful.zaps.restcontrollerimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edix.restful.zaps.modelo.dto.CarritoDTO;
import com.edix.restful.zaps.modelo.dto.CrearCarritoDTO;
import com.edix.restful.zaps.modelo.dto.ProductoDTO;
import com.edix.restful.zaps.modelo.dto.UsuarioDTO;
import com.edix.restful.zaps.modelo.entities.Carrito;
import com.edix.restful.zaps.modelo.entities.CarritoProducto;
import com.edix.restful.zaps.modelo.entities.Producto;
import com.edix.restful.zaps.modelo.entities.Usuario;
import com.edix.restful.zaps.restcontroller.CarritoController;
import com.edix.restful.zaps.service.CarritoService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/carritos")
public class CarritoRestControllerImpl implements CarritoController {

    @Autowired
    private CarritoService carritoService;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CarritoDTO> buscarCarritoPorId(@PathVariable("id") int idCarrito) {
        Carrito carrito = carritoService.buscarCarritoPorId(idCarrito);
        if (carrito == null) {
            return ResponseEntity.notFound().build();
        }
        CarritoDTO carritoDTO = convertirACarritoDTO(carrito);
        return ResponseEntity.ok(carritoDTO);
    }
    private CarritoDTO convertirACarritoDTO(Carrito carrito) {
        CarritoDTO carritoDTO = new CarritoDTO();
        carritoDTO.setIdCarrito(carrito.getIdCarrito());
        
        
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        Usuario usuario = carrito.getUsuario();
        usuarioDTO.setIdUsuario(usuario.getIdUsuario());
        usuarioDTO.setUsername(usuario.getUsername());
        
        carritoDTO.setUsuario(usuarioDTO);
        
      
        List<ProductoDTO> productosDTO = new ArrayList<>();
        BigDecimal precioTotal = BigDecimal.ZERO;
        for (CarritoProducto carritoProducto : carrito.getCarritoProducto()) {
            ProductoDTO productoDTO = new ProductoDTO();
            productoDTO.setIdProducto(carritoProducto.getProducto().getIdProducto());
            productoDTO.setNombre(carritoProducto.getProducto().getNombre());
            productoDTO.setCantidad(carritoProducto.getCantidad());
            productoDTO.setPrecio(carritoProducto.getSubtotal());
            
            precioTotal = precioTotal.add(carritoProducto.getProducto().getPrecio().multiply(BigDecimal.valueOf(carritoProducto.getCantidad())));
            
            productosDTO.add(productoDTO);
        }
        
        carritoDTO.setProductos(productosDTO);
        carritoDTO.setPrecioTotal(precioTotal);
        return carritoDTO;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Carrito>> buscarTodosCarritos() {
        List<Carrito> carritos = carritoService.buscarTodosCarritos();
        return ResponseEntity.ok(carritos);
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> modificarCarrito(@RequestBody Carrito carrito) {
        boolean result = carritoService.modificarCarrito(carrito);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> crearCarrito(@RequestBody CrearCarritoDTO crearCarritoDTO) {
    	boolean result = carritoService.crearCarrito(crearCarritoDTO.getIdUsuario());
        if (result) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarrito(@PathVariable int idCarrito) {
        boolean result = carritoService.eliminarCarrito(idCarrito);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
        @PostMapping("/{idCarrito}/{idProducto}/{cantidad}")
        public ResponseEntity<Void> agregarProductoAlCarrito(@PathVariable int idCarrito, @PathVariable int idProducto, @PathVariable int cantidad) {

            boolean result = carritoService.agregarProductoAlCarrito(idCarrito, idProducto, cantidad);
            if (result) {
                return ResponseEntity.status(HttpStatus.CREATED).build();
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
    }

