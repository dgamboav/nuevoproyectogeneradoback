package com.dgamboav.nuevoproyectogeneradoback.servicio.impl;

import lombok.extern.slf4j.Slf4j;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.HashMap;
import java.util.Optional;
import java.util.Map;
import java.util.List;
import org.springframework.beans.BeanUtils;
import com.dgamboav.nuevoproyectogeneradoback.repositorio.FaqRepository;
import com.dgamboav.nuevoproyectogeneradoback.servicio.FaqService;
import com.dgamboav.nuevoproyectogeneradoback.dto.FaqDTO;
import com.dgamboav.nuevoproyectogeneradoback.entidad.Faq;
import com.dgamboav.nuevoproyectogeneradoback.specification.FaqSpecification;
import com.dgamboav.nuevoproyectogeneradoback.utiles.FilterTypeInferer;

@Slf4j
@Service
public class FaqServiceImpl implements FaqService {

    private final FilterTypeInferer filterTypeInferer;
    private final FaqRepository faqRepository;
	public FaqServiceImpl(
        FaqRepository faqRepository,FilterTypeInferer filterTypeInferer
    ) {
        this.faqRepository = faqRepository;
        this.filterTypeInferer = filterTypeInferer;
    }

	@Override
	@Transactional
	public FaqDTO crearFaq(FaqDTO dto) {
		log.info("Implementación de la operación de crear Faq");
		return FaqDTO.toDTO(faqRepository.save(FaqDTO.toDomain(dto)));
	}

    @Override
	@Transactional
    public Optional<FaqDTO> obtenerFaqPorId(Long id) {
		log.info("Implementación de la operación de obtener Faq {}", id);
        return faqRepository.findById(id).map(FaqDTO::toDTO);
    }
	
	@Override
    public Page<FaqDTO> obtenerTodosFaq(Pageable pageable, Map<String, Object> filtros) {
		log.info("Implementación de la operación de obtener todos Faq");
	    Map<String, Object> entityFilters = new HashMap<>();
        if (filtros != null) {
            entityFilters.putAll(filtros);
            entityFilters.remove("page");
            entityFilters.remove("size");
        }
	
        FaqSpecification specification = new FaqSpecification(entityFilters, filterTypeInferer);
        Page<Faq> faqPage = faqRepository.findAll(specification, pageable);
        return faqPage.map(FaqDTO::toDTO);
    }
	
	@Override
     public List<FaqDTO> obtenerTodosMinFaq() {
		 log.info("Implementación de la operación para obtener todos los valores minimos de Faq");
         return faqRepository.findAll().stream()
                 .map(FaqDTO::toDTOMinimo)
                 .toList();
     }

	@Override
	@Transactional
	public Optional<FaqDTO> actualizarFaq(Long id, FaqDTO dto) {
		log.info("Implementación de la operación de actualizar Faq Id: {}", id);
		return faqRepository.findById(id)
				.map(existingFaq -> {
					Faq entidad = FaqDTO.toDomain(dto);
					BeanUtils.copyProperties(entidad, existingFaq,"id"
					
					);
					return faqRepository.save(existingFaq);
				})
				.map(FaqDTO::toDTO);
	}


    @Override
    public void eliminarFaq(Long id) {
		log.info("Implementación de la operación de eliminar Faq Id: {}", id);
        faqRepository.deleteById(id);
    }
}