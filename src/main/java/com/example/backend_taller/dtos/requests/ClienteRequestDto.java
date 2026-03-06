package com.example.backend_taller.dtos.requests;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRequestDto {

    private String nombre;
    private String telefono;
    private String documento;
    private Integer tallerId;

}
