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

import com.dgamboav.nuevoproyectogeneradoback.servicio.EmpresaService;
import com.dgamboav.nuevoproyectogeneradoback.dto.EmpresaDTO;
@RestController
@RequestMapping("/api/empresas")
public class EmpresaRestController {
	
	private final EmpresaService empresaService;
	
    EmpresaRestController(EmpresaService empresaService){
        this.empresaService = empresaService;
    }

    @PostMapping
    public ResponseEntity<EmpresaDTO> crearEmpresa(@RequestBody EmpresaDTO empresaDTO) {
        EmpresaDTO createdEmpresa = empresaService.crearEmpresa(empresaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmpresa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO> obtenerEmpresaPorId(@PathVariable Long id) {
        Optional<EmpresaDTO> empresaDTO = empresaService.obtenerEmpresaPorId(id);
        return empresaDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrado"));
    }
	
	
	@GetMapping
    public ResponseEntity<PagedModel<EmpresaDTO>> obtenerTodosEmpresa(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Map<String, Object> filtros
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EmpresaDTO> empresaDTOPage = empresaService.obtenerTodosEmpresa(pageable, filtros);
        return ResponseEntity.ok(new PagedModel<>(empresaDTOPage));
    }
	
	@GetMapping("/todosMinimo")
     public ResponseEntity<List<EmpresaDTO>> obtenerTodosMinEmpresa() {
         List<EmpresaDTO> empresaDTOs = empresaService.obtenerTodosMinEmpresa();
         return ResponseEntity.ok(empresaDTOs);
     }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDTO> actualizarEmpresa(@PathVariable Long id, @RequestBody EmpresaDTO empresaDTO) {
        Optional<EmpresaDTO> updatedEmpresaDTO = empresaService.actualizarEmpresa(id, empresaDTO);
        return updatedEmpresaDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable Long id) {
        empresaService.eliminarEmpresa(id);
        return ResponseEntity.noContent().build();
    }
}