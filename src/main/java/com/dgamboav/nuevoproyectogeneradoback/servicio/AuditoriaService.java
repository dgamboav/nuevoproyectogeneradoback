package com.dgamboav.nuevoproyectogeneradoback.servicio;

import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dgamboav.nuevoproyectogeneradoback.dto.AuditoriaDTO;
public interface AuditoriaService {

    AuditoriaDTO crearAuditoria(AuditoriaDTO auditoriaDTO);

    Optional<AuditoriaDTO> obtenerAuditoriaPorId(Long id);

	Page<AuditoriaDTO> obtenerTodosAuditoria(Pageable pageable, Map<String, Object> filtros);
	
	List<AuditoriaDTO> obtenerTodosMinAuditoria();

    Optional<AuditoriaDTO> actualizarAuditoria(Long id, AuditoriaDTO auditoriaDTO);

    void eliminarAuditoria(Long id);
}