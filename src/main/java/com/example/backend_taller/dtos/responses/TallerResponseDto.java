package com.example.backend_taller.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TallerResponseDto {
    private Integer id;
    private String nombre;
    private String direccion;
    private String telefono;
    private boolean activo;

    private String nombreAdmin;
    private String emailAdmin;

}
