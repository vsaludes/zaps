package com.edix.restful.zaps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edix.restful.zaps.modelo.entities.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer> {

	@Query("select p from Pedido p where p.usuario.idUsuario = :idUsuario")
	List<Pedido> findOrderByUser(@Param("idUsuario") int idUsuario);

	List<Pedido> findByUsuarioIdUsuario(int idUsuario);
}
