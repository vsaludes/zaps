package com.edix.restful.zaps.modelo.dto;

import java.math.BigDecimal;

import com.edix.restful.zaps.modelo.entities.Producto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

	private int idProducto;
	private String nombre;
    private int cantidad;
    private BigDecimal precio;

    public BigDecimal calcularPrecioTotal() {
        return precio.multiply(BigDecimal.valueOf(cantidad));
    }
}