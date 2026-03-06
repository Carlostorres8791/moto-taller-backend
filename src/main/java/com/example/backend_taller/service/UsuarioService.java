package com.example.backend_taller.service;


import com.example.backend_taller.dtos.requests.UsuarioRequestDto;
import com.example.backend_taller.dtos.responses.UsuarioResponseDto;

import java.util.List;

public interface UsuarioService {

    UsuarioResponseDto crear(UsuarioRequestDto dto);

    List<UsuarioResponseDto> listar();

    UsuarioResponseDto buscarPorId(Integer id);

    void eliminar(Integer id);
}
