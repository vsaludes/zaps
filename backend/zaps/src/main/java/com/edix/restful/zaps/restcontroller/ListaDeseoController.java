package com.edix.restful.zaps.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.edix.restful.zaps.modelo.entities.ListaDeseo;
import com.edix.restful.zaps.modelo.entities.Producto;

import java.util.List;

public interface ListaDeseoController {

	ResponseEntity<Void> agregarProducto(int idListaDeseo, int idProducto);

    ResponseEntity<Void> eliminarProducto(int idListaDeseo, int idProducto);

    ResponseEntity<List<Producto>> obtenerProductos(int idListaDeseo);
}
	
	
	
    
	/*
	ResponseEntity<ListaDeseo> buscarListaDeseoPorId(@PathVariable int id);

    ResponseEntity<List<ListaDeseo>> buscarTodosListasDeseo();

    ResponseEntity<Void> modificarListaDeseo(@RequestBody ListaDeseo listaDeseo);

    ResponseEntity<Void> crearListaDeseo(@RequestBody ListaDeseo listaDeseo);

    ResponseEntity<Void> eliminarListaDeseo(@PathVariable int id);
}
*/