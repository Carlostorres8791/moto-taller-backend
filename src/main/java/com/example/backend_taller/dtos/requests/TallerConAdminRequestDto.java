package com.example.backend_taller.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TallerConAdminRequestDto {

    private String nombreTaller;
    private String direccion;
    private String telefono;

    private String nombreAdmin;
    private String emailAdmin;
    private String passwordAdmin;

}
