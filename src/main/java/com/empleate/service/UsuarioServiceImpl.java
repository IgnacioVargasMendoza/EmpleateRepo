package com.empleate.service;

import com.empleate.dao.UsuarioDao;
import com.empleate.domain.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioDao.findById(id);
    }

    @Override
    public Usuario registrarUsuario(Usuario usuario) {
        return usuarioDao.save(usuario);
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioDao.findAll();
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorCorreo(String identificador) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}
