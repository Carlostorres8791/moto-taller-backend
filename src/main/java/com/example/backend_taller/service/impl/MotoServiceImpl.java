package com.example.backend_taller.service.impl;

import com.example.backend_taller.entity.MotoEntity;
import com.example.backend_taller.repository.MotoRepository;
import com.example.backend_taller.service.MotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MotoServiceImpl implements MotoService {

    private final MotoRepository repository;

    @Override
    public MotoEntity crear(MotoEntity entity){
        return repository.save(entity);
    }

    @Override
    public List<MotoEntity> listar(){
        return repository.findAll();
    }

    @Override
    public Optional<MotoEntity> buscarPorId(Integer id){
        return repository.findById(id);
    }

    @Override
    public void eliminar(Integer id){
        repository.deleteById(id);
    }
}
