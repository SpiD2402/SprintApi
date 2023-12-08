package com.codigo.ramirez_cardenas_piero.service.impl;

import com.codigo.ramirez_cardenas_piero.dao.EditorDao;

import com.codigo.ramirez_cardenas_piero.entity.EditorEntity;
import com.codigo.ramirez_cardenas_piero.service.EditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EditorServiceImpl implements EditorService {

    @Autowired
    private EditorDao editorDao;
    @Override
    public List<EditorEntity> obtenerTodo() {
         return editorDao.findAll().stream()
                .filter(a -> a.getEstado() == 1)
                .collect(Collectors.toList());
    }

    @Override
    public EditorEntity obtenerPorId(Long id) throws Exception {
        Optional<EditorEntity> editor = editorDao.findById(id);

        if(editor.isPresent())
        {
            return  editor.get();
        }
        else{
            throw new Exception("Error No existe Editor");
        }
    }

    @Override
    public EditorEntity crear(EditorEntity editor) {
        return editorDao.save(editor);
    }

    @Override
    public Optional<EditorEntity> actualizar(EditorEntity editor) throws Exception {
        return Optional.of(editorDao.save(editor));
    }

    @Override
    public void eliminar(Long id) throws Exception {
        Optional<EditorEntity> editor = editorDao.findById(id);

        if (!editor.isPresent()) {
            throw new Exception("No existe el editor con id: " + id);
        }

        editor.get().setEstado(0);
        editorDao.save(editor.get());
    }
}
