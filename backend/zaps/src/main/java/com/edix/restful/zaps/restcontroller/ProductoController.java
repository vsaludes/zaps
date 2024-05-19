package com.edix.restful.zaps.restcontroller;

import org.springframework.http.ResponseEntity;
import com.edix.restful.zaps.modelo.dto.ProductoDTO;
import com.edix.restful.zaps.modelo.entities.Producto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductoController {

    ResponseEntity<List<Producto>> verTodosProductos();

    ResponseEntity<Producto> verUnProducto(int idProducto);

    ResponseEntity<Producto> altaProducto(ProductoDTO productoDto);

    ResponseEntity<Producto> modificacionProducto(int idProducto, ProductoDTO productoDto);

    ResponseEntity<String> eliminar(int idProducto);
    
    Page<Producto> verTodosPaginados(Pageable pageable);
}