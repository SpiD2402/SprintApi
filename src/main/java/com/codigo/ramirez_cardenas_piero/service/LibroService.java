package com.codigo.ramirez_cardenas_piero.service;

import com.codigo.ramirez_cardenas_piero.entity.LibroEntity;

import java.util.List;
import java.util.Optional;

public interface LibroService {

    List<LibroEntity>obtenerTodo();
    LibroEntity obtenerPorId(Long id) throws Exception;

    LibroEntity crear(LibroEntity libro);

    Optional<LibroEntity> actualizar(LibroEntity autorActualizado) throws Exception;

    void eliminar(Long id) throws Exception;

}
