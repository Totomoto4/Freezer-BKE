package com.example.freezer.dao;

import com.example.freezer.model.GrupoProducto;
import com.example.freezer.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoDAO extends JpaRepository<Producto,Long> {

    List<Producto> findProductosByGrupoProducto(GrupoProducto grupoProducto);

}
