package com.example.freezer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "locacion")
public class Locacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_organizacion", nullable = false)
    private Organizacion organizacion;

    private String nombre;
    private String direccion;

    @JsonIgnore
    @OneToMany(mappedBy = "locacion", cascade = CascadeType.ALL)
    private List<GrupoProducto> gruposProducto;
}
