package com.dgamboav.nuevoproyectogeneradoback.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.dgamboav.nuevoproyectogeneradoback.dto.EmpleadoDTO;
import com.dgamboav.nuevoproyectogeneradoback.servicio.EmpleadoService;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {

    public static final String ENTIDAD = "empleado";
	
	public static final String GENERIC_RETURN = "redirect:/";

	private final EmpleadoService empleadoService;
	
    EmpleadoController(EmpleadoService empleadoService){
        this.empleadoService = empleadoService;
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute(ENTIDAD, new EmpleadoDTO());
        return ENTIDAD + "/crear"; // Nombre de la vista para el formulario de creación
    }

    @PostMapping("/crear")
    public String crearEmpleado(@ModelAttribute EmpleadoDTO empleado) {
        empleadoService.crearEmpleado(empleado);
        return GENERIC_RETURN + ENTIDAD; // Redirige a la lista de entidades
    }

    @GetMapping("/{id}")
    public String mostrarEmpleado(@PathVariable Long id, Model model) {
        Optional<EmpleadoDTO> empleadoOptional = empleadoService.obtenerEmpleadoPorId(id);
        if (empleadoOptional.isPresent()) {
            EmpleadoDTO empleado = empleadoOptional.get();
		model.addAttribute(ENTIDAD, empleado);
        return ENTIDAD + "/ver"; // Nombre de la vista para mostrar una entidad
        } else {
            model.addAttribute("mensajeError", "Empleado no encontrado");
            return "error"; // Nombre de una vista de error
        }
		
    }

    @GetMapping
    public String listarEmpleado(
			@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Map<String, Object> filtros,
			Model model) {
        Page<EmpleadoDTO> empleadoPage = empleadoService.obtenerTodosEmpleado(PageRequest.of(page, size), filtros);
        model.addAttribute("empleados", empleadoPage.getContent());
        model.addAttribute("page", empleadoPage);
        return ENTIDAD + "/lista"; // Nombre de la vista para la lista de entidades
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<EmpleadoDTO> empleado = empleadoService.obtenerEmpleadoPorId(id);
        model.addAttribute(ENTIDAD, empleado);
        return ENTIDAD + "/editar"; // Nombre de la vista para el formulario de edición
    }

    @PostMapping("/editar/{id}")
    public String actualizarEmpleado(@PathVariable Long id, @ModelAttribute EmpleadoDTO empleado) {
        empleadoService.actualizarEmpleado(id, empleado);
        return GENERIC_RETURN + ENTIDAD; // Redirige a la lista de entidades
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable Long id) {
        empleadoService.eliminarEmpleado(id);
        return GENERIC_RETURN + ENTIDAD; // Redirige a la lista de entidades
    }
}