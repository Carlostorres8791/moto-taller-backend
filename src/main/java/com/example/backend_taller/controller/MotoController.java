package com.example.backend_taller.controller;

import com.example.backend_taller.entity.MotoEntity;
import com.example.backend_taller.service.MotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/motos")
@RequiredArgsConstructor
public class MotoController {

    private final MotoService service;

    @PostMapping()
    public MotoEntity crear(@RequestBody MotoEntity motoEntity){
        return service.crear(motoEntity);
    }

    @GetMapping
    public List<MotoEntity> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public MotoEntity buscar(@PathVariable Integer id){
        return service.buscarPorId(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id){
        service.eliminar(id);
    }
}
