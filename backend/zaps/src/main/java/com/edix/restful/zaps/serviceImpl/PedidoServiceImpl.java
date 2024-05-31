package com.edix.restful.zaps.serviceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.restful.zaps.modelo.entities.Carrito;
import com.edix.restful.zaps.modelo.entities.CarritoProducto;
import com.edix.restful.zaps.modelo.entities.DetallePedido;
import com.edix.restful.zaps.modelo.entities.Pedido;
import com.edix.restful.zaps.repository.CarritoRepository;
import com.edix.restful.zaps.repository.DetallePedidoRepository;
import com.edix.restful.zaps.repository.PedidoRepository;
import com.edix.restful.zaps.service.PedidoService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PedidoServiceImpl implements PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
    private CarritoRepository carritoRepository;
    @Autowired
    private DetallePedidoRepository detallePedidoRepository;


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
	public boolean procesarPedido(int idCarrito, BigDecimal precioTotal) {
        try {
            Carrito carrito = carritoRepository.findById(idCarrito).orElse(null);
            if (carrito == null || carrito.getCarritoProducto().isEmpty()) {
                return false;
            }

            Pedido pedido = new Pedido();
            pedido.setFechaPedido(new Date());
            pedido.setEstadoPedido(Pedido.EstadoPedido.pendiente);
            pedido.setUsuario(carrito.getUsuario());
            pedido.setTotal(precioTotal);

            pedidoRepository.save(pedido);

            for (CarritoProducto carritoProducto : carrito.getCarritoProducto()) {
                DetallePedido detalle = new DetallePedido();
                detalle.setPedido(pedido);
                detalle.setProducto(carritoProducto.getProducto());
                detalle.setCantidad(carritoProducto.getCantidad());
                detalle.setSubtotal(carritoProducto.getSubtotal());
                detallePedidoRepository.save(detalle);
            }

            carritoRepository.delete(carrito);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	@Override
	//@Transactional
	public boolean cancelarPedido(int idPedido) {
	    try {
	        Pedido pedido = buscarPedidoPorId(idPedido);
	        if (pedido != null && pedido.getEstadoPedido() == Pedido.EstadoPedido.pendiente) {
	            pedido.setEstadoPedido(Pedido.EstadoPedido.cancelado);
	            pedidoRepository.save(pedido);
	            return true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	@Override
	//@Transactional(readOnly=true)
	public boolean marcarPedidoEntregado(int idPedido) {
	    try {
	        Pedido pedido = buscarPedidoPorId(idPedido);
	        if (pedido != null) {
	            pedido.setEstadoPedido(Pedido.EstadoPedido.entregado);
	            pedidoRepository.save(pedido);
	            return true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	@Override
	public List<Pedido> buscarTodos() {
	    try {
	        return pedidoRepository.findAll();
	    } catch (Exception e) {
	        
	        e.printStackTrace(); 
	        return new ArrayList<>(); 
	    }
}
	@Override
	//@Transactional
	public boolean eliminarPedidoPorId(int idPedido) {
	    try {
	        Pedido pedido = buscarPedidoPorId(idPedido);
	        if (pedido != null) {
	            pedidoRepository.delete(pedido);
	            return true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}
}