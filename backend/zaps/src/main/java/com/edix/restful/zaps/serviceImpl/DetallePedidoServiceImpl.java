package com.edix.restful.zaps.serviceImpl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.edix.restful.zaps.modelo.entities.DetallePedido;
import com.edix.restful.zaps.repository.DetallePedidoRepository;
import com.edix.restful.zaps.service.DetallePedidoService;

import java.util.List;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Override
    public DetallePedido buscarDetallePedidoPorId(int idDetallePedido) {
        return detallePedidoRepository.findById(idDetallePedido).orElse(null);
    }

    @Override
    public List<DetallePedido> buscarTodosDetallesPedido() {
        return detallePedidoRepository.findAll();
    }

    @Override
    public boolean modificarDetallePedido(DetallePedido detallePedido) {
        if (detallePedidoRepository.existsById(detallePedido.getIdDetallePedido())) {
            detallePedidoRepository.save(detallePedido);
            return true;
        }
        return false;
    }

    @Override
    public boolean crearDetallePedido(DetallePedido detallePedido) {
        detallePedidoRepository.save(detallePedido);
        return true;
    }

    @Override
    public boolean eliminarDetallePedido(int idDetallePedido) {
        if (detallePedidoRepository.existsById(idDetallePedido)) {
            detallePedidoRepository.deleteById(idDetallePedido);
            return true;
        }
        return false;
    }
}
