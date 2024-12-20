package com.example.freezer.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "grupo_producto")
@ToString
@Getter
@Setter
public class GrupoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_local", nullable = false)
    private Locacion locacion;

    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaAlmacenamiento categoria;

    @Getter
    public enum CategoriaAlmacenamiento {
        CONGELADO(-18.0),
        REFRIGERADO(4.0);

        private final double temperaturaMaxima;

        CategoriaAlmacenamiento(double temperaturaMaxima) {
            this.temperaturaMaxima = temperaturaMaxima;
        }

    }

}

