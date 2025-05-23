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

import com.dgamboav.nuevoproyectogeneradoback.servicio.ConformidadService;
import com.dgamboav.nuevoproyectogeneradoback.dto.ConformidadDTO;
@RestController
@RequestMapping("/api/conformidads")
public class ConformidadRestController {
	
	private final ConformidadService conformidadService;
	
    ConformidadRestController(ConformidadService conformidadService){
        this.conformidadService = conformidadService;
    }

    @PostMapping
    public ResponseEntity<ConformidadDTO> crearConformidad(@RequestBody ConformidadDTO conformidadDTO) {
        ConformidadDTO createdConformidad = conformidadService.crearConformidad(conformidadDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdConformidad);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConformidadDTO> obtenerConformidadPorId(@PathVariable Long id) {
        Optional<ConformidadDTO> conformidadDTO = conformidadService.obtenerConformidadPorId(id);
        return conformidadDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Conformidad no encontrado"));
    }
	
	
	@GetMapping
    public ResponseEntity<PagedModel<ConformidadDTO>> obtenerTodosConformidad(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Map<String, Object> filtros
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ConformidadDTO> conformidadDTOPage = conformidadService.obtenerTodosConformidad(pageable, filtros);
        return ResponseEntity.ok(new PagedModel<>(conformidadDTOPage));
    }
	
	@GetMapping("/todosMinimo")
     public ResponseEntity<List<ConformidadDTO>> obtenerTodosMinConformidad() {
         List<ConformidadDTO> conformidadDTOs = conformidadService.obtenerTodosMinConformidad();
         return ResponseEntity.ok(conformidadDTOs);
     }

    @PutMapping("/{id}")
    public ResponseEntity<ConformidadDTO> actualizarConformidad(@PathVariable Long id, @RequestBody ConformidadDTO conformidadDTO) {
        Optional<ConformidadDTO> updatedConformidadDTO = conformidadService.actualizarConformidad(id, conformidadDTO);
        return updatedConformidadDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Conformidad no encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarConformidad(@PathVariable Long id) {
        conformidadService.eliminarConformidad(id);
        return ResponseEntity.noContent().build();
    }
}