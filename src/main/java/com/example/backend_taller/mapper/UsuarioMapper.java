package com.example.backend_taller.mapper;

import com.example.backend_taller.dtos.requests.UsuarioRequestDto;
import com.example.backend_taller.dtos.responses.UsuarioResponseDto;
import com.example.backend_taller.entity.RolEntity;
import com.example.backend_taller.entity.TallerEntity;
import com.example.backend_taller.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    // DTO -> ENTITY

    public UsuarioEntity toEntity(UsuarioRequestDto dto, RolEntity rolEntity, TallerEntity tallerEntity){
        UsuarioEntity usuarioEntity = new UsuarioEntity();

        usuarioEntity.setNombre(dto.getNombre());
        usuarioEntity.setEmail(dto.getEmail());
        usuarioEntity.setActivo(dto.getActivo());
        usuarioEntity.setRolEntity(rolEntity);
        usuarioEntity.setTallerEntity(tallerEntity);
        return usuarioEntity;
    }

    // ENTITY -> DTO

    public UsuarioResponseDto toResponseDto(UsuarioEntity usuarioEntity){
        UsuarioResponseDto dto = new UsuarioResponseDto();

        dto.setId(usuarioEntity.getId());
        dto.setNombre(usuarioEntity.getNombre());
        dto.setEmail(usuarioEntity.getEmail());
        dto.setActivo(usuarioEntity.getActivo());
        dto.setRolNombre(usuarioEntity.getRolEntity() != null ? usuarioEntity.getRolEntity().getNombre() : null);
        dto.setTallerNombre(usuarioEntity.getTallerEntity() != null ? usuarioEntity.getTallerEntity().getNombre() : null);
        return dto;
    }
}
