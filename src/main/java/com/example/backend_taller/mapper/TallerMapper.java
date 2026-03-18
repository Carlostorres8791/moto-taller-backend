package com.example.backend_taller.mapper;

import com.example.backend_taller.dtos.responses.TallerResponseDto;
import com.example.backend_taller.entity.TallerEntity;
import com.example.backend_taller.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class TallerMapper {

    public TallerResponseDto toDto(TallerEntity tallerEntity, UsuarioEntity usuarioEntity){
        TallerResponseDto dto = new TallerResponseDto();

        dto.setId(tallerEntity.getId());
        dto.setNombre(tallerEntity.getNombre());
        dto.setDireccion(tallerEntity.getDireccion());
        dto.setTelefono(tallerEntity.getTelefono());
        dto.setActivo(tallerEntity.getActivo());
        if (usuarioEntity != null) {

            dto.setNombreAdmin(usuarioEntity.getNombre());
            dto.setEmailAdmin(usuarioEntity.getEmail());
        }else {
            dto.setNombreAdmin(null);
            dto.setEmailAdmin(null);
        }
        return dto;
    }
}
