package com.example.backend_taller.controller;

import com.example.backend_taller.dtos.requests.TallerConAdminRequestDto;
import com.example.backend_taller.dtos.requests.TallerUpdateRequestDto;
import com.example.backend_taller.dtos.responses.TallerResponseDto;
import com.example.backend_taller.service.TallerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/talleres")
@RequiredArgsConstructor
@PreAuthorize("hasRole('SUPER_ADMIN')")
public class TallerController {

    private final TallerService service;

    @PostMapping
    public TallerResponseDto crear(@Valid @RequestBody TallerConAdminRequestDto taller){
        return service.crearTallerConAdmin(taller);
    }

    @PutMapping("/{id}")
    public TallerResponseDto actualizar(@PathVariable Integer id, @RequestBody TallerUpdateRequestDto dto){
        return service.actualizar(id, dto);
    }

    @GetMapping("/activos")
    public List<TallerResponseDto> listarActivos(){
        return service.listarActivos();
    }

    @GetMapping
    public List<TallerResponseDto> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public TallerResponseDto buscar(@PathVariable Integer id){
        return service.buscarPorId(id);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id){
        service.eliminar(id);
    }

    @DeleteMapping("/desactivar/{id}")
    public void desactivar(@PathVariable Integer id){
        service.desactivar(id);
    }

    @PutMapping("/activar/{id}")
    public void activar(@PathVariable Integer id) { service.activar(id); }

}
