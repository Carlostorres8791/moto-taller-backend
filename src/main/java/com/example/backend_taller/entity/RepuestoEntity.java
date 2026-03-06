package com.example.backend_taller.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name= "repuesto")
public class RepuestoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    @Column(name= "precio_unitario")
    private BigDecimal precioUnitario;

    private Boolean activo;

    @ManyToOne
    @JoinColumn(name= "taller_id")
    private TallerEntity tallerEntity;

}
