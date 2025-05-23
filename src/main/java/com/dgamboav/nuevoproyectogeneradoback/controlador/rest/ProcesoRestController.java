package com.dgamboav.nuevoproyectogeneradoback.controlador.rest;

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

import com.dgamboav.nuevoproyectogeneradoback.servicio.ProcesoService;
import com.dgamboav.nuevoproyectogeneradoback.dto.ProcesoDTO;
@RestController
@RequestMapping("/api/procesos")
public class ProcesoRestController {
	
	private final ProcesoService procesoService;
	
    ProcesoRestController(ProcesoService procesoService){
        this.procesoService = procesoService;
    }

    @PostMapping
    public ResponseEntity<ProcesoDTO> crearProceso(@RequestBody ProcesoDTO procesoDTO) {
        ProcesoDTO createdProceso = procesoService.crearProceso(procesoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProceso);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcesoDTO> obtenerProcesoPorId(@PathVariable Long id) {
        Optional<ProcesoDTO> procesoDTO = procesoService.obtenerProcesoPorId(id);
        return procesoDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Proceso no encontrado"));
    }
	
	
	@GetMapping
    public ResponseEntity<PagedModel<ProcesoDTO>> obtenerTodosProceso(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Map<String, Object> filtros
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProcesoDTO> procesoDTOPage = procesoService.obtenerTodosProceso(pageable, filtros);
        return ResponseEntity.ok(new PagedModel<>(procesoDTOPage));
    }
	
	@GetMapping("/todosMinimo")
     public ResponseEntity<List<ProcesoDTO>> obtenerTodosMinProceso() {
         List<ProcesoDTO> procesoDTOs = procesoService.obtenerTodosMinProceso();
         return ResponseEntity.ok(procesoDTOs);
     }

    @PutMapping("/{id}")
    public ResponseEntity<ProcesoDTO> actualizarProceso(@PathVariable Long id, @RequestBody ProcesoDTO procesoDTO) {
        Optional<ProcesoDTO> updatedProcesoDTO = procesoService.actualizarProceso(id, procesoDTO);
        return updatedProcesoDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Proceso no encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProceso(@PathVariable Long id) {
        procesoService.eliminarProceso(id);
        return ResponseEntity.noContent().build();
    }
}