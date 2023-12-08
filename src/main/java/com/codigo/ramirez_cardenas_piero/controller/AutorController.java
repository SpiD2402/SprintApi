package com.codigo.ramirez_cardenas_piero.controller;

import com.codigo.ramirez_cardenas_piero.entity.AutorEntity;

import com.codigo.ramirez_cardenas_piero.entity.EditorEntity;
import com.codigo.ramirez_cardenas_piero.service.AutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public List<AutorEntity> listar()
    {
        List<AutorEntity> autores = autorService.obtenerTodo();
        return  autores;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorEntity> libroGetId(@PathVariable Long id) throws Exception {
        AutorEntity autor= autorService.obtenerPorId(id);
        return ResponseEntity.ok(autor);
    }

    @PostMapping
    public ResponseEntity<AutorEntity>crear(@RequestBody AutorEntity libro)
    {
        AutorEntity autorAdd = autorService.crear(libro);
        return  new ResponseEntity<>(autorAdd, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Optional<AutorEntity> actualizar(@PathVariable Long id, @RequestBody AutorEntity autor) throws Exception {
        Optional<AutorEntity> autorEncontrado = Optional.ofNullable(autorService.obtenerPorId(id));
        if (autorEncontrado.isPresent()) {
            autorEncontrado.get().setNombre(autor.getNombre());
            autorEncontrado.get().setEstado(autor.getEstado());
            autorService.actualizar(autorEncontrado.get());
        }
        return new ResponseEntity<>(autorEncontrado, HttpStatus.OK).getBody();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) throws Exception {
        try {
            autorService.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
