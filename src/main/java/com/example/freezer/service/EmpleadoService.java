package com.example.freezer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.freezer.dao.EmpleadoDAO;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoDAO empleadoDAO;

    /**public Empleado loginEmpleado(EmpleadoDTO){

    }**/

}
