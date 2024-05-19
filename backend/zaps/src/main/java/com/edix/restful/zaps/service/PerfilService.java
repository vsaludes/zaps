package com.edix.restful.zaps.service;


import java.util.List;
import com.edix.restful.zaps.modelo.entities.Perfil;

public interface PerfilService {
    Perfil buscarPerfilPorId(int idPerfil);
    List<Perfil> buscarTodosPerfiles();
    boolean modificarPerfil(Perfil perfil);
    boolean crearPerfil(Perfil perfil);
    boolean eliminarPerfil(int idPerfil);
}
