package com.codigo.ramirez_cardenas_piero.controller;

import com.codigo.ramirez_cardenas_piero.entity.AutorEntity;
import com.codigo.ramirez_cardenas_piero.entity.LibroEntity;
import com.codigo.ramirez_cardenas_piero.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/libro")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public List<LibroEntity> listar()
    {
        List<LibroEntity> libros = libroService.obtenerTodo();
        return  libros;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibroEntity>libroGetId(@PathVariable Long id) throws Exception {
            LibroEntity libro= libroService.obtenerPorId(id);
            return ResponseEntity.ok(libro);
    }

    @PostMapping
    public ResponseEntity<LibroEntity>crear(@RequestBody LibroEntity libro)
    {
            LibroEntity libroAdd = libroService.crear(libro);
            return  new ResponseEntity<>(libroAdd, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Optional<LibroEntity> actualizar(@PathVariable Long id, @RequestBody LibroEntity libro) throws Exception {
        Optional<LibroEntity> libroEncontrado = Optional.ofNullable(libroService.obtenerPorId(id));
        if (libroEncontrado.isPresent()) {
            libroEncontrado.get().setEstado(libro.getEstado());
            libroEncontrado.get().setTitulo(libro.getTitulo());
            libroEncontrado.get().setEditor(libro.getEditor());
            libroEncontrado.get().setAutores(libro.getAutores());
            libroEncontrado.get().setCategorias(libro.getCategorias());
            libroService.actualizar(libroEncontrado.get());
        }
        return new ResponseEntity<>(libroEncontrado, HttpStatus.OK).getBody();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) throws Exception {
        try {
            libroService.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
