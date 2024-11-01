package com.example.freezer.dto;

import com.example.freezer.model.Empleado;
import lombok.Getter;

@Getter
public class RegisterRequest {

    private String nombre;
    private String apellido;
    private String password;
    private String email;
    private Empleado.TipoEmpleado tipo;
    private int organizacionID;

    public enum TipoEmpleado {
        SUPERVISOR,
        BASICO
    }

}
