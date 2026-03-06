package com.example.backend_taller.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "orden_servicio")
public class OrdenServicioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "orden_id")
    private OrdenTrabajoEntity orden;

    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private ServicioEntity servicioEntity;

    @Column(name = "precio_aplicado")
    private BigDecimal precioAplicado;

}
