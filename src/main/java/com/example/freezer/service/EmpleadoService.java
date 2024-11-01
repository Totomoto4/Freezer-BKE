package com.example.freezer.service;

import com.example.freezer.dao.OrganizacionDAO;
import com.example.freezer.dto.EmpleadoDTO;
import com.example.freezer.dto.RegisterRequest;
import com.example.freezer.exception.AuthenticationException;
import com.example.freezer.exception.RegisterException;
import com.example.freezer.model.Empleado;
import com.example.freezer.model.Organizacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.freezer.dao.EmpleadoDAO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoDAO empleadoDAO;
    @Autowired
    private OrganizacionDAO organizacionDAO;

    public EmpleadoDTO authenticate(String email, String password) throws AuthenticationException {
        Empleado empleado = empleadoDAO.findEmpleadoByEmail(email);

        if (empleado == null) {
            throw new AuthenticationException("Empleado no encontrado con el email: " + email);
        }

        if (!empleado.getPassword().equals(password)) {
            throw new AuthenticationException("Contraseña incorrecta para el email: " + email);
        }

        return new EmpleadoDTO(empleado);
    }

    public EmpleadoDTO registrarEmpleado(RegisterRequest registerRequest) throws RegisterException {
        // Verificar si el email ya existe
        if (empleadoDAO.findEmpleadoByEmail(registerRequest.getEmail()) != null) {
            throw new RegisterException("El email ya está en uso");
        }

        //Obtengo organizacion
        Optional<Organizacion> organizacion = organizacionDAO.findById((long) registerRequest.getOrganizacionID());

        if (organizacion.isEmpty()){
            throw new RegisterException("No se encontro la organizacion");
        }

        // Crear el nuevo empleado
        Empleado nuevoEmpleado = new Empleado();
        nuevoEmpleado.setNombre(registerRequest.getNombre());
        nuevoEmpleado.setEmail(registerRequest.getEmail());
        nuevoEmpleado.setPassword(registerRequest.getPassword());
        nuevoEmpleado.setApellido(registerRequest.getApellido());
        nuevoEmpleado.setOrganizacion(organizacion.get());
        nuevoEmpleado.setTipo(registerRequest.getTipo());

        // Guardar el empleado en la base de datos
        Empleado empleadoGuardado = empleadoDAO.save(nuevoEmpleado);
        return new EmpleadoDTO(empleadoGuardado);
    }

    public List<EmpleadoDTO> getEmpleadosByOrganizacion(int orgID) {
        Optional<Organizacion> organizacion = organizacionDAO.findById(Long.valueOf(orgID));

        if (organizacion.isEmpty()){
            throw new RuntimeException();
        }

        List<Empleado> empleados = empleadoDAO.findEmpleadosByOrganizacion(organizacion.get());

        return empleados.stream()
                .map(EmpleadoDTO::new)
                .collect(Collectors.toList());
    }
}
