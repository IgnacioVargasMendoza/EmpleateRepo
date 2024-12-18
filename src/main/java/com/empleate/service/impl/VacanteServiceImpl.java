package com.empleate.service.impl;

import com.empleate.dao.VacanteDao;
import com.empleate.domain.Vacante;
import com.empleate.service.VacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VacanteServiceImpl implements VacanteService {

    @Autowired
    private VacanteDao vacanteDao;

    @Override
    public List<Vacante> listarVacantes() {
        return vacanteDao.findAll();
    }

    @Override
    public Vacante encontrarVacante(Long idVacante) {
        return vacanteDao.findById(idVacante).orElse(null);
    }

    @Override
    public void guardar(Vacante vacante) {
        vacanteDao.save(vacante);
    }

    @Override
    public void eliminar(Long idVacante) {
        vacanteDao.deleteById(idVacante);
    }
}
