package com.example.backend_taller.auth;

import com.example.backend_taller.entity.UsuarioEntity;
import com.example.backend_taller.repository.UsuarioRepository;
import com.example.backend_taller.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginResponseDto login(LoginRequestDto dto){
        UsuarioEntity usuarioEntity = usuarioRepository
                .findByEmail(dto.getEmail())
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(dto.getPassword(), usuarioEntity.getPassword())){
            throw new RuntimeException("Credenciales Incorrectas");
        }

        String token = jwtService.generarToken(
                usuarioEntity.getId(),
                usuarioEntity.getRolEntity().getNombre(),
                usuarioEntity.getTallerEntity() != null? usuarioEntity.getTallerEntity().getId() : null
        );

        return new LoginResponseDto(
                token,
                usuarioEntity.getNombre(),
                usuarioEntity.getRolEntity().getNombre(),
                usuarioEntity.getTallerEntity() != null? usuarioEntity.getTallerEntity().getId() : null
        );
    }
}
