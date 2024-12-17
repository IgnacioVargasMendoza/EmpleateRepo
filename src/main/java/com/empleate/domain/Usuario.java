package com.empleate.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String correo;
    private String telefono;
    private String rutaImagen;
    private boolean activo = true;
    private String puesto;
    private String ciudad;
    private String descripcion;
    private String experiencia;
    private String educacion;
    private String certificaciones;
    private String trabajos;
    private String otrosEstudios;

    @OneToMany
    @JoinColumn(name = "id_usuario")
    private List<Rol> roles;

    public Usuario() {
    }

    
    public Usuario(Long idUsuario, String username, String password, String nombre, String apellidos, LocalDate fechaNacimiento, String correo, String telefono, String rutaImagen, String puesto, String ciudad, String descripcion, String experiencia, String educacion, String certificaciones, String trabajos, String otrosEstudios, List<Rol> roles) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.telefono = telefono;
        this.rutaImagen = rutaImagen;
        this.puesto = puesto;
        this.ciudad = ciudad;
        this.descripcion = descripcion;
        this.experiencia = experiencia;
        this.educacion = educacion;
        this.certificaciones = certificaciones;
        this.trabajos = trabajos;
        this.otrosEstudios = otrosEstudios;
        this.roles = roles;
    }
    
}
