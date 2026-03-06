package com.example.backend_taller.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequestDto {

    private String nombre;
    private String email;
    private String password;
    private Boolean activo;

    private Integer rolId;
    private Integer tallerId;

}
