package com.example.backend_taller.repository;

import com.example.backend_taller.entity.OrdenTrabajoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrdenTrabajoRepository extends JpaRepository<OrdenTrabajoEntity, Integer> {
    Optional<OrdenTrabajoEntity> findByCodigo(String codigo);
}
