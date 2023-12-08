package com.codigo.ramirez_cardenas_piero.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "editor")
public class EditorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String nombre;

    private int estado;
}
