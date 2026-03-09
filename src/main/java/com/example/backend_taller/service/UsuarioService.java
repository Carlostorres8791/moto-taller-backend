package com.example.backend_taller.service;


import com.example.backend_taller.dtos.requests.UsuarioRequestDto;
import com.example.backend_taller.dtos.responses.UsuarioResponseDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface UsuarioService {

    UsuarioResponseDto crear(UsuarioRequestDto dto, HttpServletRequest request);

    List<UsuarioResponseDto> listar(HttpServletRequest request);

    UsuarioResponseDto buscarPorId(Integer id);

    void eliminar(Integer id);
}
