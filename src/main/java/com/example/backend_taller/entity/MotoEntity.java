package com.example.backend_taller.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name= "moto")
public class MotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String placa;
    private String marca;
    private String modelo;

    @ManyToOne
    @JoinColumn(name= "cliente_id")
    private ClienteEntity clienteEntity;

    @ManyToOne
    @JoinColumn(name= "taller_id")
    private TallerEntity tallerEntity;

}
