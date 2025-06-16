package com.dgamboav.nuevoproyectogeneradoback.controlador.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.web.PagedModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.dgamboav.nuevoproyectogeneradoback.servicio.EmpleadoService;
import com.dgamboav.nuevoproyectogeneradoback.dto.EmpleadoDTO;

@Slf4j
@RestController
@RequestMapping("/api/empleados")
public class EmpleadoRestController {
	
	private final EmpleadoService empleadoService;
	
    EmpleadoRestController(EmpleadoService empleadoService){
        this.empleadoService = empleadoService;
    }

    @PostMapping
    public ResponseEntity<EmpleadoDTO> crearEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {
		log.info("Iniciando la operación de crear Empleado");
        EmpleadoDTO createdEmpleado = empleadoService.crearEmpleado(empleadoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmpleado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> obtenerEmpleadoPorId(@PathVariable Long id) {
		log.info("Iniciando la operación de obtener Empleado {}", id);
        Optional<EmpleadoDTO> empleadoDTO = empleadoService.obtenerEmpleadoPorId(id);
        return empleadoDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado"));
    }
	
	
	@GetMapping
    public ResponseEntity<PagedModel<EmpleadoDTO>> obtenerTodosEmpleado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Map<String, Object> filtros
    ) {
		log.info("Iniciando la operación de obtener listado de Empleado");
        Pageable pageable = PageRequest.of(page, size);
        Page<EmpleadoDTO> empleadoDTOPage = empleadoService.obtenerTodosEmpleado(pageable, filtros);
        return ResponseEntity.ok(new PagedModel<>(empleadoDTOPage));
    }
	
	@GetMapping("/todosMinimo")
     public ResponseEntity<List<EmpleadoDTO>> obtenerTodosMinEmpleado() {
		 log.info("Iniciando la operación de obtener listado con valores minimos para reducir payload Empleado");
         List<EmpleadoDTO> empleadoDTOs = empleadoService.obtenerTodosMinEmpleado();
         return ResponseEntity.ok(empleadoDTOs);
     }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> actualizarEmpleado(@PathVariable Long id, @RequestBody EmpleadoDTO empleadoDTO) {
		log.info("Iniciando la operación de actualizar Empleado Id: {}", id);
        Optional<EmpleadoDTO> updatedEmpleadoDTO = empleadoService.actualizarEmpleado(id, empleadoDTO);
        return updatedEmpleadoDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long id) {
		log.info("Iniciando la operación de eliminar Empleado Id: {}", id);
        empleadoService.eliminarEmpleado(id);
        return ResponseEntity.noContent().build();
    }
}