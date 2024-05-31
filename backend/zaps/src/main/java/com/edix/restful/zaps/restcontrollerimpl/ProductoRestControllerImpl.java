package com.edix.restful.zaps.restcontrollerimpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.edix.restful.zaps.modelo.dto.ProductoDTO;
import com.edix.restful.zaps.modelo.dto.ProductoFilterDTO;
import com.edix.restful.zaps.modelo.entities.Producto;
import com.edix.restful.zaps.restcontroller.ProductoController;
import com.edix.restful.zaps.service.ProductoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/productos")
public class ProductoRestControllerImpl implements ProductoController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);
	
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @GetMapping
    public ResponseEntity<List<Producto>> verTodosProductos() {
        List<Producto> productos = productoService.buscarTodosProductos();
        return ResponseEntity.ok().body(productos);      
    }
    
    @Override
    @GetMapping("/{idProducto}")
    public ResponseEntity<Producto> verUnProducto(@PathVariable int idProducto) {
        Producto producto = productoService.buscarProductoId(idProducto);
        if (producto != null) {
            return ResponseEntity.ok().body(producto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @Override
    @PostMapping
    public ResponseEntity<Producto> altaProducto(@RequestBody ProductoDTO productoDto) {
        Producto producto = modelMapper.map(productoDto, Producto.class);
        Producto productoDadoAlta = productoService.altaProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoDadoAlta);
    }
    
    @Override
    @PutMapping("/{idProducto}")
    public ResponseEntity<Producto> modificacionProducto(@PathVariable int idProducto, @RequestBody ProductoDTO productoDto) {
        Producto productoExistente = productoService.buscarProductoId(idProducto);
        if (productoExistente != null) {
            Producto productoActualizado = modelMapper.map(productoDto, Producto.class);
            productoActualizado.setIdProducto(idProducto);
            Producto productoModificado = productoService.modificarProducto(productoActualizado);
            return ResponseEntity.ok().body(productoModificado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @Override
    @DeleteMapping("/{idProducto}")
    public ResponseEntity<String> eliminar(@PathVariable int idProducto) {
        if (productoService.eliminarProducto(idProducto)) {
            return ResponseEntity.ok("Producto eliminado correctamente");
        } else {
            return ResponseEntity.badRequest().body("No se pudo eliminar el producto");
        }
    }

    @Override
    @GetMapping("/page")
    public Page<Producto> verTodosPaginados(Pageable pageable) {
        logger.info("Entrando al método readAll con paginación");
        Page<Producto> productosPage = productoService.findAll(pageable);
        logger.info("Saliendo del método readAll con {} usuarios", productosPage.getTotalElements());
        return productosPage;
	}
    
    @GetMapping("/recomendador")
    public ResponseEntity<List<Producto>> recomendador(@ModelAttribute ProductoFilterDTO filtro) {

    	List<Producto> productosRec = productoService.buscarProductosRecomendador(filtro);
    	return ResponseEntity.ok(productosRec);
    }
    
}

