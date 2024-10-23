package com.example.freezer.dao;

import com.example.freezer.model.GrupoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoProductoDAO extends JpaRepository<GrupoProducto, Long> {
}
