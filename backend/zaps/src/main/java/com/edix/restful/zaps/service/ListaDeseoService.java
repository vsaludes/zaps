package com.edix.restful.zaps.service;



import java.util.List;
import com.edix.restful.zaps.modelo.entities.ListaDeseo;
import com.edix.restful.zaps.modelo.entities.Producto;

public interface ListaDeseoService {



	    boolean agregarProductoAListaDeseos(int idListaDeseo, int idProducto);

	    boolean eliminarProductoDeListaDeseos(int idListaDeseo, int idProducto);

	    List<Producto> obtenerProductosListaDeseos(int idListaDeseo);
	    
	    boolean crearListaDeseo(ListaDeseo listaDeseo);

		boolean notificacionProducto(int idListaDeseo, int idProducto, boolean notificar);
	}
	
	
	
	/*
    ListaDeseo buscarListaDeseoPorId(int idListaDeseo);
    List<ListaDeseo> buscarTodosListasDeseo();
    boolean modificarListaDeseo(ListaDeseo listaDeseo);
    boolean crearListaDeseo(ListaDeseo listaDeseo);
    boolean eliminarListaDeseo(int idListaDeseo);
    boolean agregarProductoAListaDeseos(int idListaDeseo, Producto producto);
    boolean eliminarProductoDeListaDeseos(int idListaDeseo, Producto producto);
    List<Producto> obtenerProductosListaDeseos(int idListaDeseo);
} */
