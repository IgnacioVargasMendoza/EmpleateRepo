
package com.empleate.service;

import com.empleate.domain.Perfil;

public interface PerfilService {
    Perfil obtenerPerfilPorUsuario(Long usuarioId);
    Perfil actualizarPerfil(Perfil perfil);
}