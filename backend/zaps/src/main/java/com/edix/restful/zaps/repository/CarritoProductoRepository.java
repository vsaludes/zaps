package com.edix.restful.zaps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.edix.restful.zaps.modelo.entities.CarritoProducto;


@Repository
public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, Integer> {

	//@Query("DELETE FROM CarritoProducto cp WHERE cp.producto.idProducto = :idProducto")
	//void deleteUltimoProducto (@Param("idProducto") int idProducto);
	void deleteByProductoIdProducto(int idProducto);
}
