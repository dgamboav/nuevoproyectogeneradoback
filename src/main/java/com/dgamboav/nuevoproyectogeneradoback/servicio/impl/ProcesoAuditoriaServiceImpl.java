package com.dgamboav.nuevoproyectogeneradoback.servicio.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.HashMap;
import java.util.Optional;
import java.util.Map;
import java.util.List;
import com.dgamboav.nuevoproyectogeneradoback.repositorio.ProcesoAuditoriaRepository;
import com.dgamboav.nuevoproyectogeneradoback.servicio.ProcesoAuditoriaService;
import com.dgamboav.nuevoproyectogeneradoback.dto.ProcesoAuditoriaDTO;
import com.dgamboav.nuevoproyectogeneradoback.entidad.ProcesoAuditoria;
import com.dgamboav.nuevoproyectogeneradoback.specification.ProcesoAuditoriaSpecification;
import com.dgamboav.nuevoproyectogeneradoback.utiles.FilterTypeInferer;
@Service
public class ProcesoAuditoriaServiceImpl implements ProcesoAuditoriaService {

    private final FilterTypeInferer filterTypeInferer;
    private final ProcesoAuditoriaRepository procesoAuditoriaRepository;
	public ProcesoAuditoriaServiceImpl(
        ProcesoAuditoriaRepository procesoAuditoriaRepository,FilterTypeInferer filterTypeInferer
    ) {
        this.procesoAuditoriaRepository = procesoAuditoriaRepository;
        this.filterTypeInferer = filterTypeInferer;
    }

	@Override
	@Transactional
	public ProcesoAuditoriaDTO crearProcesoAuditoria(ProcesoAuditoriaDTO dto) {
		return ProcesoAuditoriaDTO.toDTO(procesoAuditoriaRepository.save(ProcesoAuditoriaDTO.toDomain(dto)));
	}

    @Override
    public Optional<ProcesoAuditoriaDTO> obtenerProcesoAuditoriaPorId(Long id) {
        return procesoAuditoriaRepository.findById(id).map(ProcesoAuditoriaDTO::toDTO);
    }
	
	@Override
    public Page<ProcesoAuditoriaDTO> obtenerTodosProcesoAuditoria(Pageable pageable, Map<String, Object> filtros) {
	
	    Map<String, Object> entityFilters = new HashMap<>();
        if (filtros != null) {
            entityFilters.putAll(filtros);
            entityFilters.remove("page");
            entityFilters.remove("size");
        }
	
        ProcesoAuditoriaSpecification specification = new ProcesoAuditoriaSpecification(entityFilters, filterTypeInferer);
        Page<ProcesoAuditoria> procesoAuditoriaPage = procesoAuditoriaRepository.findAll(specification, pageable);
        return procesoAuditoriaPage.map(ProcesoAuditoriaDTO::toDTO);
    }
	
	@Override
     public List<ProcesoAuditoriaDTO> obtenerTodosMinProcesoAuditoria() {
         return procesoAuditoriaRepository.findAll().stream()
                 .map(ProcesoAuditoriaDTO::toDTOMinimo)
                 .toList();
     }

	@Override
	@Transactional
	public Optional<ProcesoAuditoriaDTO> actualizarProcesoAuditoria(Long id, ProcesoAuditoriaDTO dto) {
		return procesoAuditoriaRepository.findById(id)
				.map(existingProcesoAuditoria -> {
					ProcesoAuditoria entidad = ProcesoAuditoriaDTO.toDomain(dto);
					entidad.setId(id);
					return procesoAuditoriaRepository.save(entidad);
				})
				.map(ProcesoAuditoriaDTO::toDTO);
	}


    @Override
    public void eliminarProcesoAuditoria(Long id) {
        procesoAuditoriaRepository.deleteById(id);
    }
}