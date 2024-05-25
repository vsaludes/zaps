package com.edix.restful.zaps.service;


import java.util.List;
import com.edix.restful.zaps.modelo.entities.DetallePedido;

public interface DetallePedidoService {

    DetallePedido buscarDetallePedidoPorId(int idDetallePedido);
    List<DetallePedido> buscarTodosDetallesPedido();
    boolean modificarDetallePedido(DetallePedido detallePedido);
    boolean crearDetallePedido(DetallePedido detallePedido);
    boolean eliminarDetallePedido(int idDetallePedido);
}
