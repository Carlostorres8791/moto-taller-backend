package com.example.backend_taller.util;

import com.example.backend_taller.entity.UsuarioEntity;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static UsuarioEntity getUsuarioActual(){
        return (UsuarioEntity) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    public static Integer getTallerId(){
        UsuarioEntity u = getUsuarioActual();
        return u.getTallerEntity() != null ? u.getTallerEntity().getId() : null;
    }
}
