package com.example.backend_taller.controller;


import com.example.backend_taller.entity.ClienteEntity;
import com.example.backend_taller.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public ClienteEntity crear(@RequestBody ClienteEntity clienteEntity){
        return service.crear(clienteEntity);
    }

    @GetMapping
    public List<ClienteEntity> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public ClienteEntity buscar(@PathVariable Integer id){
        return service.buscarPorId(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id){
        service.eliminar(id);
    }

}
