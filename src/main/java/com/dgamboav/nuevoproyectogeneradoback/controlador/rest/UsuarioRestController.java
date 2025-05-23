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

import com.dgamboav.nuevoproyectogeneradoback.servicio.UsuarioService;
import com.dgamboav.nuevoproyectogeneradoback.dto.UsuarioDTO;
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {
	
	private final UsuarioService usuarioService;
	
    UsuarioRestController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO createdUsuario = usuarioService.crearUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUsuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable Long id) {
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
        Pageable pageable = PageRequest.of(page, size);
        Page<UsuarioDTO> usuarioDTOPage = usuarioService.obtenerTodosUsuario(pageable, filtros);
        return ResponseEntity.ok(new PagedModel<>(usuarioDTOPage));
    }
	
	@GetMapping("/todosMinimo")
     public ResponseEntity<List<UsuarioDTO>> obtenerTodosMinUsuario() {
         List<UsuarioDTO> usuarioDTOs = usuarioService.obtenerTodosMinUsuario();
         return ResponseEntity.ok(usuarioDTOs);
     }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        Optional<UsuarioDTO> updatedUsuarioDTO = usuarioService.actualizarUsuario(id, usuarioDTO);
        return updatedUsuarioDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}