package com.edix.restful.zaps.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.edix.restful.zaps.modelo.entities.Devolucion;
import com.edix.restful.zaps.repository.DevolucionRepository;
import com.edix.restful.zaps.service.DevolucionService;

import java.util.List;

@Service
public class DevolucionServiceImpl implements DevolucionService {

    @Autowired
    private DevolucionRepository devolucionRepository;

    @Override
    public Devolucion buscarDevolucionPorId(int idDevolucion) {
        return devolucionRepository.findById(idDevolucion).orElse(null);
    }

    @Override
    public List<Devolucion> buscarTodasDevoluciones() {
        return devolucionRepository.findAll();
    }

    @Override
    public boolean modificarDevolucion(Devolucion devolucion) {
        if (devolucionRepository.existsById(devolucion.getIdDevolucion())) {
            devolucionRepository.save(devolucion);
            return true;
        }
        return false;
    }

    @Override
    public boolean crearDevolucion(Devolucion devolucion) {
        devolucionRepository.save(devolucion);
        return true;
    }

    @Override
    public boolean eliminarDevolucion(int idDevolucion) {
        if (devolucionRepository.existsById(idDevolucion)) {
            devolucionRepository.deleteById(idDevolucion);
            return true;
        }
        return false;
    }
}
