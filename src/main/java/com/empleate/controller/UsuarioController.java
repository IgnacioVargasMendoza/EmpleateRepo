
package com.empleate.controller;

import com.empleate.domain.Usuario;
import com.empleate.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(Usuario usuario) {
        usuarioService.registrarUsuario(usuario);
        return "redirect:/usuarios/registro";
    }
    
    @PostMapping("/registroU")
    public String usuarioRegistro(Usuario usuario) {
        usuarioService.registrarUsuario(usuario);
        return "redirect:/usuarios/registro";
    }
}