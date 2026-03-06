package com.example.backend_taller.repository;

import com.example.backend_taller.entity.TallerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TallerRepository extends JpaRepository<TallerEntity, Integer> {

    List<TallerEntity> findByActivoTrue();

    boolean existsByNombre(String nombre);

}
