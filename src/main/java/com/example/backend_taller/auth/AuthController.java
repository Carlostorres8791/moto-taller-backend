package com.example.backend_taller.auth;


import com.example.backend_taller.entity.UsuarioEntity;
import com.example.backend_taller.exception.ResourceNotFoundException;
import com.example.backend_taller.repository.UsuarioRepository;
import com.example.backend_taller.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto req){

        UsuarioEntity user = usuarioRepository
                .findByEmail(req.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no existe"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())){
            return ResponseEntity.status(401).body("Password incorrecto");
        }

        Integer tallerId = null;

        if (!user.getRolEntity().getNombre().equals("SUPER_ADMIN")) {
            if (user.getTallerEntity() == null) {
                throw new RuntimeException("Usuario sin taller asignado es un Super Admin");
            }
            tallerId = user.getTallerEntity().getId();
        }

        String token = jwtService.generarToken(
                user.getId(),
                user.getRolEntity().getNombre(),
                tallerId
        );

        return ResponseEntity.ok(token);
    }

}
