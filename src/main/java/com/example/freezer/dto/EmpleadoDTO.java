package com.example.freezer.dto;

import com.example.freezer.model.Empleado;
import com.example.freezer.model.Organizacion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpleadoDTO {

    private Organizacion organizacionId;

    private String nombre;

    private String apellido;

    private String email;

    private Empleado.TipoEmpleado tipo;

    public enum TipoEmpleado {
        SUPERVISOR,
        BASICO
    }

    public EmpleadoDTO(Empleado empleado){
        this.nombre = empleado.getNombre();
        this.apellido = empleado.getApellido();
        this.email = empleado.getEmail();
        this.organizacionId = empleado.getOrganizacion();
        this.tipo = empleado.getTipo();
    }

}