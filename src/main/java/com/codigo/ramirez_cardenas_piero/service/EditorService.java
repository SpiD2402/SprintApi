package com.codigo.ramirez_cardenas_piero.service;

import com.codigo.ramirez_cardenas_piero.entity.EditorEntity;


import java.util.List;
import java.util.Optional;

public interface EditorService {
    List<EditorEntity> obtenerTodo();
    EditorEntity obtenerPorId(Long id) throws Exception;

    EditorEntity crear(EditorEntity editor);

    Optional<EditorEntity> actualizar(EditorEntity editor) throws Exception;

    void eliminar(Long id) throws Exception;
}
