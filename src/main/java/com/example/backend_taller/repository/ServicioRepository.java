package com.example.backend_taller.repository;

import com.example.backend_taller.entity.ServicioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioRepository extends JpaRepository<ServicioEntity, Integer> {
}
