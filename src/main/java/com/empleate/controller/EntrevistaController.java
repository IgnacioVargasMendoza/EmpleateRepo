package com.empleate.controller;

import com.empleate.domain.Entrevista;
import com.empleate.service.EntrevistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EntrevistaController {

    @Autowired
    private EntrevistaService entrevistaService;

@GetMapping("/entrevistas/gestionar")
public String gestionarEntrevistas(Model model) {
    model.addAttribute("entrevistas", entrevistaService.listarEntrevistas());
    model.addAttribute("entrevista", new Entrevista()); // Para el formulario de agendar entrevistas
    return "entrevista/gestionarEntrevistas";
}

}
