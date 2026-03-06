package com.example.backend_taller.service;

import com.example.backend_taller.dtos.requests.TallerConAdminRequestDto;
import com.example.backend_taller.dtos.responses.TallerResponseDto;

import java.util.List;

public interface TallerService {
    TallerResponseDto crearTallerConAdmin(TallerConAdminRequestDto dto);

    List<TallerResponseDto> listar();

    List<TallerResponseDto> listarActivos();

    TallerResponseDto buscarPorId(Integer id);

    void desactivar(Integer id);

    void eliminar(Integer id);
}
