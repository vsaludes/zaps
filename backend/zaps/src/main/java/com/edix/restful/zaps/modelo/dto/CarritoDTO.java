package com.edix.restful.zaps.modelo.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class CarritoDTO {
	
	    private int idCarrito;
	    private List<ProductoDTO> productos;
	    private UsuarioDTO usuario;
	    private BigDecimal precioTotal;
	    
	    public BigDecimal calcularPrecioTotal() {
	        BigDecimal precioTotal = BigDecimal.ZERO;
	        for (ProductoDTO producto : productos) {
	            precioTotal = precioTotal.add(producto.calcularPrecioTotal());
	        }
	        return precioTotal;
	    }
	    public BigDecimal getPrecioTotal() {
	        return precioTotal;
	    }
	    public void setPrecioTotal(BigDecimal precioTotal) {
	        this.precioTotal = precioTotal;
	    }
	    
}