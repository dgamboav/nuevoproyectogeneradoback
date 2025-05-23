package com.dgamboav.nuevoproyectogeneradoback.servicio;

import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dgamboav.nuevoproyectogeneradoback.dto.ProcesoAuditoriaDTO;
public interface ProcesoAuditoriaService {

    ProcesoAuditoriaDTO crearProcesoAuditoria(ProcesoAuditoriaDTO procesoAuditoriaDTO);

    Optional<ProcesoAuditoriaDTO> obtenerProcesoAuditoriaPorId(Long id);

	Page<ProcesoAuditoriaDTO> obtenerTodosProcesoAuditoria(Pageable pageable, Map<String, Object> filtros);
	
	List<ProcesoAuditoriaDTO> obtenerTodosMinProcesoAuditoria();

    Optional<ProcesoAuditoriaDTO> actualizarProcesoAuditoria(Long id, ProcesoAuditoriaDTO procesoAuditoriaDTO);

    void eliminarProcesoAuditoria(Long id);
}