package com.example.backend_taller.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TallerUpdateRequestDto {
    private String nombreTaller;
    private String direccion;
    private String telefono;

}
