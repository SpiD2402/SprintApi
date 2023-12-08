package com.codigo.ramirez_cardenas_piero.service;

import com.codigo.ramirez_cardenas_piero.entity.AutorEntity;
import com.codigo.ramirez_cardenas_piero.entity.CategoriaEntity;

import java.util.List;
import java.util.Optional;

public interface AutorService {

    List<AutorEntity> obtenerTodo();
    AutorEntity obtenerPorId(Long id) throws Exception;

    AutorEntity crear(AutorEntity autor);

    Optional <AutorEntity> actualizar(AutorEntity editorActualizado) throws Exception;

    void eliminar(Long id);

}
