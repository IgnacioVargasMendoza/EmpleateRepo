package com.empleate.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "entrevistas")
public class Entrevista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntrevista;

    @ManyToOne
    @JoinColumn(name = "id_empleo", nullable = false)
    private Empleo empleo;

    @Column(name = "fecha_entrevista")
    private String fechaEntrevista;

    private String direccion;
    private String estado;
}
