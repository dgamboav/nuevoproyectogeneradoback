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

import com.dgamboav.nuevoproyectogeneradoback.servicio.NoConformidadService;
import com.dgamboav.nuevoproyectogeneradoback.dto.NoConformidadDTO;
@RestController
@RequestMapping("/api/noconformidads")
public class NoConformidadRestController {
	
	private final NoConformidadService noConformidadService;
	
    NoConformidadRestController(NoConformidadService noConformidadService){
        this.noConformidadService = noConformidadService;
    }

    @PostMapping
    public ResponseEntity<NoConformidadDTO> crearNoConformidad(@RequestBody NoConformidadDTO noConformidadDTO) {
        NoConformidadDTO createdNoConformidad = noConformidadService.crearNoConformidad(noConformidadDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNoConformidad);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoConformidadDTO> obtenerNoConformidadPorId(@PathVariable Long id) {
        Optional<NoConformidadDTO> noConformidadDTO = noConformidadService.obtenerNoConformidadPorId(id);
        return noConformidadDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("NoConformidad no encontrado"));
    }
	
	
	@GetMapping
    public ResponseEntity<PagedModel<NoConformidadDTO>> obtenerTodosNoConformidad(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Map<String, Object> filtros
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<NoConformidadDTO> noConformidadDTOPage = noConformidadService.obtenerTodosNoConformidad(pageable, filtros);
        return ResponseEntity.ok(new PagedModel<>(noConformidadDTOPage));
    }
	
	@GetMapping("/todosMinimo")
     public ResponseEntity<List<NoConformidadDTO>> obtenerTodosMinNoConformidad() {
         List<NoConformidadDTO> noConformidadDTOs = noConformidadService.obtenerTodosMinNoConformidad();
         return ResponseEntity.ok(noConformidadDTOs);
     }

    @PutMapping("/{id}")
    public ResponseEntity<NoConformidadDTO> actualizarNoConformidad(@PathVariable Long id, @RequestBody NoConformidadDTO noConformidadDTO) {
        Optional<NoConformidadDTO> updatedNoConformidadDTO = noConformidadService.actualizarNoConformidad(id, noConformidadDTO);
        return updatedNoConformidadDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("NoConformidad no encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNoConformidad(@PathVariable Long id) {
        noConformidadService.eliminarNoConformidad(id);
        return ResponseEntity.noContent().build();
    }
}