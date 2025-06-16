package com.dgamboav.nuevoproyectogeneradoback.servicio;

import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dgamboav.nuevoproyectogeneradoback.dto.UsuarioDTO;

public interface UsuarioService {

    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);

    Optional<UsuarioDTO> obtenerUsuarioPorId(Long id);

	Page<UsuarioDTO> obtenerTodosUsuario(Pageable pageable, Map<String, Object> filtros);
	
	List<UsuarioDTO> obtenerTodosMinUsuario();

    Optional<UsuarioDTO> actualizarUsuario(Long id, UsuarioDTO usuarioDTO);

    void eliminarUsuario(Long id);
}