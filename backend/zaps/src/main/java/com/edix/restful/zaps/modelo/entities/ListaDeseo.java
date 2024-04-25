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
@Table(name="Lista_Deseos")
@NamedQuery(name="ListaDeseo.findAll", query="SELECT l FROM ListaDeseo l")
public class ListaDeseo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_deseos")
    private int idDeseos;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToMany
    @JoinTable(name = "Lista_Deseos",
            joinColumns = @JoinColumn(name = "id_deseos"),
            inverseJoinColumns = @JoinColumn(name = "id_producto"))
    private List<Producto> producto;

    @Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_agregado")
	private Date fechaAgregado;
    
    private boolean notificar;

}