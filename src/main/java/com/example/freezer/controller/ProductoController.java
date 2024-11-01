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
    public ResponseEntity<List<RegistroTemperatura>> getRegistrosTemperaturaById(@PathVariable Long prodID) {
        List<RegistroTemperatura> registros = productoService.getHistorialByProducto(prodID);
        if (registros.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(registros);
    }
}
