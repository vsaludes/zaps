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
@Table(name="Valoraciones")
@NamedQuery(name="Valoracion.findAll", query="SELECT v FROM Valoracion v")
public class Valoracion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_valoracion")
    private int idValoracion;

    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name="id_producto")
    private Producto producto;

    private String comentario;
    private int valoracion;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

}
