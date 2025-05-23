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

import com.dgamboav.nuevoproyectogeneradoback.servicio.AuditoriaService;
import com.dgamboav.nuevoproyectogeneradoback.dto.AuditoriaDTO;
@RestController
@RequestMapping("/api/auditorias")
public class AuditoriaRestController {
	
	private final AuditoriaService auditoriaService;
	
    AuditoriaRestController(AuditoriaService auditoriaService){
        this.auditoriaService = auditoriaService;
    }

    @PostMapping
    public ResponseEntity<AuditoriaDTO> crearAuditoria(@RequestBody AuditoriaDTO auditoriaDTO) {
        AuditoriaDTO createdAuditoria = auditoriaService.crearAuditoria(auditoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuditoria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditoriaDTO> obtenerAuditoriaPorId(@PathVariable Long id) {
        Optional<AuditoriaDTO> auditoriaDTO = auditoriaService.obtenerAuditoriaPorId(id);
        return auditoriaDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Auditoria no encontrado"));
    }
	
	
	@GetMapping
    public ResponseEntity<PagedModel<AuditoriaDTO>> obtenerTodosAuditoria(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Map<String, Object> filtros
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AuditoriaDTO> auditoriaDTOPage = auditoriaService.obtenerTodosAuditoria(pageable, filtros);
        return ResponseEntity.ok(new PagedModel<>(auditoriaDTOPage));
    }
	
	@GetMapping("/todosMinimo")
     public ResponseEntity<List<AuditoriaDTO>> obtenerTodosMinAuditoria() {
         List<AuditoriaDTO> auditoriaDTOs = auditoriaService.obtenerTodosMinAuditoria();
         return ResponseEntity.ok(auditoriaDTOs);
     }

    @PutMapping("/{id}")
    public ResponseEntity<AuditoriaDTO> actualizarAuditoria(@PathVariable Long id, @RequestBody AuditoriaDTO auditoriaDTO) {
        Optional<AuditoriaDTO> updatedAuditoriaDTO = auditoriaService.actualizarAuditoria(id, auditoriaDTO);
        return updatedAuditoriaDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Auditoria no encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAuditoria(@PathVariable Long id) {
        auditoriaService.eliminarAuditoria(id);
        return ResponseEntity.noContent().build();
    }
}