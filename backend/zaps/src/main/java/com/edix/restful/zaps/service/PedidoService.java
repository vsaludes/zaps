package com.edix.restful.zaps.service;

import java.util.List;



import com.edix.restful.zaps.modelo.entities.Pedido;


public interface PedidoService {
	
	Pedido buscarPedidoPorId (int idPedido);
	List<Pedido> buscarPedidoPorUsuario (int idUsuario);
	//boolean borrarPedido (int idPedido);
	boolean procesarPedido(int idPedido);
	boolean cancelarPedido(int idPedido);
	boolean marcarPedidoEntregado(int idPedido);

}
