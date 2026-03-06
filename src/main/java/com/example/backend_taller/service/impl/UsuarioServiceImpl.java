package com.example.backend_taller.service.impl;

import com.example.backend_taller.dtos.requests.UsuarioRequestDto;
import com.example.backend_taller.dtos.responses.UsuarioResponseDto;
import com.example.backend_taller.entity.RolEntity;
import com.example.backend_taller.entity.TallerEntity;
import com.example.backend_taller.entity.UsuarioEntity;
import com.example.backend_taller.exception.ResourceNotFoundException;
import com.example.backend_taller.mapper.UsuarioMapper;
import com.example.backend_taller.repository.RolRepository;
import com.example.backend_taller.repository.TallerRepository;
import com.example.backend_taller.repository.UsuarioRepository;
import com.example.backend_taller.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final TallerRepository tallerRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuarioResponseDto crear(UsuarioRequestDto dto){

        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya esta registrado");
        }

        RolEntity rolEntity = rolRepository.findById(dto.getRolId()).orElseThrow(()-> new ResourceNotFoundException("Rol no encontrado"));

        TallerEntity tallerEntity = tallerRepository.findById(dto.getTallerId()).orElseThrow(()-> new ResourceNotFoundException("Taller no encontrado"));

        if (!tallerEntity.getActivo()) {
            throw new ResourceNotFoundException("El taller está inactivo");
        }

        UsuarioEntity usuarioEntity = usuarioMapper.toEntity(dto, rolEntity, tallerEntity);

        if (rolEntity.getNombre().equals("ADMIN_TALLER")) {
            if (!usuarioRepository.findByTallerEntityIdAndRolEntityNombre(tallerEntity.getId(), "ADMIN_TALLER").isEmpty()) {
                throw new ResourceNotFoundException("El taller ya tiene un administrador asignado");
            }
        }

        //Encripta contraseña
        usuarioEntity.setPassword(passwordEncoder.encode(dto.getPassword()));

        UsuarioEntity guardado = usuarioRepository.save(usuarioEntity);

        return usuarioMapper.toResponseDto(guardado);
    }

    @Override
    public List<UsuarioResponseDto> listar(){
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toResponseDto)
                .toList();
    }

    @Override
    public UsuarioResponseDto buscarPorId(Integer id){
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Usuario con id " + id + " no encontrado"));

        return usuarioMapper.toResponseDto(usuarioEntity);
    }

    @Override
    public void eliminar(Integer id){

        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario con id " + id + " no encontrado");
        }
        usuarioRepository.deleteById(id);
    }


}
