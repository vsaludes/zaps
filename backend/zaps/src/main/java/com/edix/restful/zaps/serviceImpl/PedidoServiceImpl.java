package com.edix.restful.zaps.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.restful.zaps.modelo.entities.Pedido;

import com.edix.restful.zaps.repository.PedidoRepository;
import com.edix.restful.zaps.service.PedidoService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PedidoServiceImpl implements PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@Override
	@Transactional(readOnly=true)
	public Pedido buscarPedidoPorId(int idPedido) {
		try {
			return pedidoRepository.findById(idPedido).orElse(null); }
		catch (Exception e) {
			e.printStackTrace();
			return null; }
	}

	@Override
	@Transactional(readOnly=true)
	public List<Pedido> buscarPedidoPorUsuario(int idUsuario) {
		try {
			return pedidoRepository.findOrderByUser(idUsuario);
		}  catch (Exception e) {
			e.printStackTrace();
	        return null; }
	}
		
	@Override
	public boolean procesarPedido(int idPedido) {
	    try {
	        Pedido pedido = buscarPedidoPorId(idPedido);
	        if (pedido != null && pedido.getEstadoPedido() == Pedido.EstadoPedido.PENDIENTE) {
	            pedido.setEstadoPedido(Pedido.EstadoPedido.ENVIADO);
	            pedidoRepository.save(pedido);
	            return true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	@Override
	@Transactional
	public boolean cancelarPedido(int idPedido) {
	    try {
	        Pedido pedido = buscarPedidoPorId(idPedido);
	        if (pedido != null && pedido.getEstadoPedido() == Pedido.EstadoPedido.PENDIENTE) {
	            pedido.setEstadoPedido(Pedido.EstadoPedido.CANCELADO);
	            pedidoRepository.save(pedido);
	            return true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	@Override
	@Transactional(readOnly=true)
	public boolean marcarPedidoEntregado(int idPedido) {
	    try {
	        Pedido pedido = buscarPedidoPorId(idPedido);
	        if (pedido != null) {
	            pedido.setEstadoPedido(Pedido.EstadoPedido.ENTREGADO);
	            pedidoRepository.save(pedido);
	            return true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}
}