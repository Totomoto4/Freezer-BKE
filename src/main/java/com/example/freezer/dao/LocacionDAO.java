package com.example.freezer.dao;

import com.example.freezer.model.Locacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocacionDAO extends JpaRepository<Locacion,Long> {
}