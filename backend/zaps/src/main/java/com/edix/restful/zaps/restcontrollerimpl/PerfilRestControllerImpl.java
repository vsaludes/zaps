package com.edix.restful.zaps.restcontrollerimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edix.restful.zaps.modelo.entities.Perfil;
import com.edix.restful.zaps.restcontroller.PerfilController;
import com.edix.restful.zaps.service.PerfilService;

import java.util.List;

@RestController
@RequestMapping("/api/perfiles")
public class PerfilRestControllerImpl implements PerfilController {

    @Autowired
    private PerfilService perfilService;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Perfil> buscarPerfilPorId(@PathVariable int id) {
        Perfil perfil = perfilService.buscarPerfilPorId(id);
        if (perfil != null) {
            return ResponseEntity.ok(perfil);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Perfil>> buscarTodosPerfiles() {
        List<Perfil> perfiles = perfilService.buscarTodosPerfiles();
        return ResponseEntity.ok(perfiles);
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> modificarPerfil(@RequestBody Perfil perfil) {
        boolean result = perfilService.modificarPerfil(perfil);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> crearPerfil(@RequestBody Perfil perfil) {
        boolean result = perfilService.crearPerfil(perfil);
        if (result) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPerfil(@PathVariable int id) {
        boolean result = perfilService.eliminarPerfil(id);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
