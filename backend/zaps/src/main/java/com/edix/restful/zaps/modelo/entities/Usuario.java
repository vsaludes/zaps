package com.edix.restful.zaps.modelo.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name="Usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private int idUsuario;
	private String username;
	private String password;
	private String nombre;
	private String email;
	private String direccion;
	private String telefono;
	private boolean verificado;
	private boolean enabled;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_registro")
	private Date fechaRegistro;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ultima_conexion")
	private Date ultimaConexion;
		
	@OneToMany(mappedBy="usuario")
    private List<Valoracion> valoracion;

	@OneToOne(mappedBy="usuario")
    private Carrito carrito;

    @OneToMany(mappedBy="usuario")
    private List<Pedido> pedido;

    @OneToMany(mappedBy="usuario")
    private List<ListaDeseo> listaDeseo;
    
	//uni-directional many-to-many association to Perfil
	@ManyToMany
	@JoinTable(
		name="Usuario_Perfiles"
		, joinColumns={
			@JoinColumn(name="id_usuario")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_perfil")
			}
	)
	private List<Perfil> perfiles;

}


