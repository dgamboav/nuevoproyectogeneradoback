package com.dgamboav.nuevoproyectogeneradoback.servicio.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.HashMap;
import java.util.Optional;
import java.util.Map;
import java.util.List;
import com.dgamboav.nuevoproyectogeneradoback.repositorio.ProcesoRepository;
import com.dgamboav.nuevoproyectogeneradoback.servicio.ProcesoService;
import com.dgamboav.nuevoproyectogeneradoback.dto.ProcesoDTO;
import com.dgamboav.nuevoproyectogeneradoback.entidad.Proceso;
import com.dgamboav.nuevoproyectogeneradoback.specification.ProcesoSpecification;
import com.dgamboav.nuevoproyectogeneradoback.utiles.FilterTypeInferer;
@Service
public class ProcesoServiceImpl implements ProcesoService {

    private final FilterTypeInferer filterTypeInferer;
    private final ProcesoRepository procesoRepository;
	public ProcesoServiceImpl(
        ProcesoRepository procesoRepository,FilterTypeInferer filterTypeInferer
    ) {
        this.procesoRepository = procesoRepository;
        this.filterTypeInferer = filterTypeInferer;
    }

	@Override
	@Transactional
	public ProcesoDTO crearProceso(ProcesoDTO dto) {
		return ProcesoDTO.toDTO(procesoRepository.save(ProcesoDTO.toDomain(dto)));
	}

    @Override
    public Optional<ProcesoDTO> obtenerProcesoPorId(Long id) {
        return procesoRepository.findById(id).map(ProcesoDTO::toDTO);
    }
	
	@Override
    public Page<ProcesoDTO> obtenerTodosProceso(Pageable pageable, Map<String, Object> filtros) {
	
	    Map<String, Object> entityFilters = new HashMap<>();
        if (filtros != null) {
            entityFilters.putAll(filtros);
            entityFilters.remove("page");
            entityFilters.remove("size");
        }
	
        ProcesoSpecification specification = new ProcesoSpecification(entityFilters, filterTypeInferer);
        Page<Proceso> procesoPage = procesoRepository.findAll(specification, pageable);
        return procesoPage.map(ProcesoDTO::toDTO);
    }
	
	@Override
     public List<ProcesoDTO> obtenerTodosMinProceso() {
         return procesoRepository.findAll().stream()
                 .map(ProcesoDTO::toDTOMinimo)
                 .toList();
     }

	@Override
	@Transactional
	public Optional<ProcesoDTO> actualizarProceso(Long id, ProcesoDTO dto) {
		return procesoRepository.findById(id)
				.map(existingProceso -> {
					Proceso entidad = ProcesoDTO.toDomain(dto);
					entidad.setId(id);
					return procesoRepository.save(entidad);
				})
				.map(ProcesoDTO::toDTO);
	}


    @Override
    public void eliminarProceso(Long id) {
        procesoRepository.deleteById(id);
    }
}