package com.dgamboav.nuevoproyectogeneradoback.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.dgamboav.nuevoproyectogeneradoback.dto.UsuarioDTO;
import com.dgamboav.nuevoproyectogeneradoback.servicio.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    public static final String ENTIDAD = "usuario";
	
	public static final String GENERIC_RETURN = "redirect:/";

	private final UsuarioService usuarioService;
	
    UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute(ENTIDAD, new UsuarioDTO());
        return ENTIDAD + "/crear"; // Nombre de la vista para el formulario de creación
    }

    @PostMapping("/crear")
    public String crearUsuario(@ModelAttribute UsuarioDTO usuario) {
        usuarioService.crearUsuario(usuario);
        return GENERIC_RETURN + ENTIDAD; // Redirige a la lista de entidades
    }

    @GetMapping("/{id}")
    public String mostrarUsuario(@PathVariable Long id, Model model) {
        Optional<UsuarioDTO> usuarioOptional = usuarioService.obtenerUsuarioPorId(id);
        if (usuarioOptional.isPresent()) {
            UsuarioDTO usuario = usuarioOptional.get();
		model.addAttribute(ENTIDAD, usuario);
        return ENTIDAD + "/ver"; // Nombre de la vista para mostrar una entidad
        } else {
            model.addAttribute("mensajeError", "Usuario no encontrado");
            return "error"; // Nombre de una vista de error
        }
		
    }

    @GetMapping
    public String listarUsuario(
			@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Map<String, Object> filtros,
			Model model) {
        Page<UsuarioDTO> usuarioPage = usuarioService.obtenerTodosUsuario(PageRequest.of(page, size), filtros);
        model.addAttribute("usuarios", usuarioPage.getContent());
        model.addAttribute("page", usuarioPage);
        return ENTIDAD + "/lista"; // Nombre de la vista para la lista de entidades
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<UsuarioDTO> usuario = usuarioService.obtenerUsuarioPorId(id);
        model.addAttribute(ENTIDAD, usuario);
        return ENTIDAD + "/editar"; // Nombre de la vista para el formulario de edición
    }

    @PostMapping("/editar/{id}")
    public String actualizarUsuario(@PathVariable Long id, @ModelAttribute UsuarioDTO usuario) {
        usuarioService.actualizarUsuario(id, usuario);
        return GENERIC_RETURN + ENTIDAD; // Redirige a la lista de entidades
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return GENERIC_RETURN + ENTIDAD; // Redirige a la lista de entidades
    }
}