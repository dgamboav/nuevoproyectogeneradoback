package com.dgamboav.nuevoproyectogeneradoback.servicio;

import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dgamboav.nuevoproyectogeneradoback.dto.ConformidadDTO;
public interface ConformidadService {

    ConformidadDTO crearConformidad(ConformidadDTO conformidadDTO);

    Optional<ConformidadDTO> obtenerConformidadPorId(Long id);

	Page<ConformidadDTO> obtenerTodosConformidad(Pageable pageable, Map<String, Object> filtros);
	
	List<ConformidadDTO> obtenerTodosMinConformidad();

    Optional<ConformidadDTO> actualizarConformidad(Long id, ConformidadDTO conformidadDTO);

    void eliminarConformidad(Long id);
}