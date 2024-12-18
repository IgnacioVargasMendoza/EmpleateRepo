package com.empleate.controller;

import com.empleate.domain.FeriaEmpleo;
import com.empleate.service.FeriaEmpleoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FeriaEmpleoController {

    @Autowired
    private FeriaEmpleoService feriaEmpleoService;

    @GetMapping("/feriaEmpleo/listado")
    public String listarFerias(Model model) {
        model.addAttribute("ferias", feriaEmpleoService.listarFerias());
        return "feriaEmpleo/ListaEventos";
    }
}
