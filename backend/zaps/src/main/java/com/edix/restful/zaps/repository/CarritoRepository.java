package com.edix.restful.zaps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edix.restful.zaps.modelo.entities.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
}
