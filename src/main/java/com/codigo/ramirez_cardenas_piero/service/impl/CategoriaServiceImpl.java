package com.codigo.ramirez_cardenas_piero.service.impl;

import com.codigo.ramirez_cardenas_piero.dao.CategoriaDao;
import com.codigo.ramirez_cardenas_piero.entity.AutorEntity;
import com.codigo.ramirez_cardenas_piero.entity.CategoriaEntity;
import com.codigo.ramirez_cardenas_piero.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaDao categoriaDao;
    @Override
    public List<CategoriaEntity> obtenerTodo() {
        return categoriaDao.findAll().stream()
                .filter(a -> a.getEstado() == 1)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaEntity obtenerPorId(Long id) throws Exception {
        Optional<CategoriaEntity> categoria = categoriaDao.findById(id);

        if(categoria.isPresent())
        {
            return  categoria.get();
        }
        else{
            throw new Exception("Error No existe Categoria");
        }
    }

    @Override
    public CategoriaEntity crear(CategoriaEntity categoria) {
        return categoriaDao.save(categoria);
    }


    @Override
    public Optional<CategoriaEntity> actualizar(CategoriaEntity editorActualizado) throws Exception {
        return Optional.of(categoriaDao.save(editorActualizado));
    }

    @Override
    public void eliminar(Long id) throws Exception {
        Optional<CategoriaEntity> categoria = categoriaDao.findById(id);

        if (!categoria.isPresent()) {
            throw new Exception("No existe la categoría con id: " + id);
        }

        categoria.get().setEstado(0);
        categoriaDao.save(categoria.get());
    }
}
