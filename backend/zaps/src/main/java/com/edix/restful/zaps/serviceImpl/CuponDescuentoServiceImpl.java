package com.edix.restful.zaps.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.edix.restful.zaps.modelo.entities.CuponDescuento;
import com.edix.restful.zaps.repository.CuponDescuentoRepository;
import com.edix.restful.zaps.service.CuponDescuentoService;

import java.util.List;

@Service
public class CuponDescuentoServiceImpl implements CuponDescuentoService {

    @Autowired
    private CuponDescuentoRepository cuponDescuentoRepository;

    @Override
    public CuponDescuento buscarCuponPorId(int idCupon) {
        return cuponDescuentoRepository.findById(idCupon).orElse(null);
    }

    @Override
    public List<CuponDescuento> buscarTodosCupones() {
        return cuponDescuentoRepository.findAll();
    }

    @Override
    public boolean modificarCupon(CuponDescuento cupon) {
        if (cuponDescuentoRepository.existsById(cupon.getIdCupon())) {
            cuponDescuentoRepository.save(cupon);
            return true;
        }
        return false;
    }

    @Override
    public boolean crearCupon(CuponDescuento cupon) {
        cuponDescuentoRepository.save(cupon);
        return true;
    }

    @Override
    public boolean eliminarCupon(int idCupon) {
        if (cuponDescuentoRepository.existsById(idCupon)) {
            cuponDescuentoRepository.deleteById(idCupon);
            return true;
        }
        return false;
    }
}
