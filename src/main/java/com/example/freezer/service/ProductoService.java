package com.example.freezer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.freezer.dao.ProductoDAO;
import com.example.freezer.dao.RegistroTemperaturaDAO;
import com.example.freezer.model.Producto;
import com.example.freezer.model.RegistroTemperatura;

@Service
public class ProductoService {

    @Autowired
    private ProductoDAO productoDAO;

    @Autowired
    private RegistroTemperaturaDAO registroTemperaturaDAO;

    public List<RegistroTemperatura> getHistorialByProducto(Long prodID){
        Optional<Producto> productoBuscado = productoDAO.findById(prodID);

        return registroTemperaturaDAO.findRegistroTemperaturasByProductoOrderByFechaHora(productoBuscado.get());
    }

    public double obtenerPromedioTemperatura(Long productoId) {
        // Simula el cálculo del promedio desde la base de datos
        return 2.8; // Ejemplo de valor, reemplazar con la lógica de cálculo real
    }
}
