package com.example.freezer.service;

import com.example.freezer.dao.LocacionDAO;
import com.example.freezer.dao.OrganizacionDAO;
import com.example.freezer.dto.EmpleadoDTO;
import com.example.freezer.model.Empleado;
import com.example.freezer.model.Locacion;
import com.example.freezer.model.Organizacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocacionService {

    @Autowired
    LocacionDAO locacionDAO;

    @Autowired
    OrganizacionDAO organizacionDAO;

    public List<Locacion> getLocacionByOrganizacion(Long orgID) {
        Optional<Organizacion> organizacion = organizacionDAO.findById(orgID);

        if (organizacion.isEmpty()){
            throw new RuntimeException("No se encontro la organizacion");
        }

        return locacionDAO.findAllByOrganizacion(organizacion.get());

    }
}
