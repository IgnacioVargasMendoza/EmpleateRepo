package com.empleate.controller;

import com.empleate.domain.Usuario;
import com.empleate.service.FirebaseStorageService;
import com.empleate.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var usuarios = usuarioService.getUsuarios();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("totalUsuarios", usuarios.size());
        return "/usuario/listado";
    }

    @GetMapping("/nuevo")
    public String usuarioNuevo(Usuario usuario) {
        return "/usuario/modifica";
    }

    @PostMapping("/guardar")
    public String usuarioGuardar(Usuario usuario,
        @RequestParam("imagenFile") MultipartFile imagenFile) {

        boolean nuevo = true;
        // Validar si es una creación o modificación (Si trae ID)
        if (usuario.getIdUsuario() != null && usuario.getIdUsuario() != 0) {
            nuevo = false;
            Usuario actual = usuarioService.getUsuario(usuario);
            usuario.setPassword(actual.getPassword());
            usuario.setUsername(actual.getUsername());
            usuario.setRoles(actual.getRoles());
            usuario.setActivo(actual.isActivo());
            if (imagenFile.isEmpty()) {
                usuario.setRutaImagen(actual.getRutaImagen());
            }
        } else {
            usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
            usuario.setActivo(true); // Para crearlo siempre activo
        }

        if (!imagenFile.isEmpty()) {
            usuarioService.save(usuario, false);
            usuario.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile,
                            "usuario",
                            usuario.getIdUsuario()));
        }
        usuarioService.save(usuario, nuevo);
        return "redirect:/usuario/listado";
    }

    @GetMapping("/eliminar/{idUsuario}")
    public String usuarioEliminar(Usuario usuario) {
        usuarioService.delete(usuario);
        return "redirect:/usuario/listado";
    }

    @GetMapping("/modificar/{idUsuario}")
    public String usuarioModificar(Usuario usuario, Model model) {
        usuario = usuarioService.getUsuario(usuario);
        model.addAttribute("usuario", usuario);
        return "/usuario/modifica";
    }

//    @PostMapping("/guardar")
//    public String usuarioGuardar(Usuario usuario, @RequestParam("imagenFile") MultipartFile imagenFile) {
//        boolean nuevo = (usuario.getIdUsuario() == null || usuario.getIdUsuario() == 0);
//        Usuario actual = nuevo ? new Usuario() : usuarioService.getUsuario(usuario);
//
//        if (nuevo) {
//            usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
//            usuario.setActivo(true);
//        } else {
//            usuario.setPassword(actual.getPassword());
//            usuario.setUsername(actual.getUsername());
//            usuario.setRoles(actual.getRoles());
//            usuario.setActivo(actual.isActivo());
//            if (imagenFile.isEmpty()) {
//                usuario.setRutaImagen(actual.getRutaImagen());
//            }
//        }
//
//        // Manejo de imagen
//        if (!imagenFile.isEmpty()) {
//            try {
//                String rutaBase = "src/main/resources/static/imagenes/";
//                String nombreArchivo = UUID.randomUUID() + "_" + imagenFile.getOriginalFilename();
//                Path rutaCompleta = Paths.get(rutaBase + nombreArchivo);
//                Files.createDirectories(rutaCompleta.getParent());
//                Files.copy(imagenFile.getInputStream(), rutaCompleta, StandardCopyOption.REPLACE_EXISTING);
//                usuario.setRutaImagen("/imagenes/" + nombreArchivo);
//            } catch (IOException e) {
//                e.printStackTrace();
//                return "error";
//            }
//        }
//
//        usuarioService.saveUsuario(usuario);
//        return "redirect:/usuarios";
//    }
}
