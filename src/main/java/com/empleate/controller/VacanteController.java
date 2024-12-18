package com.empleate.controller;

import com.empleate.domain.Vacante;
import com.empleate.service.VacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VacanteController {

    @Autowired
    private VacanteService vacanteService;

    @GetMapping("/vacantes/listado")
    public String listarVacantes(Model model) {
        model.addAttribute("vacantes", vacanteService.listarVacantes());
        return "vacante/fragmentos";
    }
}
