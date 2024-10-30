package com.example.freezer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.freezer.model.RegistroTemperatura;
import com.example.freezer.service.ProductoService;

@RestController
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping("/productos/{prodID}")
    public List<RegistroTemperatura> getRegistrosTemperaturaById(@PathVariable Long prodID){
        List<RegistroTemperatura> registros = productoService.getHistorialByProducto(prodID);
        for(RegistroTemperatura registroTemperatura : registros){
            System.out.println(registroTemperatura.getEmpleado().getId());
        }
        return productoService.getHistorialByProducto(prodID);
    }

    @GetMapping("/{productoId}/estado")
    public ResponseEntity<String> getEstadoProducto(@PathVariable Long productoId) {
        double promedioTemp = productoService.obtenerPromedioTemperatura(productoId);
        String estado = promedioTemp <= 3.0 ? "Aprobado" : "Desaprobado";
        return ResponseEntity.ok(estado);
    }
}
