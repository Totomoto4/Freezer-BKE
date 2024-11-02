package com.example.freezer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CargaTemperaturaRequest {

    private Long grupoID;
    private float temperatura;
    private Long empleadoID;

}
