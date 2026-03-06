package com.example.backend_taller.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name= "servicio")
public class ServicioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String descripcion;

    @Column(name= "precio_base")
    private BigDecimal precioBase;

    private Boolean activo;

    @ManyToOne
    @JoinColumn(name= "taller_id")
    private TallerEntity tallerEntity;

}
