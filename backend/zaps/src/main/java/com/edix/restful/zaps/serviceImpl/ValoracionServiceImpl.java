package com.edix.restful.zaps.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.edix.restful.zaps.modelo.entities.Valoracion;
import com.edix.restful.zaps.repository.ValoracionRepository;
import com.edix.restful.zaps.service.ValoracionService;

import java.util.List;

@Service
public class ValoracionServiceImpl implements ValoracionService {

    @Autowired
    private ValoracionRepository valoracionRepository;

    @Override
    public Valoracion buscarValoracionPorId(int idValoracion) {
        return valoracionRepository.findById(idValoracion).orElse(null);
    }

    @Override
    public List<Valoracion> buscarTodasValoraciones() {
        return valoracionRepository.findAll();
    }

    @Override
    public boolean modificarValoracion(Valoracion valoracion) {
        if (valoracionRepository.existsById(valoracion.getIdValoracion())) {
            valoracionRepository.save(valoracion);
            return true;
        }
        return false;
    }

    @Override
    public boolean crearValoracion(Valoracion valoracion) {
        valoracionRepository.save(valoracion);
        return true;
    }

    @Override
    public boolean eliminarValoracion(int idValoracion) {
        if (valoracionRepository.existsById(idValoracion)) {
            valoracionRepository.deleteById(idValoracion);
            return true;
        }
        return false;
    }
}
