package com.dgamboav.nuevoproyectogeneradoback.servicio.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.HashMap;
import java.util.Optional;
import java.util.Map;
import java.util.List;
import com.dgamboav.nuevoproyectogeneradoback.repositorio.AccionCorrectivaRepository;
import com.dgamboav.nuevoproyectogeneradoback.servicio.AccionCorrectivaService;
import com.dgamboav.nuevoproyectogeneradoback.dto.AccionCorrectivaDTO;
import com.dgamboav.nuevoproyectogeneradoback.entidad.AccionCorrectiva;
import com.dgamboav.nuevoproyectogeneradoback.specification.AccionCorrectivaSpecification;
import com.dgamboav.nuevoproyectogeneradoback.utiles.FilterTypeInferer;
@Service
public class AccionCorrectivaServiceImpl implements AccionCorrectivaService {

    private final FilterTypeInferer filterTypeInferer;
    private final AccionCorrectivaRepository accionCorrectivaRepository;
	public AccionCorrectivaServiceImpl(
        AccionCorrectivaRepository accionCorrectivaRepository,FilterTypeInferer filterTypeInferer
    ) {
        this.accionCorrectivaRepository = accionCorrectivaRepository;
        this.filterTypeInferer = filterTypeInferer;
    }

	@Override
	@Transactional
	public AccionCorrectivaDTO crearAccionCorrectiva(AccionCorrectivaDTO dto) {
		return AccionCorrectivaDTO.toDTO(accionCorrectivaRepository.save(AccionCorrectivaDTO.toDomain(dto)));
	}

    @Override
    public Optional<AccionCorrectivaDTO> obtenerAccionCorrectivaPorId(Long id) {
        return accionCorrectivaRepository.findById(id).map(AccionCorrectivaDTO::toDTO);
    }
	
	@Override
    public Page<AccionCorrectivaDTO> obtenerTodosAccionCorrectiva(Pageable pageable, Map<String, Object> filtros) {
	
	    Map<String, Object> entityFilters = new HashMap<>();
        if (filtros != null) {
            entityFilters.putAll(filtros);
            entityFilters.remove("page");
            entityFilters.remove("size");
        }
	
        AccionCorrectivaSpecification specification = new AccionCorrectivaSpecification(entityFilters, filterTypeInferer);
        Page<AccionCorrectiva> accionCorrectivaPage = accionCorrectivaRepository.findAll(specification, pageable);
        return accionCorrectivaPage.map(AccionCorrectivaDTO::toDTO);
    }
	
	@Override
     public List<AccionCorrectivaDTO> obtenerTodosMinAccionCorrectiva() {
         return accionCorrectivaRepository.findAll().stream()
                 .map(AccionCorrectivaDTO::toDTOMinimo)
                 .toList();
     }

	@Override
	@Transactional
	public Optional<AccionCorrectivaDTO> actualizarAccionCorrectiva(Long id, AccionCorrectivaDTO dto) {
		return accionCorrectivaRepository.findById(id)
				.map(existingAccionCorrectiva -> {
					AccionCorrectiva entidad = AccionCorrectivaDTO.toDomain(dto);
					entidad.setId(id);
					return accionCorrectivaRepository.save(entidad);
				})
				.map(AccionCorrectivaDTO::toDTO);
	}


    @Override
    public void eliminarAccionCorrectiva(Long id) {
        accionCorrectivaRepository.deleteById(id);
    }
}