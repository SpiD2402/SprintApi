package com.codigo.ramirez_cardenas_piero.dao;

import com.codigo.ramirez_cardenas_piero.entity.LibroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroDao extends JpaRepository<LibroEntity,Long> {
}
