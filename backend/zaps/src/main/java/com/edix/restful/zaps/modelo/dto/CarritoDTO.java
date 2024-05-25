package com.edix.restful.zaps.modelo.dto;

import java.util.List;
import lombok.Data;

@Data
public class CarritoDTO {
	
	    private int idCarrito;
	    private List<ProductoDTO> productos;
	    private UsuarioDTO usuario;
	    
}