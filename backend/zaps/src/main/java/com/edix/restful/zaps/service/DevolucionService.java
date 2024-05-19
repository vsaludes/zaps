package com.edix.restful.zaps.service;


import java.util.List;
import com.edix.restful.zaps.modelo.entities.Devolucion;

public interface DevolucionService {
    Devolucion buscarDevolucionPorId(int idDevolucion);
    List<Devolucion> buscarTodasDevoluciones();
    boolean modificarDevolucion(Devolucion devolucion);
    boolean crearDevolucion(Devolucion devolucion);
    boolean eliminarDevolucion(int idDevolucion);
}
