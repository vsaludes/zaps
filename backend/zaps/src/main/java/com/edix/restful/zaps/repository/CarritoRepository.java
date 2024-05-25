package com.edix.restful.zaps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edix.restful.zaps.modelo.entities.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
	
	@Query("SELECT c FROM Carrito c WHERE c.usuario.idUsuario = :idUsuario")
	Carrito findByUsuarioId(int idUsuario);
}
