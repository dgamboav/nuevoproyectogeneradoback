package com.dgamboav.nuevoproyectogeneradoback.servicio;

import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dgamboav.nuevoproyectogeneradoback.dto.ProcesoDTO;
public interface ProcesoService {

    ProcesoDTO crearProceso(ProcesoDTO procesoDTO);

    Optional<ProcesoDTO> obtenerProcesoPorId(Long id);

	Page<ProcesoDTO> obtenerTodosProceso(Pageable pageable, Map<String, Object> filtros);
	
	List<ProcesoDTO> obtenerTodosMinProceso();

    Optional<ProcesoDTO> actualizarProceso(Long id, ProcesoDTO procesoDTO);

    void eliminarProceso(Long id);
}