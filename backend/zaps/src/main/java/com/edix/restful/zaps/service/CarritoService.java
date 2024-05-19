package com.edix.restful.zaps.service;


import java.util.List;
import com.edix.restful.zaps.modelo.entities.Carrito;

public interface CarritoService {
    Carrito buscarCarritoPorId(int idCarrito);
    List<Carrito> buscarTodosCarritos();
    boolean modificarCarrito(Carrito carrito);
    boolean crearCarrito(Carrito carrito);
    boolean eliminarCarrito(int idCarrito);
}
