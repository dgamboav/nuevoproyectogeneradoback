package com.dgamboav.nuevoproyectogeneradoback.servicio;

import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dgamboav.nuevoproyectogeneradoback.dto.AccionCorrectivaDTO;
public interface AccionCorrectivaService {

    AccionCorrectivaDTO crearAccionCorrectiva(AccionCorrectivaDTO accionCorrectivaDTO);

    Optional<AccionCorrectivaDTO> obtenerAccionCorrectivaPorId(Long id);

	Page<AccionCorrectivaDTO> obtenerTodosAccionCorrectiva(Pageable pageable, Map<String, Object> filtros);
	
	List<AccionCorrectivaDTO> obtenerTodosMinAccionCorrectiva();

    Optional<AccionCorrectivaDTO> actualizarAccionCorrectiva(Long id, AccionCorrectivaDTO accionCorrectivaDTO);

    void eliminarAccionCorrectiva(Long id);
}