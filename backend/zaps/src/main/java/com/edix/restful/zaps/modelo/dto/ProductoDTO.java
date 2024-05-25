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
    private int cantidad;
}