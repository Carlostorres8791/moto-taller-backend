package com.example.backend_taller.repository;

import com.example.backend_taller.entity.EstadoOrdenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoOrdenRepository extends JpaRepository<EstadoOrdenEntity, Integer> {
    Optional<EstadoOrdenEntity> findByNombre(String nombre);
}
