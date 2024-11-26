package com.empleate.service;

import com.empleate.domain.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Optional<Usuario> obtenerUsuarioPorId(Long id);

    Usuario registrarUsuario(Usuario usuario);

    // Método para obtener todos los usuarios
    List<Usuario> obtenerTodosLosUsuarios();

    public Optional<Usuario> obtenerUsuarioPorCorreo(String identificador);

}
