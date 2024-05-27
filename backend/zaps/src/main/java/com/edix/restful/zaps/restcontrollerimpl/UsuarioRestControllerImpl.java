package com.edix.restful.zaps.restcontrollerimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.edix.restful.zaps.modelo.entities.Producto;
import com.edix.restful.zaps.modelo.entities.Usuario;
import com.edix.restful.zaps.modelo.entities.Valoracion;
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

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable int id) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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
    public ResponseEntity<List<Usuario>> buscarTodosUsuarios() {
        List<Usuario> usuarios = usuarioService.buscarTodosUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping
    public ResponseEntity<Void> modificarUsuario(@Validated @RequestBody Usuario usuario) {
        boolean result = usuarioService.modificarUsuario(usuario);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/{id}/listaDeseos")
    public ResponseEntity<Void> agregarAListaDeseos(@PathVariable int id, @RequestBody Producto producto) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        if (usuario != null) {
            boolean result = usuarioService.agregarAListaDeseos(usuario, producto);
            if (result) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/{id}/listaDeseos")
    public ResponseEntity<Void> eliminarDeListaDeseos(@PathVariable int id, @RequestBody Producto producto) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        if (usuario != null) {
            boolean result = usuarioService.eliminarDeListaDeseos(usuario, producto);
            if (result) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/{id}/valoraciones")
    public ResponseEntity<Void> agregarValoracionAUsuario(@PathVariable int id, @RequestBody Valoracion valoracion) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        if (usuario != null) {
            boolean result = usuarioService.agregarValoracionAUsuario(usuario, valoracion);
            if (result) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/{id}/valoraciones")
    public ResponseEntity<Void> eliminarValoracionDeUsuario(@PathVariable int id, @RequestBody Valoracion valoracion) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        if (usuario != null) {
            boolean result = usuarioService.eliminarValoracionDeUsuario(usuario, valoracion);
            if (result) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/{id}/notificacionProducto")
    public ResponseEntity<Void> enviarNotificacionProducto(@PathVariable int id, @RequestBody Producto producto) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
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
}
