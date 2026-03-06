package com.example.backend_taller.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponseDto {

    private Integer id;
    private String nombre;
    private String email;
    private Boolean activo;

    private String rolNombre;
    private String tallerNombre;

}
