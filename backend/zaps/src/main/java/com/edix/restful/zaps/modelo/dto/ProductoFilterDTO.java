package com.edix.restful.zaps.modelo.dto;

import java.math.BigDecimal;


import org.springframework.data.jpa.domain.Specification;

import com.edix.restful.zaps.modelo.entities.Producto;
import com.edix.restful.zaps.modelo.entities.Producto.Genero;
import com.edix.restful.zaps.modelo.entities.Producto.TipoDistancia;
import com.edix.restful.zaps.modelo.entities.Producto.TipoPisada;
import com.edix.restful.zaps.modelo.entities.Producto.TipoSuperficie;
import com.edix.restful.zaps.modelo.entities.Producto.Uso;

import jakarta.persistence.criteria.Predicate;
import lombok.Data;

@Data
public class ProductoFilterDTO {
	
	private BigDecimal precioMinimo;
	private BigDecimal precioMaximo;
	private TipoPisada tipoPisada;
	private TipoSuperficie tipoSuperficie;
	private TipoDistancia tipoDistancia;
	private Genero genero;
	private Uso uso;
	
	
	public Specification<Producto> obtainFilterSpecification() {
		return (root, query, cb) -> {
			
			Predicate predicate = cb.and();
			
			if (this.precioMinimo != null && this.precioMaximo != null) {
                predicate = cb.and(
                    predicate,
                    cb.between(root.get("precio"), this.precioMinimo, this.precioMaximo)
                );
            }

	            if (this.tipoPisada != null) {
	                predicate = cb.and(predicate, cb.equal(root.get("tipoPisada"), this.tipoPisada));
	            }

	            if (this.tipoSuperficie != null) {
	                predicate = cb.and(predicate, cb.equal(root.get("tipoSuperficie"), this.tipoSuperficie));
	            }

	            if (this.tipoDistancia != null) {
	                predicate = cb.and(predicate, cb.equal(root.get("tipoDistancia"), this.tipoDistancia));
	            }

	            if (this.genero != null) {
	                predicate = cb.and(predicate, cb.equal(root.get("genero"), this.genero));
	            }

	            if (this.uso != null) {
	                predicate = cb.and(predicate, cb.equal(root.get("uso"), this.uso));
	            }

	            return predicate;
	        };
	    }
	}

