package com.codigo.ramirez_cardenas_piero.service.impl;

import com.codigo.ramirez_cardenas_piero.dao.AutorDao;
import com.codigo.ramirez_cardenas_piero.entity.AutorEntity;

import com.codigo.ramirez_cardenas_piero.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AutorServiceImpl implements AutorService {

    @Autowired
    private AutorDao autorDao;

    @Override
    public List<AutorEntity> obtenerTodo() {
        return autorDao.findAll().stream()
                .filter(a -> a.getEstado() == 1)
                .collect(Collectors.toList());
    }

    @Override
    public AutorEntity obtenerPorId(Long id) throws Exception {
        Optional<AutorEntity> autor = autorDao.findById(id);

        if(autor.isPresent())
        {
            return autor.get();
        }
        else{
            throw new Exception("Error No existe Autor");
        }
    }

    @Override
    public AutorEntity crear(AutorEntity autor) {
        return autorDao.save(autor);
    }

    @Override
    public Optional<AutorEntity> actualizar(AutorEntity editorActualizado) throws Exception {
        return Optional.of(autorDao.save(editorActualizado));
    }

    @Override
    public void eliminar(Long id) {
        Optional<AutorEntity> autor = autorDao.findById(id);
        if (autor.isPresent()) {
            autor.get().setEstado(0);
            autorDao.save(autor.get());
        }
    }
}
