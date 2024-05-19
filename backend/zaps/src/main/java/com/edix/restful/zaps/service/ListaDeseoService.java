package com.edix.restful.zaps.service;



import java.util.List;
import com.edix.restful.zaps.modelo.entities.ListaDeseo;

public interface ListaDeseoService {

    ListaDeseo buscarListaDeseoPorId(int idListaDeseo);
    List<ListaDeseo> buscarTodosListasDeseo();
    boolean modificarListaDeseo(ListaDeseo listaDeseo);
    boolean crearListaDeseo(ListaDeseo listaDeseo);
    boolean eliminarListaDeseo(int idListaDeseo);
}
