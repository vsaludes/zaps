package com.edix.restful.zaps.modelo.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
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
import lombok.ToString;

@Data
@AllArgsConstructor
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
    
    //@ManyToMany
    //@JoinTable(
      //  name = "Carrito_Producto",
        //joinColumns = @JoinColumn(name = "id_carrito"),
        //inverseJoinColumns = @JoinColumn(name = "id_producto")
    //)
    //@JsonManagedReference
    //private List<Producto> productos;
    
    //private int cantidad;
    
    //private BigDecimal subtotal;
    
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL/*, orphanRemoval = true*/)
    @JsonIgnore
    private List<CarritoProducto> carritoProducto;

    
    //@Temporal(TemporalType.TIMESTAMP)
	//private Date fecha;
    
    public Carrito() {
        this.carritoProducto = new ArrayList<>();
    }
   
    public List<Producto> getProductos() {
        List<Producto> productos = new ArrayList<>();
        for (CarritoProducto cp : carritoProducto) {
            productos.add(cp.getProducto());
        }
        return productos;
    }

    public int getCantidadProducto(Producto producto) {
        for (CarritoProducto cp : carritoProducto) {
            if (cp.getProducto().getIdProducto() == producto.getIdProducto()) {
                return cp.getCantidad();
            }
        }
        return 0; 
    }

    public void setCantidadProducto(Producto producto, int cantidad) {
        for (CarritoProducto cp : carritoProducto) {
            if (cp.getProducto().getIdProducto() == producto.getIdProducto()) {
                cp.setCantidad(cantidad);
                return;
            }
        }
    }

    public void agregarProducto(Producto producto, int cantidad) {
        for (CarritoProducto cp : carritoProducto) {
            if (cp.getProducto().getIdProducto() == producto.getIdProducto()) {
                cp.setCantidad(cp.getCantidad() + cantidad);
                cp.setSubtotal(cp.getProducto().getPrecio().multiply(BigDecimal.valueOf(cp.getCantidad())));
                return;
            }
        }
        CarritoProducto nuevoCarritoProducto = new CarritoProducto();
        nuevoCarritoProducto.setCarrito(this);
        nuevoCarritoProducto.setProducto(producto);
        nuevoCarritoProducto.setCantidad(cantidad);
        nuevoCarritoProducto.setSubtotal(producto.getPrecio().multiply(BigDecimal.valueOf(cantidad)));
        carritoProducto.add(nuevoCarritoProducto);
    }

    @Override
    public String toString() {
        return "Carrito{" +
                "idCarrito=" + idCarrito +
                ", usuario=" + (usuario != null ? usuario.getUsername() : "null") +
                '}';
    }


}