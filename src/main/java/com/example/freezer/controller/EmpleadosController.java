package com.example.freezer.controller;

import com.example.freezer.dto.EmpleadoDTO;
import com.example.freezer.dto.RegisterRequest;
import com.example.freezer.exception.AuthenticationException;
import com.example.freezer.dto.LoginRequest;
import com.example.freezer.exception.RegisterException;
import com.example.freezer.model.Locacion;
import com.example.freezer.service.EmpleadoService;
import com.example.freezer.service.LocacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmpleadosController {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private LocacionService locacionService;

    @PostMapping("/auth")
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

    @DeleteMapping("/empleado/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long id) {
        boolean eliminado = empleadoService.eliminarEmpleado(id);

        if (eliminado) {
            return ResponseEntity.noContent().build(); // 204 No Content si se elimina exitosamente
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found si el empleado no existe
        }
    }

    @PutMapping("/empleado/{id}")
    public ResponseEntity<EmpleadoDTO> editarEmpleado(@PathVariable Long id, @RequestBody EmpleadoDTO empleadoDTO) {
        Optional<EmpleadoDTO> empleadoActualizado = empleadoService.editarEmpleado(id, empleadoDTO);

        return empleadoActualizado
                .map(ResponseEntity::ok) // 200 OK con el empleado actualizado
                .orElseGet(() -> ResponseEntity.notFound().build()); // 404 Not Found si el empleado no existe
    }

    @GetMapping("/locaciones/{id}")
    public ResponseEntity<List<Locacion>> obtenerLocaciones(@PathVariable int id) {

        return ResponseEntity.ok(locacionService.getLocacionByOrganizacion((long) id));
    }
}
