package com.edix.restful.zaps.service;


import java.math.BigDecimal;
import java.util.List;

import com.edix.restful.zaps.modelo.dto.CarritoDTO;
import com.edix.restful.zaps.modelo.dto.CrearCarritoDTO;
import com.edix.restful.zaps.modelo.dto.ProductoDTO;
import com.edix.restful.zaps.modelo.entities.Carrito;
import com.edix.restful.zaps.modelo.entities.Producto;

public interface CarritoService {
    Carrito buscarCarritoPorId(int idCarrito);
    List<Carrito> buscarTodosCarritos();
    boolean modificarCarrito(Carrito carrito);
    boolean crearCarrito(Carrito carrito);
    boolean eliminarCarrito(int idCarrito);
    //boolean agregarProductoAlCarrito(int idCarrito, Producto producto, int cantidad);
    boolean eliminarProductoDelCarrito(int idCarrito, int idProducto, int cantidad);
    BigDecimal calcularTotalCarrito(int idCarrito);
	boolean agregarProductoAlCarrito (int idCarrito, int idProducto, int cantidad);
	Carrito buscarCarritoPorIdUsuario (int idUsuario);
}
