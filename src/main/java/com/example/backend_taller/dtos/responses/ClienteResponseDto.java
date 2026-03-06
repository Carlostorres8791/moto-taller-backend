package com.example.backend_taller.dtos.responses;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteResponseDto {

    private Integer id;
    private String nombre;
    private String telefono;
    private String documento;

}
