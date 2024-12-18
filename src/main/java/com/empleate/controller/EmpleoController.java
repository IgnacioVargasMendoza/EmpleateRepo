package com.empleate.controller;

import com.empleate.domain.Empleo;
import com.empleate.service.EmpleoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmpleoController {

    @Autowired
    private EmpleoService empleoService;

    @GetMapping("/empleos")
    public String listarEmpleos(Model model) {
        model.addAttribute("empleos", empleoService.listarEmpleos());
        return "empleos/listadoEmpleo";
    }

    @PostMapping("/empleos/guardar")
    public String guardarEmpleo(Empleo empleo) {
        empleoService.guardar(empleo);
        return "redirect:/empleos";
    }
}