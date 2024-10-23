package com.example.freezer.dao;

import com.example.freezer.model.Producto;
import com.example.freezer.model.RegistroTemperatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroTemperaturaDAO extends JpaRepository<RegistroTemperatura,Long> {

    List<RegistroTemperatura> findRegistroTemperaturasByProductoOrderByFechaHora(Producto producto);
}
