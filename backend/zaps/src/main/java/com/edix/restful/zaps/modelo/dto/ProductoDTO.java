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

    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private double talla;
    private String color;
    private Producto.TipoPisada tipoPisada;
    private Producto.TipoSuperficie tipoSuperficie;
    private Producto.TipoDistancia tipoDistancia;
    private int tipoDrop;
    private Producto.Genero genero;
    private String marca;
    private Producto.Uso uso;
    private int año;
    private int stock;
    private String imagenUrl;
    private boolean disponible;
    
}