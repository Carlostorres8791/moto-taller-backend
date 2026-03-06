package com.example.backend_taller.repository;

import com.example.backend_taller.entity.MotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MotoRepository extends JpaRepository<MotoEntity, Integer> {
    Optional<MotoEntity> findByPlaca(String placa);
}
