package com.empleate.controller;

import com.empleate.domain.Perfil;
import com.empleate.domain.Usuario;
import com.empleate.service.PerfilService;
import com.empleate.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PerfilService perfilService;

    @GetMapping("/ver")
    public String verPerfil(Model model) {

        Long usuarioId = 1L;
        Usuario usuario = usuarioService.obtenerUsuarioPorId(usuarioId).orElse(new Usuario());
        Perfil perfil = perfilService.obtenerPerfilPorUsuario(usuarioId);

        model.addAttribute("usuario", usuario);
        model.addAttribute("perfil", perfil);
        return "perfil";
    }

    @GetMapping("/ver/{id}")
    public String verPerfil(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id).orElse(new Usuario());
        List<Usuario> perfiles = usuarioService.obtenerTodosLosUsuarios();

        model.addAttribute("usuario", usuario);
        model.addAttribute("perfil", usuario.getPerfil());
        model.addAttribute("perfiles", perfiles);
        model.addAttribute("pageTitle", "Perfil de Empleado");
        model.addAttribute("pageContent", "perfil.html :: pageContent");

        return "base";
    }

    @PostMapping("/actualizar")
    public String actualizarPerfil(Usuario usuario, Perfil perfil) {
        usuarioService.registrarUsuario(usuario);
        perfilService.actualizarPerfil(perfil);
        return "redirect:/perfil/ver";
    }
}
