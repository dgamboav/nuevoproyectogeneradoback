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

import com.dgamboav.nuevoproyectogeneradoback.servicio.UsuarioService;
import com.dgamboav.nuevoproyectogeneradoback.dto.UsuarioDTO;

@Slf4j
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {
	
	private final UsuarioService usuarioService;
	
    UsuarioRestController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
		log.info("Iniciando la operación de crear Usuario");
        UsuarioDTO createdUsuario = usuarioService.crearUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUsuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable Long id) {
		log.info("Iniciando la operación de obtener Usuario {}", id);
        Optional<UsuarioDTO> usuarioDTO = usuarioService.obtenerUsuarioPorId(id);
        return usuarioDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    }
	
	
	@GetMapping
    public ResponseEntity<PagedModel<UsuarioDTO>> obtenerTodosUsuario(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Map<String, Object> filtros
    ) {
		log.info("Iniciando la operación de obtener listado de Usuario");
        Pageable pageable = PageRequest.of(page, size);
        Page<UsuarioDTO> usuarioDTOPage = usuarioService.obtenerTodosUsuario(pageable, filtros);
        return ResponseEntity.ok(new PagedModel<>(usuarioDTOPage));
    }
	
	@GetMapping("/todosMinimo")
     public ResponseEntity<List<UsuarioDTO>> obtenerTodosMinUsuario() {
		 log.info("Iniciando la operación de obtener listado con valores minimos para reducir payload Usuario");
         List<UsuarioDTO> usuarioDTOs = usuarioService.obtenerTodosMinUsuario();
         return ResponseEntity.ok(usuarioDTOs);
     }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
		log.info("Iniciando la operación de actualizar Usuario Id: {}", id);
        Optional<UsuarioDTO> updatedUsuarioDTO = usuarioService.actualizarUsuario(id, usuarioDTO);
        return updatedUsuarioDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
		log.info("Iniciando la operación de eliminar Usuario Id: {}", id);
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}