package com.example.freezer.dao;

import com.example.freezer.model.Locacion;
import com.example.freezer.model.Organizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocacionDAO extends JpaRepository<Locacion,Long> {

    List<Locacion> findAllByOrganizacion(Organizacion organizacion);
}
