package com.example.freezer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoProductoRequest {

    private Long locacionID;

    private String nombre;

    private String categoria; // Se espera un valor como "CONGELADO" o "REFRIGERADO"
}
