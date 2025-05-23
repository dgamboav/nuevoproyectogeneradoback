package com.dgamboav.nuevoproyectogeneradoback.servicio;

import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dgamboav.nuevoproyectogeneradoback.dto.NoConformidadDTO;
public interface NoConformidadService {

    NoConformidadDTO crearNoConformidad(NoConformidadDTO noConformidadDTO);

    Optional<NoConformidadDTO> obtenerNoConformidadPorId(Long id);

	Page<NoConformidadDTO> obtenerTodosNoConformidad(Pageable pageable, Map<String, Object> filtros);
	
	List<NoConformidadDTO> obtenerTodosMinNoConformidad();

    Optional<NoConformidadDTO> actualizarNoConformidad(Long id, NoConformidadDTO noConformidadDTO);

    void eliminarNoConformidad(Long id);
}