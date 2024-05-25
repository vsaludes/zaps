package com.edix.restful.zaps.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.edix.restful.zaps.modelo.entities.Perfil;
import com.edix.restful.zaps.repository.PerfilRepository;
import com.edix.restful.zaps.service.PerfilService;

import java.util.List;

@Service
public class PerfilServiceImpl implements PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    @Override
    public Perfil buscarPerfilPorId(int idPerfil) {
        return perfilRepository.findById(idPerfil).orElse(null);
    }

    @Override
    public List<Perfil> buscarTodosPerfiles() {
        return perfilRepository.findAll();
    }

    @Override
    public boolean modificarPerfil(Perfil perfil) {
        if (perfilRepository.existsById(perfil.getIdPerfil())) {
            perfilRepository.save(perfil);
            return true;
        }
        return false;
    }

    @Override
    public boolean crearPerfil(Perfil perfil) {
        perfilRepository.save(perfil);
        return true;
    }

    @Override
    public boolean eliminarPerfil(int idPerfil) {
        if (perfilRepository.existsById(idPerfil)) {
            perfilRepository.deleteById(idPerfil);
            return true;
        }
        return false;
    }
}
