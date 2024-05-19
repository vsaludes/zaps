package com.edix.restful.zaps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edix.restful.zaps.modelo.entities.ListaDeseo;

@Repository
public interface ListaDeseoRepository extends JpaRepository<ListaDeseo, Integer> {
}