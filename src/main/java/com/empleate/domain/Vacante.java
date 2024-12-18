package com.empleate.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "vacantes")
public class Vacante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVacante;

    @ManyToOne
    @JoinColumn(name = "id_feria", nullable = false)
    private FeriaEmpleo feriaEmpleo;

    private String titulo;
    private String descripcion;
    private String requisitos;
}
