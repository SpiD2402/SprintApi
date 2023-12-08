package com.codigo.ramirez_cardenas_piero.service.impl;

import com.codigo.ramirez_cardenas_piero.dao.LibroDao;

import com.codigo.ramirez_cardenas_piero.entity.CategoriaEntity;
import com.codigo.ramirez_cardenas_piero.entity.LibroEntity;
import com.codigo.ramirez_cardenas_piero.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    LibroDao libroDao;

    @Override
    public List<LibroEntity> obtenerTodo() {
        return  libroDao.findAll().stream()
                .filter(a -> a.getEstado() == 1)
                .collect(Collectors.toList());
    }

    @Override
    public LibroEntity obtenerPorId(Long id) throws Exception {
        Optional<LibroEntity> libro = libroDao.findById(id);

        if(libro.isPresent())
        {
            return libro.get();
        }
        else{
            throw new Exception("Error No existe Libro");
        }
    }

    @Override
    public LibroEntity crear(LibroEntity libro) {
        return libroDao.save(libro);
    }

    @Override
    public Optional<LibroEntity> actualizar(LibroEntity libro) throws Exception {
        return Optional.of(libroDao.save(libro));
    }


    @Override
    public void eliminar(Long id) throws Exception {
        Optional<LibroEntity> libro = libroDao.findById(id);

        if (!libro.isPresent()) {
            throw new Exception("No existe el libro con id: " + id);
        }

        libro.get().setEstado(0);
        libroDao.save(libro.get());
    }
}
