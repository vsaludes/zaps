package com.edix.restful.zaps.service;

import java.math.BigDecimal;
import java.util.List;



import com.edix.restful.zaps.modelo.entities.Pedido;


public interface PedidoService {
	
	Pedido buscarPedidoPorId (int idPedido);
	List<Pedido> buscarPedidoPorUsuario (int idUsuario);
	List<Pedido> buscarTodos();
	boolean eliminarPedidoPorId(int idPedido);
	boolean cancelarPedido(int idPedido);
	boolean marcarPedidoEntregado(int idPedido);
	boolean procesarPedido(int idCarrito, BigDecimal precioTotal);

}
