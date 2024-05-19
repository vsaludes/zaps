package com.edix.restful.zaps.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.edix.restful.zaps.modelo.entities.Producto;

public interface ProductoService {
	
	List<Producto> buscarTodosProductos();
	Producto buscarProductoId(int idProducto);
	Producto altaProducto(Producto producto);
	Producto modificarProducto(Producto producto);
	boolean eliminarProducto(int idProducto);
	List<Producto> buscarPorFiltros(String nombre, BigDecimal precioMin, BigDecimal precioMax, double talla, String color,
			Producto.TipoPisada tipoPisada, Producto.TipoSuperficie tipoSuperficie, Producto.TipoDistancia tipoDistancia, int tipoDrop,
            Producto.Genero genero, String marca, Producto.Uso uso, int año, boolean disponible);
	Page<Producto> findAll(Pageable pageable);
}


