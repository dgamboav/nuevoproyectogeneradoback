package com.dgamboav.nuevoproyectogeneradoback.servicio.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.HashMap;
import java.util.Optional;
import java.util.Map;
import java.util.List;
import com.dgamboav.nuevoproyectogeneradoback.repositorio.NoConformidadRepository;
import com.dgamboav.nuevoproyectogeneradoback.servicio.NoConformidadService;
import com.dgamboav.nuevoproyectogeneradoback.dto.NoConformidadDTO;
import com.dgamboav.nuevoproyectogeneradoback.entidad.NoConformidad;
import com.dgamboav.nuevoproyectogeneradoback.specification.NoConformidadSpecification;
import com.dgamboav.nuevoproyectogeneradoback.utiles.FilterTypeInferer;
@Service
public class NoConformidadServiceImpl implements NoConformidadService {

    private final FilterTypeInferer filterTypeInferer;
    private final NoConformidadRepository noConformidadRepository;
	public NoConformidadServiceImpl(
        NoConformidadRepository noConformidadRepository,FilterTypeInferer filterTypeInferer
    ) {
        this.noConformidadRepository = noConformidadRepository;
        this.filterTypeInferer = filterTypeInferer;
    }

	@Override
	@Transactional
	public NoConformidadDTO crearNoConformidad(NoConformidadDTO dto) {
		return NoConformidadDTO.toDTO(noConformidadRepository.save(NoConformidadDTO.toDomain(dto)));
	}

    @Override
    public Optional<NoConformidadDTO> obtenerNoConformidadPorId(Long id) {
        return noConformidadRepository.findById(id).map(NoConformidadDTO::toDTO);
    }
	
	@Override
    public Page<NoConformidadDTO> obtenerTodosNoConformidad(Pageable pageable, Map<String, Object> filtros) {
	
	    Map<String, Object> entityFilters = new HashMap<>();
        if (filtros != null) {
            entityFilters.putAll(filtros);
            entityFilters.remove("page");
            entityFilters.remove("size");
        }
	
        NoConformidadSpecification specification = new NoConformidadSpecification(entityFilters, filterTypeInferer);
        Page<NoConformidad> noConformidadPage = noConformidadRepository.findAll(specification, pageable);
        return noConformidadPage.map(NoConformidadDTO::toDTO);
    }
	
	@Override
     public List<NoConformidadDTO> obtenerTodosMinNoConformidad() {
         return noConformidadRepository.findAll().stream()
                 .map(NoConformidadDTO::toDTOMinimo)
                 .toList();
     }

	@Override
	@Transactional
	public Optional<NoConformidadDTO> actualizarNoConformidad(Long id, NoConformidadDTO dto) {
		return noConformidadRepository.findById(id)
				.map(existingNoConformidad -> {
					NoConformidad entidad = NoConformidadDTO.toDomain(dto);
					entidad.setId(id);
					return noConformidadRepository.save(entidad);
				})
				.map(NoConformidadDTO::toDTO);
	}


    @Override
    public void eliminarNoConformidad(Long id) {
        noConformidadRepository.deleteById(id);
    }
}