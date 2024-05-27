package com.edix.restful.zaps.modelo.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
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
@Table(name="Pedidos")
@NamedQuery(name="Pedido.findAll", query="SELECT p FROM Pedido p")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pedido")
	private int idPedido;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_pedido")
	private Date fechaPedido;
	
	private BigDecimal total;
	
	@Enumerated(EnumType.STRING)
    @Column(name="estado_pedido")
    private EstadoPedido estadoPedido;
	
	@ManyToOne
    @JoinColumn(name="id_usuario")
	@JsonIgnore
    private Usuario usuario;
	
	@OneToMany(mappedBy = "pedido")
	@JsonIgnore
    private List<Devolucion> devolucion;
	
	@OneToMany(mappedBy = "pedido")
	@JsonIgnore
	private List<DetallePedido> detallePedido;
	
	public enum EstadoPedido {
	    pendiente,
	    enviado,
	    entregado,
	    cancelado
	}
	
}