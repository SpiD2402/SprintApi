package com.codigo.ramirez_cardenas_piero.controller;

import com.codigo.ramirez_cardenas_piero.entity.EditorEntity;
import com.codigo.ramirez_cardenas_piero.service.EditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/editor")
public class EditorController {

    @Autowired
    private EditorService editorService;

    @GetMapping
    public List<EditorEntity> listar()
    {
        List<EditorEntity> editores = editorService.obtenerTodo();
        return  editores;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EditorEntity> libroGetId(@PathVariable Long id) throws Exception {
        EditorEntity editor= editorService.obtenerPorId(id);
        return ResponseEntity.ok(editor);
    }

    @PostMapping
    public ResponseEntity<EditorEntity>crear(@RequestBody EditorEntity editor)
    {
        EditorEntity autorAdd = editorService.crear(editor);
        return  new ResponseEntity<>(autorAdd, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Optional<EditorEntity> actualizar(@PathVariable Long id, @RequestBody EditorEntity editor) throws Exception {
        Optional<EditorEntity> editorEncontrado = Optional.ofNullable(editorService.obtenerPorId(id));
        if(editorEncontrado.isPresent())
        {
            editorEncontrado.get().setNombre(editor.getNombre());
            editorEncontrado.get().setEstado(editor.getEstado());
            editorService.actualizar(editorEncontrado.get());
        }
        return new ResponseEntity<>(editorEncontrado,HttpStatus.OK).getBody();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) throws Exception {
        try {
            editorService.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
