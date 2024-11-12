/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/Controller.java to edit this template
 */
package com.empleate.controller;

import com.empleate.domain.Vacante;
import com.empleate.service.VacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Ignac
 */
@Controller
@RequestMapping("/vacante")
public class VacanteController {

    @Autowired
    private VacanteService vacanteService;

    @GetMapping("/crear")
    public String mostrarFormularioCrearVacante(Model model) {
        model.addAttribute("vacante", new Vacante());
        return "vacante"; 
    }

    @PostMapping("/guardar")
    public String guardarVacante(@ModelAttribute Vacante vacante) {
        vacanteService.guardarVacante(vacante);
        return "redirect:/vacante"; 
    }

    @GetMapping
    public String listarVacantes(Model model) {
        model.addAttribute("vacantes", vacanteService.listarVacantes());
        return "listaVacantes"; 
    }

}
