package com.empleate.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String puestoActual;
    private String ciudad;
    private String experiencia;
    private String educacion;
    private String certificaciones;

    @ElementCollection
    private List<String> puestosDesempenados;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPuestoActual() {
        return puestoActual;
    }

    public void setPuestoActual(String puestoActual) {
        this.puestoActual = puestoActual;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getEducacion() {
        return educacion;
    }

    public void setEducacion(String educacion) {
        this.educacion = educacion;
    }

    public String getCertificaciones() {
        return certificaciones;
    }

    public void setCertificaciones(String certificaciones) {
        this.certificaciones = certificaciones;
    }

    public List<String> getPuestosDesempenados() {
        return puestosDesempenados;
    }

    public void setPuestosDesempenados(List<String> puestosDesempenados) {
        this.puestosDesempenados = puestosDesempenados;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
