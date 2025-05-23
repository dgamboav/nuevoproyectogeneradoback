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

import com.dgamboav.nuevoproyectogeneradoback.servicio.AccionCorrectivaService;
import com.dgamboav.nuevoproyectogeneradoback.dto.AccionCorrectivaDTO;
@RestController
@RequestMapping("/api/accioncorrectivas")
public class AccionCorrectivaRestController {
	
	private final AccionCorrectivaService accionCorrectivaService;
	
    AccionCorrectivaRestController(AccionCorrectivaService accionCorrectivaService){
        this.accionCorrectivaService = accionCorrectivaService;
    }

    @PostMapping
    public ResponseEntity<AccionCorrectivaDTO> crearAccionCorrectiva(@RequestBody AccionCorrectivaDTO accionCorrectivaDTO) {
        AccionCorrectivaDTO createdAccionCorrectiva = accionCorrectivaService.crearAccionCorrectiva(accionCorrectivaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccionCorrectiva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccionCorrectivaDTO> obtenerAccionCorrectivaPorId(@PathVariable Long id) {
        Optional<AccionCorrectivaDTO> accionCorrectivaDTO = accionCorrectivaService.obtenerAccionCorrectivaPorId(id);
        return accionCorrectivaDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("AccionCorrectiva no encontrado"));
    }
	
	
	@GetMapping
    public ResponseEntity<PagedModel<AccionCorrectivaDTO>> obtenerTodosAccionCorrectiva(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Map<String, Object> filtros
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AccionCorrectivaDTO> accionCorrectivaDTOPage = accionCorrectivaService.obtenerTodosAccionCorrectiva(pageable, filtros);
        return ResponseEntity.ok(new PagedModel<>(accionCorrectivaDTOPage));
    }
	
	@GetMapping("/todosMinimo")
     public ResponseEntity<List<AccionCorrectivaDTO>> obtenerTodosMinAccionCorrectiva() {
         List<AccionCorrectivaDTO> accionCorrectivaDTOs = accionCorrectivaService.obtenerTodosMinAccionCorrectiva();
         return ResponseEntity.ok(accionCorrectivaDTOs);
     }

    @PutMapping("/{id}")
    public ResponseEntity<AccionCorrectivaDTO> actualizarAccionCorrectiva(@PathVariable Long id, @RequestBody AccionCorrectivaDTO accionCorrectivaDTO) {
        Optional<AccionCorrectivaDTO> updatedAccionCorrectivaDTO = accionCorrectivaService.actualizarAccionCorrectiva(id, accionCorrectivaDTO);
        return updatedAccionCorrectivaDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("AccionCorrectiva no encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAccionCorrectiva(@PathVariable Long id) {
        accionCorrectivaService.eliminarAccionCorrectiva(id);
        return ResponseEntity.noContent().build();
    }
}