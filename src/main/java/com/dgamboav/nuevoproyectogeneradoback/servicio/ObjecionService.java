package com.dgamboav.nuevoproyectogeneradoback.servicio;

import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dgamboav.nuevoproyectogeneradoback.dto.ObjecionDTO;

public interface ObjecionService {

    ObjecionDTO crearObjecion(ObjecionDTO objecionDTO);

    Optional<ObjecionDTO> obtenerObjecionPorId(Long id);

	Page<ObjecionDTO> obtenerTodosObjecion(Pageable pageable, Map<String, Object> filtros);
	
	List<ObjecionDTO> obtenerTodosMinObjecion();

    Optional<ObjecionDTO> actualizarObjecion(Long id, ObjecionDTO objecionDTO);

    void eliminarObjecion(Long id);
}