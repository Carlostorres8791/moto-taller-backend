package com.example.backend_taller.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TallerConAdminRequestDto {

    @NotBlank(message = "El campo nombre del taller es obligatorio")
    private String nombreTaller;

    @NotBlank(message = "El campo dirección es obligatorio")
    private String direccion;

    @NotBlank(message = "El campo teléfono es obligatorio")
    private String telefono;

    @NotBlank(message = "El campo nombre del administrador es obligatorio")
    private String nombreAdmin;

    @NotBlank(message = "El campo email del administrador es obligatorio")
    private String emailAdmin;

    @NotBlank(message = "El campo contraseña es obligatorio")
    private String passwordAdmin;

}
