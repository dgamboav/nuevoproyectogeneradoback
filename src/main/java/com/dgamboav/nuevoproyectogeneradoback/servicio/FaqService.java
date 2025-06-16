package com.dgamboav.nuevoproyectogeneradoback.servicio;

import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dgamboav.nuevoproyectogeneradoback.dto.FaqDTO;

public interface FaqService {

    FaqDTO crearFaq(FaqDTO faqDTO);

    Optional<FaqDTO> obtenerFaqPorId(Long id);

	Page<FaqDTO> obtenerTodosFaq(Pageable pageable, Map<String, Object> filtros);
	
	List<FaqDTO> obtenerTodosMinFaq();

    Optional<FaqDTO> actualizarFaq(Long id, FaqDTO faqDTO);

    void eliminarFaq(Long id);
}