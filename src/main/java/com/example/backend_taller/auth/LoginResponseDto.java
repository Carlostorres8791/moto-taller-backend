package com.example.backend_taller.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDto {

    private String token;
    private String nombre;
    private String rol;
    private Integer tallerId;

}
