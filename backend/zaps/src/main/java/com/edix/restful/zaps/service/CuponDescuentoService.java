package com.edix.restful.zaps.service;


import java.util.List;
import com.edix.restful.zaps.modelo.entities.CuponDescuento;

public interface CuponDescuentoService {
    CuponDescuento buscarCuponPorId(int idCupon);
    List<CuponDescuento> buscarTodosCupones();
    boolean modificarCupon(CuponDescuento cupon);
    boolean crearCupon(CuponDescuento cupon);
    boolean eliminarCupon(int idCupon);
}
