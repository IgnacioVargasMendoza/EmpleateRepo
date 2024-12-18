package com.empleate.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "empleos")
public class Empleo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpleo;

    private String titulo;
    private String descripcion;
    private String ubicacion;
    private Double salario;

    @Column(name = "fecha_publicacion")
    private String fechaPublicacion;

    @Column(name = "fecha_expiracion")
    private String fechaExpiracion;
}
