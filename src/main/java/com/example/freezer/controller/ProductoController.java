package com.example.freezer.controller;

import com.example.freezer.model.RegistroTemperatura;
import com.example.freezer.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
