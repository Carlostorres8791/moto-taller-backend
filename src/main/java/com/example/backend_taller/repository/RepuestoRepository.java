package com.example.backend_taller.repository;

import com.example.backend_taller.entity.RepuestoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepuestoRepository extends JpaRepository<RepuestoEntity, Integer> {
}
