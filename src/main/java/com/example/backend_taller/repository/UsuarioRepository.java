package com.example.backend_taller.repository;

import com.example.backend_taller.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    Optional<UsuarioEntity> findByEmail(String email);

    List<UsuarioEntity> findByTallerEntityId(Integer tallerId);

    List<UsuarioEntity> findByRolEntityNombre(String nombre);

    List<UsuarioEntity> findByTallerEntityIdAndRolEntityNombre(Integer tallerId, String nombre);

    List<UsuarioEntity> findByTallerEntityIdAndActivoTrue(Integer tallerId);

}
