package com.edix.restful.zaps.service;

import java.util.List;

import com.edix.restful.zaps.modelo.entities.Valoracion;

public interface ValoracionService {
    Valoracion buscarValoracionPorId(int idValoracion);
    List<Valoracion> buscarTodasValoraciones();
    boolean modificarValoracion(Valoracion valoracion);
    boolean crearValoracion(Valoracion valoracion);
    boolean eliminarValoracion(int idValoracion);
}