package com.example.freezer.dao;

import com.example.freezer.model.Organizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizacionDAO extends JpaRepository<Organizacion, Long> {
}
