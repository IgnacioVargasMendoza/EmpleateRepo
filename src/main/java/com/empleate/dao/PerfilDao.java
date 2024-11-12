package com.empleate.dao;

import com.empleate.domain.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilDao extends JpaRepository<Perfil, Long> {

    Perfil findByUsuarioId(Long usuarioId);
}
