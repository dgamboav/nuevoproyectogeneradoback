package com.dgamboav.nuevoproyectogeneradoback.servicio.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.HashMap;
import java.util.Optional;
import java.util.Map;
import java.util.List;
import com.dgamboav.nuevoproyectogeneradoback.repositorio.ConformidadRepository;
import com.dgamboav.nuevoproyectogeneradoback.servicio.ConformidadService;
import com.dgamboav.nuevoproyectogeneradoback.dto.ConformidadDTO;
import com.dgamboav.nuevoproyectogeneradoback.entidad.Conformidad;
import com.dgamboav.nuevoproyectogeneradoback.specification.ConformidadSpecification;
import com.dgamboav.nuevoproyectogeneradoback.utiles.FilterTypeInferer;
@Service
public class ConformidadServiceImpl implements ConformidadService {

    private final FilterTypeInferer filterTypeInferer;
    private final ConformidadRepository conformidadRepository;
	public ConformidadServiceImpl(
        ConformidadRepository conformidadRepository,FilterTypeInferer filterTypeInferer
    ) {
        this.conformidadRepository = conformidadRepository;
        this.filterTypeInferer = filterTypeInferer;
    }

	@Override
	@Transactional
	public ConformidadDTO crearConformidad(ConformidadDTO dto) {
		return ConformidadDTO.toDTO(conformidadRepository.save(ConformidadDTO.toDomain(dto)));
	}

    @Override
    public Optional<ConformidadDTO> obtenerConformidadPorId(Long id) {
        return conformidadRepository.findById(id).map(ConformidadDTO::toDTO);
    }
	
	@Override
    public Page<ConformidadDTO> obtenerTodosConformidad(Pageable pageable, Map<String, Object> filtros) {
	
	    Map<String, Object> entityFilters = new HashMap<>();
        if (filtros != null) {
            entityFilters.putAll(filtros);
            entityFilters.remove("page");
            entityFilters.remove("size");
        }
	
        ConformidadSpecification specification = new ConformidadSpecification(entityFilters, filterTypeInferer);
        Page<Conformidad> conformidadPage = conformidadRepository.findAll(specification, pageable);
        return conformidadPage.map(ConformidadDTO::toDTO);
    }
	
	@Override
     public List<ConformidadDTO> obtenerTodosMinConformidad() {
         return conformidadRepository.findAll().stream()
                 .map(ConformidadDTO::toDTOMinimo)
                 .toList();
     }

	@Override
	@Transactional
	public Optional<ConformidadDTO> actualizarConformidad(Long id, ConformidadDTO dto) {
		return conformidadRepository.findById(id)
				.map(existingConformidad -> {
					Conformidad entidad = ConformidadDTO.toDomain(dto);
					entidad.setId(id);
					return conformidadRepository.save(entidad);
				})
				.map(ConformidadDTO::toDTO);
	}


    @Override
    public void eliminarConformidad(Long id) {
        conformidadRepository.deleteById(id);
    }
}