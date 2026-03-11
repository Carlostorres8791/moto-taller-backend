package com.example.backend_taller.mapper;

import com.example.backend_taller.dtos.responses.TallerResponseDto;
import com.example.backend_taller.entity.TallerEntity;
import org.springframework.stereotype.Component;

@Component
public class TallerMapper {

    public TallerResponseDto toDto(TallerEntity entity){
        TallerResponseDto dto = new TallerResponseDto();

        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setDireccion(entity.getDireccion());
        dto.setTelefono(entity.getTelefono());
        dto.setActivo(entity.getActivo());

        return dto;
    }
}
