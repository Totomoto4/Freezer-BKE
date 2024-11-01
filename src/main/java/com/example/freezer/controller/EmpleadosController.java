package com.example.freezer.controller;

import com.example.freezer.dto.EmpleadoDTO;
import com.example.freezer.dto.RegisterRequest;
import com.example.freezer.exception.AuthenticationException;
import com.example.freezer.dto.LoginRequest;
import com.example.freezer.exception.RegisterException;
import com.example.freezer.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmpleadosController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/auth")
    public ResponseEntity<EmpleadoDTO> login(@RequestBody LoginRequest loginRequest) throws AuthenticationException {
        EmpleadoDTO authUser = empleadoService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        return ResponseEntity.ok(authUser);
    }

    @PostMapping("/register")
    public ResponseEntity<EmpleadoDTO> register(@RequestBody RegisterRequest registerRequest) throws RegisterException {
        EmpleadoDTO newUser = empleadoService.registrarEmpleado(registerRequest);

        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/empleados/{orgID}")
    public ResponseEntity<List<EmpleadoDTO>> getEmpleadosByOrganizacion(@PathVariable int orgID) {
        List<EmpleadoDTO> empleadoDTOList = empleadoService.getEmpleadosByOrganizacion(orgID);

        return ResponseEntity.ok(empleadoDTOList);
    }
}
