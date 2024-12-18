package com.empleate.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "feria_empleo")
public class FeriaEmpleo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFeria;

    private String nombre;
    private String descripcion;

    @Column(name = "fecha_inicio")
    private String fechaInicio;

    @Column(name = "fecha_fin")
    private String fechaFin;

    private String ubicacion;
}
