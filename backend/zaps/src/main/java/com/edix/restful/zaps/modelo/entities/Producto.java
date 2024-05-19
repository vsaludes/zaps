package com.edix.restful.zaps.modelo.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name="Productos")
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_producto")
	private int idProducto;
	
	private String nombre;
	private String descripcion;
	private BigDecimal precio;
	private double talla;
	private String color;
	
	@Enumerated(EnumType.STRING)
    @Column(name="tipo_pisada")
    private TipoPisada tipoPisada;
	
	@Enumerated(EnumType.STRING)
    @Column(name="tipo_superficie")
    private TipoSuperficie tipoSuperficie;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_distancia")
	private TipoDistancia tipoDistancia;
	
	@Column(name="tipo_drop")
	private int tipoDrop;
	
	@Enumerated(EnumType.STRING)
    private Genero genero;
	
	private String marca;
	
	@Enumerated(EnumType.STRING)
    private Uso uso;
	
	private int año;
	private int stock;
	
	@Column(name="imagen_url")
	private String imagenUrl;
	
	private boolean disponible;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;
	
	@OneToMany(mappedBy="producto")
	private List<Valoracion> valoracion;
	
	//@ManyToMany(mappedBy = "producto")
	//private List<ListaDeseo> listaDeseo;
	@ManyToMany
	@JoinTable(name = "Lista_Deseos",
	        joinColumns = @JoinColumn(name = "id_producto"),
	        inverseJoinColumns = @JoinColumn(name = "id_deseos"))
	private List<ListaDeseo> listaDeseo;

	@ManyToOne
	@JoinColumn(name = "id_carrito")
	private Carrito carrito;
	
	@OneToMany(mappedBy = "producto")
    private List<DetallePedido> detallePedido;
    
    @OneToMany(mappedBy = "producto")
    private List<Devolucion> devolucion;
	
	public enum TipoPisada {
	    PRONADOR,
	    SUPINADOR,
	    NEUTRO
	}

	public enum TipoSuperficie {
	    RUNNING,
	    TRAIL,
	    PISTA
	}

	public enum TipoDistancia {
	    CORTA,
	    MEDIA_MARATON,
	    MARATON,
	    ULTRA
	}

	public enum Genero {
	    MUJER,
	    HOMBRE,
	    UNISEX,
	    JUNIOR
	}

	public enum Uso {
	    ENTRENAMIENTO,
	    COMPETICION,
	    MIXTA
	}
	
}
