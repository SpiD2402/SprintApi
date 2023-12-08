package com.codigo.ramirez_cardenas_piero.controller;

import com.codigo.ramirez_cardenas_piero.entity.AutorEntity;
import com.codigo.ramirez_cardenas_piero.entity.CategoriaEntity;
import com.codigo.ramirez_cardenas_piero.service.AutorService;
import com.codigo.ramirez_cardenas_piero.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaEntity> listar()
    {
        List<CategoriaEntity> categorias = categoriaService.obtenerTodo();
        return  categorias;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaEntity> libroGetId(@PathVariable Long id) throws Exception {
        CategoriaEntity autor= categoriaService.obtenerPorId(id);
        return ResponseEntity.ok(autor);
    }

    @PostMapping
    public ResponseEntity<CategoriaEntity>crear(@RequestBody CategoriaEntity libro)
    {
        CategoriaEntity categoriaAdd = categoriaService.crear(libro);
        return  new ResponseEntity<>(categoriaAdd, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Optional<CategoriaEntity> actualizar(@PathVariable Long id, @RequestBody CategoriaEntity categoria) throws Exception {
        Optional<CategoriaEntity> categoriaEncontrada = Optional.ofNullable(categoriaService.obtenerPorId(id));
        if (categoriaEncontrada.isPresent()) {
            categoriaEncontrada.get().setNombre(categoria.getNombre());
            categoriaEncontrada.get().setEstado(categoria.getEstado());
            categoriaService.actualizar(categoriaEncontrada.get());
        }
        return new ResponseEntity<>(categoriaEncontrada, HttpStatus.OK).getBody();

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) throws Exception {
        try {
            categoriaService.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
