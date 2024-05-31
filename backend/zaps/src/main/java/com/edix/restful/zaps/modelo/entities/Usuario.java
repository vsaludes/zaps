package com.edix.restful.zaps.modelo.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.edix.restful.zaps.Auth.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

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
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Usuarios", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable, UserDetails {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private int idUsuario;
	@Column(nullable = false)
	private String username;
	private String password;
	private String nombre;
	private String email;
	private String direccion;
	private String telefono;
	
	private boolean enabled; 
	@Enumerated(EnumType.STRING)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	Role role;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_registro")
	private Date fechaRegistro;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ultima_conexion")
	private Date ultimaConexion;
		
	@OneToMany(mappedBy="usuario")
	@JsonIgnore
    private List<Valoracion> valoracion;

	@OneToOne(mappedBy="usuario")
	@JsonIgnore
    private Carrito carrito;

    @OneToMany(mappedBy="usuario")
    @JsonIgnore
    private List<Pedido> pedido;

    @OneToOne(mappedBy="usuario")
    @JsonIgnore
    private ListaDeseo listaDeseo;
    
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority(role.name()));	
		}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public String toString() {
	    return "Usuario{" +
	            "idUsuario=" + idUsuario +
	            ", username='" + username + '\'' +
	            ", password='" + password + '\'' +
	            ", nombre='" + nombre + '\'' +
	            ", email='" + email + '\'' +
	            ", direccion='" + direccion + '\'' +
	            ", telefono='" + telefono + '\'' +
	            ", enabled=" + enabled +
	            ", role=" + role +
	            ", fechaRegistro=" + fechaRegistro +
	            ", ultimaConexion=" + ultimaConexion +
	            '}';
	}


}


