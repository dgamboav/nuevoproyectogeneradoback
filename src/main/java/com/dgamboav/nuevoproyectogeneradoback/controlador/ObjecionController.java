package com.dgamboav.nuevoproyectogeneradoback.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.dgamboav.nuevoproyectogeneradoback.dto.ObjecionDTO;
import com.dgamboav.nuevoproyectogeneradoback.servicio.ObjecionService;

@Controller
@RequestMapping("/objecion")
public class ObjecionController {

    public static final String ENTIDAD = "objecion";
	
	public static final String GENERIC_RETURN = "redirect:/";

	private final ObjecionService objecionService;
	
    ObjecionController(ObjecionService objecionService){
        this.objecionService = objecionService;
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute(ENTIDAD, new ObjecionDTO());
        return ENTIDAD + "/crear"; // Nombre de la vista para el formulario de creación
    }

    @PostMapping("/crear")
    public String crearObjecion(@ModelAttribute ObjecionDTO objecion) {
        objecionService.crearObjecion(objecion);
        return GENERIC_RETURN + ENTIDAD; // Redirige a la lista de entidades
    }

    @GetMapping("/{id}")
    public String mostrarObjecion(@PathVariable Long id, Model model) {
        Optional<ObjecionDTO> objecionOptional = objecionService.obtenerObjecionPorId(id);
        if (objecionOptional.isPresent()) {
            ObjecionDTO objecion = objecionOptional.get();
		model.addAttribute(ENTIDAD, objecion);
        return ENTIDAD + "/ver"; // Nombre de la vista para mostrar una entidad
        } else {
            model.addAttribute("mensajeError", "Objecion no encontrado");
            return "error"; // Nombre de una vista de error
        }
		
    }

    @GetMapping
    public String listarObjecion(
			@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Map<String, Object> filtros,
			Model model) {
        Page<ObjecionDTO> objecionPage = objecionService.obtenerTodosObjecion(PageRequest.of(page, size), filtros);
        model.addAttribute("objecions", objecionPage.getContent());
        model.addAttribute("page", objecionPage);
        return ENTIDAD + "/lista"; // Nombre de la vista para la lista de entidades
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<ObjecionDTO> objecion = objecionService.obtenerObjecionPorId(id);
        model.addAttribute(ENTIDAD, objecion);
        return ENTIDAD + "/editar"; // Nombre de la vista para el formulario de edición
    }

    @PostMapping("/editar/{id}")
    public String actualizarObjecion(@PathVariable Long id, @ModelAttribute ObjecionDTO objecion) {
        objecionService.actualizarObjecion(id, objecion);
        return GENERIC_RETURN + ENTIDAD; // Redirige a la lista de entidades
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarObjecion(@PathVariable Long id) {
        objecionService.eliminarObjecion(id);
        return GENERIC_RETURN + ENTIDAD; // Redirige a la lista de entidades
    }
}