package com.example.backend_taller.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private Boolean activo = true;

    @ManyToOne(optional = false)
    @JoinColumn(name= "rol_id", nullable = false)
    private RolEntity rolEntity;

    @ManyToOne
    @JoinColumn(name= "taller_id")
    private TallerEntity tallerEntity;

}
