package com.dgamboav.nuevoproyectogeneradoback.servicio;

import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dgamboav.nuevoproyectogeneradoback.dto.ProyectoDTO;

public interface ProyectoService {

    ProyectoDTO crearProyecto(ProyectoDTO proyectoDTO);

    Optional<ProyectoDTO> obtenerProyectoPorId(Long id);

	Page<ProyectoDTO> obtenerTodosProyecto(Pageable pageable, Map<String, Object> filtros);
	
	List<ProyectoDTO> obtenerTodosMinProyecto();

    Optional<ProyectoDTO> actualizarProyecto(Long id, ProyectoDTO proyectoDTO);

    void eliminarProyecto(Long id);
}