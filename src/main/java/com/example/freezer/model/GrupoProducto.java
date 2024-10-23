package com.example.freezer.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "grupo_producto")
public class GrupoProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_local", nullable = false)
    private Locacion locacion;

    private String nombre;

}

