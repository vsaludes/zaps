package com.edix.restful.zaps.serviceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.edix.restful.zaps.modelo.dto.ProductoFilterDTO;
import com.edix.restful.zaps.modelo.entities.Producto;
import com.edix.restful.zaps.modelo.entities.Producto.Genero;
import com.edix.restful.zaps.modelo.entities.Producto.Talla;
import com.edix.restful.zaps.modelo.entities.Producto.TipoDistancia;
import com.edix.restful.zaps.modelo.entities.Producto.TipoPisada;
import com.edix.restful.zaps.modelo.entities.Producto.TipoSuperficie;
import com.edix.restful.zaps.modelo.entities.Producto.Uso;
import com.edix.restful.zaps.repository.ProductoRepository;
import com.edix.restful.zaps.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;
	
	@Override
	@Transactional(readOnly=true)
	public Producto buscarProductoId(int idProducto) {
		try {
			return productoRepository.findById(idProducto).orElse(null); }
		catch (Exception e) {
			e.printStackTrace();
			return null; }
	}

	@Override
	@Transactional
	public Producto altaProducto(Producto producto) {
		try {
			return productoRepository.save(producto);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional
	public Producto modificarProducto(Producto producto) {
		try {
			if (buscarProductoId(producto.getIdProducto()) != null)
				return productoRepository.save(producto);
			else
				return null;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	@Transactional
	public boolean eliminarProducto(int idProducto) {
		try {
			if (buscarProductoId(idProducto) != null) {
				 productoRepository.deleteById(idProducto);
				 return true;
			}
			else
				return false;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<Producto> buscarPorFiltros(String nombre, BigDecimal precioMin, BigDecimal precioMax, Talla talla,
			String color, TipoPisada tipoPisada, TipoSuperficie tipoSuperficie, TipoDistancia tipoDistancia,
			int tipoDrop, Genero genero, String marca, Uso uso, int año, boolean disponible) {
		try {
	        return productoRepository.buscarProductosFiltrados(nombre, precioMin, precioMax, talla, color,
	                tipoPisada, tipoSuperficie, tipoDistancia, tipoDrop, genero, marca, uso, año, disponible);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	}

}

	@Override
	@Transactional
	public List<Producto> buscarTodosProductos() {
		try {
			return productoRepository.findAll();
		} catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Producto> findAll(Pageable pageable) {		
		return productoRepository.findAll(pageable);
	}

	@Override
	public List<Producto> buscarProductosRecomendador(ProductoFilterDTO filtro) {
		Specification<Producto> specification = filtro.obtainFilterSpecification();
		List<Producto> productosFiltrados = productoRepository.findAll(specification);
		
		return productosFiltrados;
	}
	}
