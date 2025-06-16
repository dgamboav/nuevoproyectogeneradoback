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

import com.dgamboav.nuevoproyectogeneradoback.servicio.ProyectoService;
import com.dgamboav.nuevoproyectogeneradoback.dto.ProyectoDTO;

@Slf4j
@RestController
@RequestMapping("/api/proyectos")
public class ProyectoRestController {
	
	private final ProyectoService proyectoService;
	
    ProyectoRestController(ProyectoService proyectoService){
        this.proyectoService = proyectoService;
    }

    @PostMapping
    public ResponseEntity<ProyectoDTO> crearProyecto(@RequestBody ProyectoDTO proyectoDTO) {
		log.info("Iniciando la operación de crear Proyecto");
        ProyectoDTO createdProyecto = proyectoService.crearProyecto(proyectoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProyecto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProyectoDTO> obtenerProyectoPorId(@PathVariable Long id) {
		log.info("Iniciando la operación de obtener Proyecto {}", id);
        Optional<ProyectoDTO> proyectoDTO = proyectoService.obtenerProyectoPorId(id);
        return proyectoDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Proyecto no encontrado"));
    }
	
	
	@GetMapping
    public ResponseEntity<PagedModel<ProyectoDTO>> obtenerTodosProyecto(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Map<String, Object> filtros
    ) {
		log.info("Iniciando la operación de obtener listado de Proyecto");
        Pageable pageable = PageRequest.of(page, size);
        Page<ProyectoDTO> proyectoDTOPage = proyectoService.obtenerTodosProyecto(pageable, filtros);
        return ResponseEntity.ok(new PagedModel<>(proyectoDTOPage));
    }
	
	@GetMapping("/todosMinimo")
     public ResponseEntity<List<ProyectoDTO>> obtenerTodosMinProyecto() {
		 log.info("Iniciando la operación de obtener listado con valores minimos para reducir payload Proyecto");
         List<ProyectoDTO> proyectoDTOs = proyectoService.obtenerTodosMinProyecto();
         return ResponseEntity.ok(proyectoDTOs);
     }

    @PutMapping("/{id}")
    public ResponseEntity<ProyectoDTO> actualizarProyecto(@PathVariable Long id, @RequestBody ProyectoDTO proyectoDTO) {
		log.info("Iniciando la operación de actualizar Proyecto Id: {}", id);
        Optional<ProyectoDTO> updatedProyectoDTO = proyectoService.actualizarProyecto(id, proyectoDTO);
        return updatedProyectoDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Proyecto no encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProyecto(@PathVariable Long id) {
		log.info("Iniciando la operación de eliminar Proyecto Id: {}", id);
        proyectoService.eliminarProyecto(id);
        return ResponseEntity.noContent().build();
    }
}