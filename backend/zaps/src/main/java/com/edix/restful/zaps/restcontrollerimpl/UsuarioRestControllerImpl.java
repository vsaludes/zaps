package com.edix.restful.zaps.restcontrollerimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.edix.restful.zaps.modelo.entities.Producto;
import com.edix.restful.zaps.modelo.entities.Usuario;
import com.edix.restful.zaps.modelo.entities.Valoracion;
import com.edix.restful.zaps.service.ListaDeseoService;
import com.edix.restful.zaps.service.UsuarioService;

import lombok.RequiredArgsConstructor;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class UsuarioRestControllerImpl {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired 
    private ListaDeseoService listaDeseoService;

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable int idUsuario) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        boolean creado = usuarioService.crearUsuario(usuario);
        if (creado) {
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @GetMapping("/nombre/{username}")
    public ResponseEntity<Optional<Usuario>> buscarUsuarioPorNombre(@PathVariable String username) {
        Optional<Usuario> usuario = usuarioService.buscarUsuarioPorNombre(username);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> buscarUsuarioPorEmail(@PathVariable String email) {
        Usuario usuario = usuarioService.buscarUsuarioPorEmail(email);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Usuario>> buscarTodosUsuarios() {
        List<Usuario> usuarios = usuarioService.buscarTodosUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping
    public ResponseEntity<Usuario> modificarUsuario(@RequestBody Usuario usuario) {
        boolean result = usuarioService.modificarUsuario(usuario);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/{idUsuario}/listaDeseos/{idProducto}")
    public ResponseEntity<Void> agregarAListaDeseos(@PathVariable int idUsuario, @PathVariable int idProducto) {
        boolean result = listaDeseoService.agregarProductoAListaDeseos(idUsuario, idProducto);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @DeleteMapping("/{idUsuario}/listaDeseos/{idProducto}")
    public ResponseEntity<Void> eliminarDeListaDeseos(@PathVariable int idUsuario, @PathVariable int idProducto) {
        boolean result = listaDeseoService.eliminarProductoDeListaDeseos(idUsuario, idProducto);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/{idUsuario}/valoraciones")
    public ResponseEntity<Void> agregarValoracionAUsuario(@PathVariable int idUsuario, @RequestBody Valoracion valoracion) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);
        if (usuario != null) {
            boolean result = usuarioService.agregarValoracionAUsuario(usuario, valoracion);
            if (result) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/{idUsuario}/valoraciones")
    public ResponseEntity<Void> eliminarValoracionDeUsuario(@PathVariable int idUsuario, @RequestBody Valoracion valoracion) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);
        if (usuario != null) {
            boolean result = usuarioService.eliminarValoracionDeUsuario(usuario, valoracion);
            if (result) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/{idUsuario}/notificacionProducto")
    public ResponseEntity<Void> enviarNotificacionProducto(@PathVariable int idUsuario, @RequestBody Producto producto) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);
        if (usuario != null) {
            boolean result = usuarioService.enviarNotificacionProducto(usuario, producto);
            if (result) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/current")
    public ResponseEntity<Optional<Usuario>> getCurrentUser(Principal principal) {
        // Assuming you have a method to get user by username (principal.getName())
        Optional<Usuario> user = usuarioService.buscarUsuarioPorNombre(principal.getName());
        return ResponseEntity.ok(user);
    }
    
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable int idUsuario) {
        boolean result = usuarioService.eliminarUsuario(idUsuario);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario con ID " + idUsuario + " no fue encontrado");
        }
    }
}
