package com.edix.restful.zaps.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.restful.zaps.modelo.entities.CarritoProducto;
import com.edix.restful.zaps.repository.CarritoProductoRepository;
import com.edix.restful.zaps.service.CarritoProductoService;

@Service
public class CarritoProductoServiceImpl implements CarritoProductoService {

	@Autowired
	private CarritoProductoRepository carritoProductoRepository;

	@Override
    public boolean deleteUltimoProducto(int idProducto) {
        try {
            carritoProductoRepository.deleteByProductoIdProducto(idProducto);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false; 
        }
    }
}