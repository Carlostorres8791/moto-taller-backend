package com.example.backend_taller.service.impl;

import com.example.backend_taller.entity.ClienteEntity;
import com.example.backend_taller.repository.ClienteRepository;
import com.example.backend_taller.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;

    @Override
    public ClienteEntity crear(ClienteEntity entity){
        return repository.save(entity);
    }

    @Override
    public List<ClienteEntity> listar(){
        return repository.findAll();
    }

    @Override
    public Optional<ClienteEntity> buscarPorId(Integer id){
        return repository.findById(id);
    }

    @Override
    public void eliminar(Integer id){
        repository.deleteById(id);
    }
}
