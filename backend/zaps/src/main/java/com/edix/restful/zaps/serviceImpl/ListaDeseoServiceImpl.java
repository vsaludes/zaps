package com.edix.restful.zaps.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.edix.restful.zaps.modelo.entities.ListaDeseo;
import com.edix.restful.zaps.repository.ListaDeseoRepository;
import com.edix.restful.zaps.service.ListaDeseoService;

import java.util.List;

@Service
public class ListaDeseoServiceImpl implements ListaDeseoService {

    @Autowired
    private ListaDeseoRepository listaDeseoRepository;

    @Override
    public ListaDeseo buscarListaDeseoPorId(int idListaDeseo) {
        return listaDeseoRepository.findById(idListaDeseo).orElse(null);
    }

    @Override
    public List<ListaDeseo> buscarTodosListasDeseo() {
        return listaDeseoRepository.findAll();
    }

    @Override
    public boolean modificarListaDeseo(ListaDeseo listaDeseo) {
        if (listaDeseoRepository.existsById(listaDeseo.getIdDeseos())) {
            listaDeseoRepository.save(listaDeseo);
            return true;
        }
        return false;
    }

    @Override
    public boolean crearListaDeseo(ListaDeseo listaDeseo) {
        listaDeseoRepository.save(listaDeseo);
        return true;
    }

    @Override
    public boolean eliminarListaDeseo(int idListaDeseo) {
        if (listaDeseoRepository.existsById(idListaDeseo)) {
            listaDeseoRepository.deleteById(idListaDeseo);
            return true;
        }
        return false;
    }
}
