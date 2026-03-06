package com.example.backend_taller.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name= "orden_trabajo")
public class OrdenTrabajoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String codigo;

    @Column(name = "fecha_ingreso")
    private LocalDateTime fechaIngreso;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @Column(name = "total_final")
    private BigDecimal totalFinal;

    @ManyToOne
    @JoinColumn(name = "moto_id")
    private MotoEntity motoEntity;

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private UsuarioEntity tecnico;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private EstadoOrdenEntity estado;

    @ManyToOne
    @JoinColumn(name = "taller_id")
    private TallerEntity tallerEntity;
}
