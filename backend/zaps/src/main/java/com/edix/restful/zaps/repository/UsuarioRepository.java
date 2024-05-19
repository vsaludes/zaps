package com.edix.restful.zaps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edix.restful.zaps.modelo.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{


Usuario findByUsername(String username);
Usuario findByEmail(String email);
}