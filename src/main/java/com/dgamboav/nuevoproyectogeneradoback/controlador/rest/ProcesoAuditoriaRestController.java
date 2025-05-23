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

import com.dgamboav.nuevoproyectogeneradoback.servicio.ProcesoAuditoriaService;
import com.dgamboav.nuevoproyectogeneradoback.dto.ProcesoAuditoriaDTO;
@RestController
@RequestMapping("/api/procesoauditorias")
public class ProcesoAuditoriaRestController {
	
	private final ProcesoAuditoriaService procesoAuditoriaService;
	
    ProcesoAuditoriaRestController(ProcesoAuditoriaService procesoAuditoriaService){
        this.procesoAuditoriaService = procesoAuditoriaService;
    }

    @PostMapping
    public ResponseEntity<ProcesoAuditoriaDTO> crearProcesoAuditoria(@RequestBody ProcesoAuditoriaDTO procesoAuditoriaDTO) {
        ProcesoAuditoriaDTO createdProcesoAuditoria = procesoAuditoriaService.crearProcesoAuditoria(procesoAuditoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProcesoAuditoria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcesoAuditoriaDTO> obtenerProcesoAuditoriaPorId(@PathVariable Long id) {
        Optional<ProcesoAuditoriaDTO> procesoAuditoriaDTO = procesoAuditoriaService.obtenerProcesoAuditoriaPorId(id);
        return procesoAuditoriaDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("ProcesoAuditoria no encontrado"));
    }
	
	
	@GetMapping
    public ResponseEntity<PagedModel<ProcesoAuditoriaDTO>> obtenerTodosProcesoAuditoria(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Map<String, Object> filtros
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProcesoAuditoriaDTO> procesoAuditoriaDTOPage = procesoAuditoriaService.obtenerTodosProcesoAuditoria(pageable, filtros);
        return ResponseEntity.ok(new PagedModel<>(procesoAuditoriaDTOPage));
    }
	
	@GetMapping("/todosMinimo")
     public ResponseEntity<List<ProcesoAuditoriaDTO>> obtenerTodosMinProcesoAuditoria() {
         List<ProcesoAuditoriaDTO> procesoAuditoriaDTOs = procesoAuditoriaService.obtenerTodosMinProcesoAuditoria();
         return ResponseEntity.ok(procesoAuditoriaDTOs);
     }

    @PutMapping("/{id}")
    public ResponseEntity<ProcesoAuditoriaDTO> actualizarProcesoAuditoria(@PathVariable Long id, @RequestBody ProcesoAuditoriaDTO procesoAuditoriaDTO) {
        Optional<ProcesoAuditoriaDTO> updatedProcesoAuditoriaDTO = procesoAuditoriaService.actualizarProcesoAuditoria(id, procesoAuditoriaDTO);
        return updatedProcesoAuditoriaDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("ProcesoAuditoria no encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProcesoAuditoria(@PathVariable Long id) {
        procesoAuditoriaService.eliminarProcesoAuditoria(id);
        return ResponseEntity.noContent().build();
    }
}