package com.example.freezer.service;

import com.example.freezer.dao.EmpleadoDAO;
import com.example.freezer.dto.EmpleadoDTO;
import com.example.freezer.model.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoDAO empleadoDAO;

    /**public Empleado loginEmpleado(EmpleadoDTO){

    }**/

}
