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
import jakarta.servlet.http.HttpServletRequest;
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
    public UsuarioResponseDto crear(UsuarioRequestDto dto, HttpServletRequest request){

        String rolLogueado = (String) request.getAttribute("rol");
        Integer tallerIdToken = (Integer) request.getAttribute("tallerId");

        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya esta registrado");
        }

        RolEntity rolEntity = rolRepository.findById(dto.getRolId()).orElseThrow(()-> new ResourceNotFoundException("Rol no encontrado"));

        if ("SUPER_ADMIN".equals(rolLogueado)) {
            if (dto.getTallerId() == null) {
                throw new IllegalArgumentException("Debe de especificar el taller");
            }
        }

        if ("ADMIN_TALLER".equals(rolLogueado)) {

            dto.setTallerId(tallerIdToken.intValue());

            if ("SUPER_ADMIN".equals(rolEntity.getNombre()) || "ADMIN_TALLER".equals(rolEntity.getNombre())) {
                throw new IllegalArgumentException("No tienes permiso para crear ese rol");
            }
        }

        if ("RECEPCIONISTA".equals(rolLogueado)) {

            if (!"CLIENTE".equals(rolEntity.getNombre())) {

                throw new IllegalArgumentException("Recepcionista solo puede crear clientes");
            }

            dto.setTallerId(tallerIdToken.intValue());
        }

        TallerEntity tallerEntity = tallerRepository.findById(dto.getTallerId()).orElseThrow(()-> new ResourceNotFoundException("Taller no encontrado"));

        if (!tallerEntity.getActivo()) {
            throw new ResourceNotFoundException("El taller está inactivo");
        }

        if ("ADMIN_TALLER".equals(rolEntity.getNombre())) {

            if (!usuarioRepository
                    .findByTallerEntityIdAndRolEntityNombre(tallerEntity.getId(), "ADMIN_TALLER")
                    .isEmpty()) {
                throw new IllegalArgumentException("El taller ya tiene un administrador asignado");
            }
        }

        UsuarioEntity usuarioEntity = usuarioMapper.toEntity(dto, rolEntity, tallerEntity);
        //Encripta contraseña
        usuarioEntity.setPassword(passwordEncoder.encode(dto.getPassword()));

        UsuarioEntity guardado = usuarioRepository.save(usuarioEntity);

        return usuarioMapper.toResponseDto(guardado);
    }

    @Override
    public List<UsuarioResponseDto> listar(HttpServletRequest request){
       String rolLogueado = (String) request.getAttribute("rol");
       Long tallerIdLong = (Long) request.getAttribute("tallerId");
       Integer tallerIdToken = tallerIdLong.intValue();

       List<UsuarioEntity> usuarios;
        if ("SUPER_ADMIN".equals(rolLogueado)) {
            usuarios = usuarioRepository.findAll();
        } else if ("ADMIN_TALLER".equals(rolLogueado)) {
            usuarios = usuarioRepository.findByTallerEntityId(tallerIdToken);
        } else if ("RECEPCIONISTA".equals(rolLogueado)) {
            usuarios = usuarioRepository
                    .findByTallerEntityIdAndRolEntityNombre(tallerIdToken, "CLIENTE");
        }else {
            throw new IllegalArgumentException("No tienes permisos para listar usuarios ");
        }

        return usuarios
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
