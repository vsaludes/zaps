package com.edix.restful.zaps.modelo.entities;

import java.io.Serializable;

import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Devoluciones")
@NamedQuery(name="Devolucion.findAll", query="SELECT d FROM Devolucion d")
public class Devolucion implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_devolucion")
	private int idDevolucion;
	
	@ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;
	
	@ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;
	
	@Column(name="cantidad_devuelta")
	private int cantidadDevuelta;
	
	private String motivo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_devolucion")
	private Date fechaDevolucion;
}
