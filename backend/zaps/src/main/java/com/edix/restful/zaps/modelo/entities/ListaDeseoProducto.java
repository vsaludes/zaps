package com.edix.restful.zaps.modelo.entities;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "lista_deseos_productos")
public class ListaDeseoProducto implements Serializable {
	    private static final long serialVersionUID = 1L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private int id;

	    @ManyToOne
	    @JoinColumn(name = "id_deseos")
	    @JsonIgnore
	    private ListaDeseo listaDeseo;

	    @ManyToOne
	    @JoinColumn(name = "id_producto")
	    private Producto producto;

	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "fecha_agregado")
	    private Date fechaAgregado;

	    @Column(name = "notificar")
	    private boolean notificar;
	}


