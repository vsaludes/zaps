package com.edix.restful.zaps.serviceImpl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.edix.restful.zaps.modelo.entities.Carrito;
import com.edix.restful.zaps.repository.CarritoRepository;
import com.edix.restful.zaps.service.CarritoService;

import java.util.List;

@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Override
    public Carrito buscarCarritoPorId(int idCarrito) {
        return carritoRepository.findById(idCarrito).orElse(null);
    }

    @Override
    public List<Carrito> buscarTodosCarritos() {
        return carritoRepository.findAll();
    }

    @Override
    public boolean modificarCarrito(Carrito carrito) {
        if (carritoRepository.existsById(carrito.getIdCarrito())) {
            carritoRepository.save(carrito);
            return true;
        }
        return false;
    }

    @Override
    public boolean crearCarrito(Carrito carrito) {
        carritoRepository.save(carrito);
        return true;
    }

    @Override
    public boolean eliminarCarrito(int idCarrito) {
        if (carritoRepository.existsById(idCarrito)) {
            carritoRepository.deleteById(idCarrito);
            return true;
        }
        return false;
    }
}
