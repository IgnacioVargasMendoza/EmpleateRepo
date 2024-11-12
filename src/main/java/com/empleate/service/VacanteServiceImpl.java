/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empleate.service;

import com.empleate.dao.VacanteDao;
import com.empleate.domain.Vacante;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ignac
 */
@Service
public class VacanteServiceImpl implements VacanteService{

    @Autowired
    private VacanteDao vacanteDao;

    @Override
    public List<Vacante> listarVacantes() {
        return vacanteDao.findAll();
    }

    @Override
    public Vacante guardarVacante(Vacante vacante) {
        return vacanteDao.save(vacante);
    }

    @Override
    public Vacante obtenerVacantePorId(Long id) {
        return vacanteDao.findById(id).orElse(null);
    }

    @Override
    public void eliminarVacante(Long id) {
        vacanteDao.deleteById(id);
    }

}
