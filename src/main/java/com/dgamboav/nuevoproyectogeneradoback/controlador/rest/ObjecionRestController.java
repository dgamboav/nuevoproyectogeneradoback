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

import com.dgamboav.nuevoproyectogeneradoback.servicio.ObjecionService;
import com.dgamboav.nuevoproyectogeneradoback.dto.ObjecionDTO;

@RestController
@RequestMapping("/api/objecions")
public class ObjecionRestController {
	
	private final ObjecionService objecionService;
	
    ObjecionRestController(ObjecionService objecionService){
        this.objecionService = objecionService;
    }

    @PostMapping
    public ResponseEntity<ObjecionDTO> crearObjecion(@RequestBody ObjecionDTO objecionDTO) {
        ObjecionDTO createdObjecion = objecionService.crearObjecion(objecionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdObjecion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjecionDTO> obtenerObjecionPorId(@PathVariable Long id) {
        Optional<ObjecionDTO> objecionDTO = objecionService.obtenerObjecionPorId(id);
        return objecionDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Objecion no encontrado"));
    }
	
	
	@GetMapping
    public ResponseEntity<PagedModel<ObjecionDTO>> obtenerTodosObjecion(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Map<String, Object> filtros
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ObjecionDTO> objecionDTOPage = objecionService.obtenerTodosObjecion(pageable, filtros);
        return ResponseEntity.ok(new PagedModel<>(objecionDTOPage));
    }
	
	@GetMapping("/todosMinimo")
     public ResponseEntity<List<ObjecionDTO>> obtenerTodosMinObjecion() {
         List<ObjecionDTO> objecionDTOs = objecionService.obtenerTodosMinObjecion();
         return ResponseEntity.ok(objecionDTOs);
     }

    @PutMapping("/{id}")
    public ResponseEntity<ObjecionDTO> actualizarObjecion(@PathVariable Long id, @RequestBody ObjecionDTO objecionDTO) {
        Optional<ObjecionDTO> updatedObjecionDTO = objecionService.actualizarObjecion(id, objecionDTO);
        return updatedObjecionDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Objecion no encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarObjecion(@PathVariable Long id) {
        objecionService.eliminarObjecion(id);
        return ResponseEntity.noContent().build();
    }
}