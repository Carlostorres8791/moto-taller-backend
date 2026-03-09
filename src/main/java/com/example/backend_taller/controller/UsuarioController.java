package com.example.backend_taller.controller;

import com.example.backend_taller.dtos.requests.UsuarioRequestDto;
import com.example.backend_taller.dtos.responses.UsuarioResponseDto;
import com.example.backend_taller.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    public UsuarioResponseDto crear(@RequestBody UsuarioRequestDto dto, HttpServletRequest request){
        return service.crear(dto, request);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> listar(HttpServletRequest request){
        return ResponseEntity.ok(service.listar(request));
    }

    @GetMapping("/{id}")
    public UsuarioResponseDto buscar(@PathVariable Integer id){
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id){
        service.eliminar(id);
    }
}
