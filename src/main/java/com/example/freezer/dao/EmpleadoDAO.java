package com.example.freezer.dao;

import com.example.freezer.model.Empleado;
import com.example.freezer.model.Organizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoDAO extends JpaRepository<Empleado,Long> {

    List<Empleado> findEmpleadosByOrganizacion(Organizacion organizacion);
}
