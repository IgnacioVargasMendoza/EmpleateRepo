package com.empleate.controller;

import com.empleate.domain.Usuario;
import com.empleate.service.FirebaseStorageService;
import com.empleate.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @GetMapping("/perfil")
    public String mostrarPerfil(Model model) {
        // Obtener el usuario autenticado actual
        String username = obtenerUsernameActual();
        Usuario usuario = usuarioService.getUsuarioPorUsername(username);

        if (usuario == null) {
            model.addAttribute("error", "Usuario no encontrado.");
            return "error/usuario-no-encontrado";
        }

        // Pasar toda la informaciÃ³n del usuario al modelo
        model.addAttribute("usuario", usuario);
        return "/perfil/muestra";
    }

    @GetMapping("/perfil/editar/{idUsuario}")
    public String editarPerfil(@PathVariable("idUsuario") Long idUsuario, Model model) {
        // Busca el usuario por ID
        Usuario usuario = usuarioService.getUsuarioPorId(idUsuario);

        // Si el usuario no existe, inicializa uno vacío
        if (usuario == null) {
            return "redirect:/usuario/perfil"; // Redirige si el usuario no existe
        }

        // Agrega el usuario al modelo para la vista
        model.addAttribute("usuario", usuario);

        return "perfil/editar"; // Nombre de la plantilla Thymeleaf (perfil/editar.html)
    }

    @PostMapping("/guardar")
    public String guardarPerfil(@ModelAttribute Usuario usuarioEditado) {
        // Obtener el usuario existente desde la base de datos
        Usuario usuarioExistente = usuarioService.getUsuarioPorId(usuarioEditado.getIdUsuario());

        if (usuarioExistente != null) {
            // Actualizar solo los campos específicos
            usuarioExistente.setPuesto(usuarioEditado.getPuesto());
            usuarioExistente.setCiudad(usuarioEditado.getCiudad());
            usuarioExistente.setDescripcion(usuarioEditado.getDescripcion());
            usuarioExistente.setExperiencia(usuarioEditado.getExperiencia());
            usuarioExistente.setEducacion(usuarioEditado.getEducacion());
            usuarioExistente.setCertificaciones(usuarioEditado.getCertificaciones());
            usuarioExistente.setTrabajos(usuarioEditado.getTrabajos());
            usuarioExistente.setOtrosEstudios(usuarioEditado.getOtrosEstudios());

            // Mantener la ruta de imagen si no se envía una nueva imagen
            if (usuarioEditado.getRutaImagen() != null) {
                usuarioExistente.setRutaImagen(usuarioEditado.getRutaImagen());
            }

            // Guardar el usuario actualizado
            usuarioService.save(usuarioExistente);
        }

        return "perfil/editar"; // Redirigir a la página del perfil
    }

    /*// Guardar perfil editado
    @PostMapping("/guardar")
    public String guardarPerfil(@ModelAttribute Usuario usuario) {
        usuarioService.save(usuario);
        return "redirect:/perfil";
    }*/
    private String obtenerUsernameActual() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
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
