package com.edix.restful.zaps.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.edix.restful.zaps.modelo.entities.ListaDeseo;
import com.edix.restful.zaps.modelo.entities.ListaDeseoProducto;
import com.edix.restful.zaps.modelo.entities.Producto;
import com.edix.restful.zaps.repository.ListaDeseoProductoRepository;
import com.edix.restful.zaps.repository.ListaDeseoRepository;
import com.edix.restful.zaps.repository.ProductoRepository;
import com.edix.restful.zaps.service.ListaDeseoService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ListaDeseoServiceImpl implements ListaDeseoService {

    @Autowired
    private ListaDeseoRepository listaDeseoRepository;

    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private ListaDeseoProductoRepository listaDeseoProductoRepository;
    
    @Override
    public boolean agregarProductoAListaDeseos(int idListaDeseo, int idProducto) {
        ListaDeseo listaDeseo = listaDeseoRepository.findById(idListaDeseo).orElse(null);
        Producto producto = productoRepository.findById(idProducto).orElse(null);
        if (listaDeseo != null && producto != null) {
        	ListaDeseoProducto listaDeseoProducto = new ListaDeseoProducto();
            listaDeseoProducto.setListaDeseo(listaDeseo);
            listaDeseoProducto.setProducto(producto);
            listaDeseoProducto.setFechaAgregado(new Date());
            listaDeseoProducto.setNotificar(false); 
            listaDeseoProductoRepository.save(listaDeseoProducto);
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarProductoDeListaDeseos(int idListaDeseo, int idProducto) {
        listaDeseoProductoRepository.deleteByListaDeseoIdAndProductoId(idListaDeseo, idProducto);
        return true;
    }

    @Override
    public List<Producto> obtenerProductosListaDeseos(int idListaDeseo) {
        List<ListaDeseoProducto> listaDeseoProductos = listaDeseoProductoRepository.findByListaDeseoId(idListaDeseo);
        List<Producto> productos = new ArrayList<>();
        for (ListaDeseoProducto listaDeseoProducto : listaDeseoProductos) {
            productos.add(listaDeseoProducto.getProducto());
        }
        return productos;
    }
    
    @Override
    public boolean crearListaDeseo(ListaDeseo listaDeseo) {
        listaDeseoRepository.save(listaDeseo);
        return true;
    }
    
    @Override
    public boolean notificacionProducto(int idListaDeseo, int idProducto, boolean notificar) {
        ListaDeseoProducto listaDeseoProducto = listaDeseoProductoRepository.findByListaDeseoIdAndProductoId(idListaDeseo, idProducto);
        if (listaDeseoProducto != null) {
            listaDeseoProducto.setNotificar(notificar);
            listaDeseoProductoRepository.save(listaDeseoProducto);
            return true;
        }
        return false;
    }
}

/*
@Override
public ListaDeseo buscarListaDeseoPorId(int idListaDeseo) {
    return listaDeseoRepository.findById(idListaDeseo).orElse(null);
}

@Override
public List<ListaDeseo> buscarTodosListasDeseo() {
    return listaDeseoRepository.findAll();
}

@Override
public boolean eliminarListaDeseo(int idListaDeseo) {
    if (listaDeseoRepository.existsById(idListaDeseo)) {
        listaDeseoRepository.deleteById(idListaDeseo);
        return true;
    }
    return false;
}
}

    /*
    @Override
    public ListaDeseo buscarListaDeseoPorId(int idListaDeseo) {
        return listaDeseoRepository.findById(idListaDeseo).orElse(null);
    }

    @Override
    public List<ListaDeseo> buscarTodosListasDeseo() {
        return listaDeseoRepository.findAll();
    }

    @Override
    public boolean modificarListaDeseo(ListaDeseo listaDeseo) {
        if (listaDeseoRepository.existsById(listaDeseo.getIdDeseos())) {
            listaDeseoRepository.save(listaDeseo);
            return true;
        }
        return false;
    }

    @Override
    public boolean crearListaDeseo(ListaDeseo listaDeseo) {
        listaDeseoRepository.save(listaDeseo);
        return true;
    }

    @Override
    public boolean eliminarListaDeseo(int idListaDeseo) {
        if (listaDeseoRepository.existsById(idListaDeseo)) {
            listaDeseoRepository.deleteById(idListaDeseo);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean agregarProductoAListaDeseos(int idListaDeseo, Producto producto) {
        ListaDeseo listaDeseo = listaDeseoRepository.findById(idListaDeseo).orElse(null);
        if (listaDeseo != null) {
            listaDeseo.getProducto().add(producto);
            listaDeseoRepository.save(listaDeseo);
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarProductoDeListaDeseos(int idListaDeseo, Producto producto) {
        ListaDeseo listaDeseo = listaDeseoRepository.findById(idListaDeseo).orElse(null);
        if (listaDeseo != null) {
            listaDeseo.getProducto().remove(producto);
            listaDeseoRepository.save(listaDeseo);
            return true;
        }
        return false;
    }

    @Override
    public List<Producto> obtenerProductosListaDeseos(int idListaDeseo) {
        ListaDeseo listaDeseo = listaDeseoRepository.findById(idListaDeseo).orElse(null);
        if (listaDeseo != null) {
            return listaDeseo.getProducto();
        }
        return new ArrayList<>();
    }
} */