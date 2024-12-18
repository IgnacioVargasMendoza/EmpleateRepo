package com.empleate.service.impl;

import com.empleate.dao.FeriaEmpleoDao;
import com.empleate.domain.FeriaEmpleo;
import com.empleate.service.FeriaEmpleoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FeriaEmpleoServiceImpl implements FeriaEmpleoService {

    @Autowired
    private FeriaEmpleoDao feriaEmpleoDao;

    @Override
    public List<FeriaEmpleo> listarFerias() {
        return feriaEmpleoDao.findAll();
    }

    @Override
    public FeriaEmpleo encontrarFeria(Long idFeria) {
        return feriaEmpleoDao.findById(idFeria).orElse(null);
    }

    @Override
    public void guardar(FeriaEmpleo feriaEmpleo) {
        feriaEmpleoDao.save(feriaEmpleo);
    }

    @Override
    public void eliminar(Long idFeria) {
        feriaEmpleoDao.deleteById(idFeria);
    }
}
