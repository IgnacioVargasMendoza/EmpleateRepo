package com.empleate.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;

@Data //Esta anotacion es importante para utilizar los metodos de hibernate
@Entity
@Table(name = "vacante")
public class Vacante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vacante")
    private long vacanteId;

    private String tituloEmpleo;
    private String descripcion;
    private String cualidadesRequeridas;
    private String educacion;
    private String beneficios;
    private double salario;
    private String ubicacion;

    public Vacante() {
    }

    public Vacante(String tituloEmpleo, String descripcion, String cualidadesRequeridas, String educacion, String beneficios, double salario, String ubicacion) {
        this.tituloEmpleo = tituloEmpleo;
        this.descripcion = descripcion;
        this.cualidadesRequeridas = cualidadesRequeridas;
        this.educacion = educacion;
        this.beneficios = beneficios;
        this.salario = salario;
        this.ubicacion = ubicacion;
    }

}
