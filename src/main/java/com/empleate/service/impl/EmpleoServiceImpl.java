package com.empleate.service.impl;

import com.empleate.dao.EmpleoDao;
import com.empleate.domain.Empleo;
import com.empleate.service.EmpleoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleoServiceImpl implements EmpleoService {

    @Autowired
    private EmpleoDao empleoDao;

    @Override
    public List<Empleo> listarEmpleos() {
        return empleoDao.findAll();
    }

    @Override
    public void guardar(Empleo empleo) {
        empleoDao.save(empleo);
    }
}
