package com.example.backend_taller.service.impl;

import com.example.backend_taller.dtos.requests.TallerConAdminRequestDto;
import com.example.backend_taller.dtos.requests.TallerUpdateRequestDto;
import com.example.backend_taller.dtos.responses.TallerResponseDto;
import com.example.backend_taller.entity.RolEntity;
import com.example.backend_taller.entity.TallerEntity;
import com.example.backend_taller.entity.UsuarioEntity;
import com.example.backend_taller.exception.ResourceNotFoundException;
import com.example.backend_taller.mapper.TallerMapper;
import com.example.backend_taller.repository.RolRepository;
import com.example.backend_taller.repository.TallerRepository;
import com.example.backend_taller.repository.UsuarioRepository;
import com.example.backend_taller.service.TallerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TallerServiceImpl implements TallerService {

    private final TallerRepository tallerRepository;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final TallerMapper mapper;

    @Override
    public TallerResponseDto crearTallerConAdmin(TallerConAdminRequestDto dto){
        if(tallerRepository.existsByNombre(dto.getNombreTaller())){
            throw new IllegalArgumentException("Ya existe un taller con ese nombre");
        }

        if (usuarioRepository.findByEmail(dto.getEmailAdmin()).isPresent()) {
            throw new IllegalArgumentException("El email del Admin ya esta registrado");
        }

        RolEntity rolEntityAdmin = rolRepository.findByNombre("ADMIN_TALLER").orElseThrow(()-> new ResourceNotFoundException("Rol ADMIN_TALLER no encontrado"));

        TallerEntity tallerEntity = new TallerEntity();

        tallerEntity.setNombre(dto.getNombreTaller());
        tallerEntity.setDireccion(dto.getDireccion());
        tallerEntity.setTelefono(dto.getTelefono());
        tallerEntity.setActivo(true);

        TallerEntity tallerEntityGuardado = tallerRepository.save(tallerEntity);

        UsuarioEntity admin = new UsuarioEntity();
        admin.setNombre(dto.getNombreAdmin());
        admin.setEmail(dto.getEmailAdmin());
        admin.setPassword(passwordEncoder.encode(dto.getPasswordAdmin()));
        admin.setRolEntity(rolEntityAdmin);
        admin.setTallerEntity(tallerEntityGuardado);
        admin.setActivo(true);

        usuarioRepository.save(admin);

        TallerResponseDto response = new TallerResponseDto();
        response.setId(tallerEntityGuardado.getId());
        response.setNombre(tallerEntityGuardado.getNombre());
        response.setDireccion(tallerEntityGuardado.getDireccion());
        response.setTelefono(tallerEntityGuardado.getTelefono());
        response.setActivo(tallerEntityGuardado.getActivo());
        response.setNombreAdmin(admin.getNombre());
        response.setEmailAdmin(admin.getEmail());

        return response;
    }

    @Override
    public TallerResponseDto actualizar(Integer id, TallerUpdateRequestDto dto){

        TallerEntity taller = tallerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Taller no encontrado"));

        taller.setNombre(dto.getNombreTaller());
        taller.setDireccion(dto.getDireccion());
        taller.setTelefono(dto.getTelefono());

        TallerEntity actualizado = tallerRepository.save(taller);

        return mapper.toDto(actualizado);
    }

    @Override
    public List<TallerResponseDto> listarActivos(){
        return tallerRepository.findByActivoTrue().stream().map(t ->{
            TallerResponseDto dto = new TallerResponseDto();
            dto.setId(t.getId());
            dto.setNombre(t.getNombre());
            dto.setDireccion(t.getDireccion());
            dto.setTelefono(t.getTelefono());
            dto.setActivo(t.getActivo());
            return dto;
        })
                .toList();
    }

    @Override
    public List<TallerResponseDto> listar(){
        return tallerRepository.findAll().stream().map(t ->{
                    TallerResponseDto dto = new TallerResponseDto();
                    dto.setId(t.getId());
                    dto.setNombre(t.getNombre());
                    dto.setDireccion(t.getDireccion());
                    dto.setTelefono(t.getTelefono());
                    dto.setActivo(t.getActivo());
                    return dto;
                })
                .toList();
    }


    @Override
    public TallerResponseDto buscarPorId(Integer id){
        TallerEntity tallerEntity = tallerRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Taller no encontrado"));

        TallerResponseDto dto = new TallerResponseDto();

        dto.setId(tallerEntity.getId());
        dto.setNombre(tallerEntity.getNombre());
        dto.setDireccion(tallerEntity.getDireccion());
        dto.setTelefono(tallerEntity.getTelefono());
        dto.setActivo(tallerEntity.getActivo());

        return dto;
    }

    @Override
    public void desactivar(Integer id) {
        TallerEntity tallerEntity = tallerRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Taller no encontrado "));

        tallerEntity.setActivo(false);
        tallerRepository.save(tallerEntity);
    }

    @Override
    public void activar(Integer id){
        TallerEntity tallerEntity = tallerRepository.findById(id)
                .orElseThrow(()-> new ResolutionException("Taller activado"));

        tallerEntity.setActivo(true);
        tallerRepository.save(tallerEntity);
    }

    @Override
    public void eliminar(Integer id){
        TallerEntity tallerEntity = tallerRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Taller no encontrado"));

        tallerRepository.deleteById(id);
    }
}
