package com.example.freezer.dao;

import com.example.freezer.model.GrupoProducto;
import com.example.freezer.model.Locacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoProductoDAO extends JpaRepository<GrupoProducto, Long> {

    List<GrupoProducto> findGrupoProductoByLocacion(Locacion locacion);

}
