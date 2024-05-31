package com.edix.restful.zaps.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edix.restful.zaps.modelo.entities.ListaDeseoProducto;

public interface ListaDeseoProductoRepository extends JpaRepository<ListaDeseoProducto, Integer> {

	@Query("SELECT ld FROM ListaDeseoProducto ld WHERE ld.listaDeseo.idDeseos = :idListaDeseo AND ld.producto.idProducto = :idProducto")
	ListaDeseoProducto findByListaDeseoIdAndProductoId(int idListaDeseo, int idProducto);

	@Query("SELECT ldp FROM ListaDeseoProducto ldp WHERE ldp.listaDeseo.idDeseos = :idListaDeseo")
	List<ListaDeseoProducto> findByListaDeseoId(int idListaDeseo);

	@Query("DELETE FROM ListaDeseoProducto ld WHERE ld.listaDeseo.idDeseos = :idListaDeseo AND ld.producto.idProducto = :idProducto")
	void deleteByListaDeseoIdAndProductoId(int idListaDeseo, int idProducto);
}