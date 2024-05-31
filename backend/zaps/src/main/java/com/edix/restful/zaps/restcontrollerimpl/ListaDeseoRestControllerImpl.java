package com.edix.restful.zaps.restcontrollerimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edix.restful.zaps.modelo.entities.ListaDeseo;
import com.edix.restful.zaps.modelo.entities.Producto;
import com.edix.restful.zaps.restcontroller.ListaDeseoController;
import com.edix.restful.zaps.service.ListaDeseoService;

import java.util.List;

    @RestController
    @RequestMapping("/api/listadeseos")
    public class ListaDeseoRestControllerImpl implements ListaDeseoController {

        @Autowired
        private ListaDeseoService listaDeseoService;

        @PostMapping("/{idListaDeseo}/{idProducto}")
        public ResponseEntity<Void> agregarProducto(@PathVariable int idListaDeseo, @PathVariable int idProducto) {
            boolean exito = listaDeseoService.agregarProductoAListaDeseos(idListaDeseo, idProducto);
            if (exito) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/{idListaDeseo}/{idProducto}")
        public ResponseEntity<Void> eliminarProducto(@PathVariable int idListaDeseo, @PathVariable int idProducto) {
            boolean exito = listaDeseoService.eliminarProductoDeListaDeseos(idListaDeseo, idProducto);
            if (exito) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @GetMapping("/{idListaDeseo}")
        public ResponseEntity<List<Producto>> obtenerProductos(@PathVariable int idListaDeseo) {
            List<Producto> productos = listaDeseoService.obtenerProductosListaDeseos(idListaDeseo);
            if (!productos.isEmpty()) {
                return ResponseEntity.ok(productos);
            } else {
                return ResponseEntity.noContent().build();
            }
        }
        
        @PutMapping("/{idListaDeseo}/{idProducto}/notificar")
        public ResponseEntity<Void> actualizarNotificacionProducto(@PathVariable int idListaDeseo, @PathVariable int idProducto, @RequestParam boolean notificar) {
            boolean exito = listaDeseoService.notificacionProducto(idListaDeseo, idProducto, notificar);
            if (exito) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }
    
    /*
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ListaDeseo> buscarListaDeseoPorId(@PathVariable int id) {
        ListaDeseo listaDeseo = listaDeseoService.buscarListaDeseoPorId(id);
        if (listaDeseo != null) {
            return ResponseEntity.ok(listaDeseo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ListaDeseo>> buscarTodosListasDeseo() {
        List<ListaDeseo> listasDeseo = listaDeseoService.buscarTodosListasDeseo();
        return ResponseEntity.ok(listasDeseo);
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> modificarListaDeseo(@RequestBody ListaDeseo listaDeseo) {
        boolean result = listaDeseoService.modificarListaDeseo(listaDeseo);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> crearListaDeseo(@RequestBody ListaDeseo listaDeseo) {
        boolean result = listaDeseoService.crearListaDeseo(listaDeseo);
        if (result) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarListaDeseo(@PathVariable int id) {
        boolean result = listaDeseoService.eliminarListaDeseo(id);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
*/