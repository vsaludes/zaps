package com.edix.restful.zaps.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edix.restful.zaps.modelo.entities.Producto;
import com.edix.restful.zaps.modelo.entities.Producto.Talla;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query("SELECT p FROM Producto p " +
            "WHERE (:nombre IS NULL OR p.nombre = :nombre) " +
            "AND (:precioMin IS NULL OR p.precio >= :precioMin) " +
            "AND (:precioMax IS NULL OR p.precio <= :precioMax) " +
            "AND (:talla IS NULL OR p.talla = :talla) " +
            "AND (:color IS NULL OR p.color = :color) " +
            "AND (:tipoPisada IS NULL OR p.tipoPisada = :tipoPisada) " +
            "AND (:tipoSuperficie IS NULL OR p.tipoSuperficie = :tipoSuperficie) " +
            "AND (:tipoDistancia IS NULL OR p.tipoDistancia = :tipoDistancia) " +
            "AND (:tipoDrop IS NULL OR p.tipoDrop = :tipoDrop) " +
            "AND (:genero IS NULL OR p.genero = :genero) " +
            "AND (:marca IS NULL OR p.marca = :marca) " +
            "AND (:uso IS NULL OR p.uso = :uso) " +
            "AND (:año IS NULL OR p.año = :año) " +
            "AND (:disponible IS NULL OR p.disponible = :disponible)")
    List<Producto> buscarProductosFiltrados (@Param("nombre") String nombre,
                                             @Param("precioMin") BigDecimal precioMin,
                                             @Param("precioMax") BigDecimal precioMax,
                                             @Param("talla") Talla talla,
                                             @Param("color") String color,
                                             @Param("tipoPisada") Producto.TipoPisada tipoPisada,
                                             @Param("tipoSuperficie") Producto.TipoSuperficie tipoSuperficie,
                                             @Param("tipoDistancia") Producto.TipoDistancia tipoDistancia,
                                             @Param("tipoDrop") int tipoDrop,
                                             @Param("genero") Producto.Genero genero,
                                             @Param("marca") String marca,
                                             @Param("uso") Producto.Uso uso,
                                             @Param("año") int año,
                                             @Param("disponible") boolean disponible);

	List<Producto> findAll(Specification<Producto> specification);
}