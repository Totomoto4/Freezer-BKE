package com.example.freezer.model;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "grupo_producto")
@ToString
public class GrupoProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_local", nullable = false)
    private Locacion locacion;

    private String nombre;

}

