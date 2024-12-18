package com.empleate.service.impl;

import com.empleate.dao.EntrevistaDao;
import com.empleate.domain.Entrevista;
import com.empleate.service.EntrevistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EntrevistaServiceImpl implements EntrevistaService {

    @Autowired
    private EntrevistaDao entrevistaDao;

    @Override
    public List<Entrevista> listarEntrevistas() {
        return entrevistaDao.findAll();
    }

    @Override
    public Entrevista encontrarEntrevista(Long idEntrevista) {
        return entrevistaDao.findById(idEntrevista).orElse(null);
    }

    @Override
    public void guardar(Entrevista entrevista) {
        entrevistaDao.save(entrevista);
    }

    @Override
    public void eliminar(Long idEntrevista) {
        entrevistaDao.deleteById(idEntrevista);
    }
}
