package com.dgamboav.nuevoproyectogeneradoback.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.dgamboav.nuevoproyectogeneradoback.dto.ProyectoDTO;
import com.dgamboav.nuevoproyectogeneradoback.servicio.ProyectoService;

@Controller
@RequestMapping("/proyecto")
public class ProyectoController {

    public static final String ENTIDAD = "proyecto";
	
	public static final String GENERIC_RETURN = "redirect:/";

	private final ProyectoService proyectoService;
	
    ProyectoController(ProyectoService proyectoService){
        this.proyectoService = proyectoService;
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute(ENTIDAD, new ProyectoDTO());
        return ENTIDAD + "/crear"; // Nombre de la vista para el formulario de creación
    }

    @PostMapping("/crear")
    public String crearProyecto(@ModelAttribute ProyectoDTO proyecto) {
        proyectoService.crearProyecto(proyecto);
        return GENERIC_RETURN + ENTIDAD; // Redirige a la lista de entidades
    }

    @GetMapping("/{id}")
    public String mostrarProyecto(@PathVariable Long id, Model model) {
        Optional<ProyectoDTO> proyectoOptional = proyectoService.obtenerProyectoPorId(id);
        if (proyectoOptional.isPresent()) {
            ProyectoDTO proyecto = proyectoOptional.get();
		model.addAttribute(ENTIDAD, proyecto);
        return ENTIDAD + "/ver"; // Nombre de la vista para mostrar una entidad
        } else {
            model.addAttribute("mensajeError", "Proyecto no encontrado");
            return "error"; // Nombre de una vista de error
        }
		
    }

    @GetMapping
    public String listarProyecto(
			@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Map<String, Object> filtros,
			Model model) {
        Page<ProyectoDTO> proyectoPage = proyectoService.obtenerTodosProyecto(PageRequest.of(page, size), filtros);
        model.addAttribute("proyectos", proyectoPage.getContent());
        model.addAttribute("page", proyectoPage);
        return ENTIDAD + "/lista"; // Nombre de la vista para la lista de entidades
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<ProyectoDTO> proyecto = proyectoService.obtenerProyectoPorId(id);
        model.addAttribute(ENTIDAD, proyecto);
        return ENTIDAD + "/editar"; // Nombre de la vista para el formulario de edición
    }

    @PostMapping("/editar/{id}")
    public String actualizarProyecto(@PathVariable Long id, @ModelAttribute ProyectoDTO proyecto) {
        proyectoService.actualizarProyecto(id, proyecto);
        return GENERIC_RETURN + ENTIDAD; // Redirige a la lista de entidades
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProyecto(@PathVariable Long id) {
        proyectoService.eliminarProyecto(id);
        return GENERIC_RETURN + ENTIDAD; // Redirige a la lista de entidades
    }
}