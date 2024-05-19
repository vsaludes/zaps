package com.edix.restful.zaps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edix.restful.zaps.modelo.entities.Valoracion;

@Repository
public interface ValoracionRepository extends JpaRepository<Valoracion, Integer> {
}