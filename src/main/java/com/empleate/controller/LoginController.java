import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.empleate.service.UsuarioService;
import com.empleate.domain.Usuario;
import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    // Mostrar formulario de login
    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "login"; // Nombre del archivo HTML del formulario de login
    }

    // Procesar el formulario de login
    @PostMapping("/login")
    public String procesarLogin(@RequestParam String identificador, @RequestParam String password, Model model) {
        boolean loginExitoso = verificarLogin(identificador, password);
        if (loginExitoso) {
            return "redirect:/perfil"; // Redirigir a la página del perfil del usuario
        } else {
            model.addAttribute("error", "Credenciales inválidas");
            return "login"; // Volver al formulario de login con mensaje de error
        }
    }

    private boolean verificarLogin(String identificador, String password) {
        // Verificar si es correo electrónico o teléfono
        Optional<Usuario> usuarioOpt = usuarioService.obtenerUsuarioPorCorreo(identificador);
        
        if (usuarioOpt.isEmpty()) 
        
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            // Comparar la contraseña
            return usuario.getContrasena().equals(password);
        }
        
        return false; // No se encontró el usuario
    }
}
