package com.edix.restful.zaps.modelo.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name="Carrito")
@NamedQuery(name="Carrito.findAll", query="SELECT c FROM Carrito c")
public class Carrito implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_carrito")
    private int idCarrito;
    
    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    
    //@ManyToOne
    //@JoinColumn(name = "id_producto")
    //private Producto producto;
    
    //@OneToMany(mappedBy = "carrito")
    //private List<Producto> productos;
    
    @ManyToMany
    @JoinTable(
        name = "Carrito_Producto",
        joinColumns = @JoinColumn(name = "id_carrito"),
        inverseJoinColumns = @JoinColumn(name = "id_producto")
    )
    @JsonManagedReference
    private List<Producto> productos;
    
    private int cantidad;
    
    private BigDecimal subtotal;
    
    @Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
    
    public List<Producto> getProductos() {
        return this.productos;
    }
    
    public int getCantidadProducto(Producto producto) {
        for (Producto p : productos) {
            if (p.getIdProducto() == producto.getIdProducto()) {
                return p.getCantidad();
            }
        }
        return 0; 
    }
    
    public void setCantidadProducto(Producto producto, int cantidad) {
        for (Producto p : productos) {
            if (p.getIdProducto() == producto.getIdProducto()) {
                p.setCantidad(cantidad);
                return;
            }
        }
        
}
}