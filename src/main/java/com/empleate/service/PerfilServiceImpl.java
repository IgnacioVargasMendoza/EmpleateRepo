package com.empleate.service;

import com.empleate.dao.PerfilDao;
import com.empleate.domain.Perfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilServiceImpl implements PerfilService {

    @Autowired
    private PerfilDao perfilDao;

    @Override
    public Perfil obtenerPerfilPorUsuario(Long usuarioId) {
        return perfilDao.findByUsuarioId(usuarioId);
    }

    @Override
    public Perfil actualizarPerfil(Perfil perfil) {
        return perfilDao.save(perfil);
    }
}
