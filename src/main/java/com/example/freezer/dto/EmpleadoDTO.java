package com.example.freezer.dto;

public class EmpleadoDTO {

    private int organizacionId;

    private String nombre;

    private String apellido;

    private String email;

    private String password;

    private TipoEmpleado tipo;

    public enum TipoEmpleado {
        SUPERVISOR,
        BASICO
    }

}