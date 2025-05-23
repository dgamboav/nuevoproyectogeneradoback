package com.dgamboav.nuevoproyectogeneradoback.servicio.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.HashMap;
import java.util.Optional;
import java.util.Map;
import java.util.List;
import com.dgamboav.nuevoproyectogeneradoback.repositorio.AuditoriaRepository;
import com.dgamboav.nuevoproyectogeneradoback.servicio.AuditoriaService;
import com.dgamboav.nuevoproyectogeneradoback.dto.AuditoriaDTO;
import com.dgamboav.nuevoproyectogeneradoback.entidad.Auditoria;
import com.dgamboav.nuevoproyectogeneradoback.specification.AuditoriaSpecification;
import com.dgamboav.nuevoproyectogeneradoback.utiles.FilterTypeInferer;
@Service
public class AuditoriaServiceImpl implements AuditoriaService {

    private final FilterTypeInferer filterTypeInferer;
    private final AuditoriaRepository auditoriaRepository;
	public AuditoriaServiceImpl(
        AuditoriaRepository auditoriaRepository,FilterTypeInferer filterTypeInferer
    ) {
        this.auditoriaRepository = auditoriaRepository;
        this.filterTypeInferer = filterTypeInferer;
    }

	@Override
	@Transactional
	public AuditoriaDTO crearAuditoria(AuditoriaDTO dto) {
		return AuditoriaDTO.toDTO(auditoriaRepository.save(AuditoriaDTO.toDomain(dto)));
	}

    @Override
    public Optional<AuditoriaDTO> obtenerAuditoriaPorId(Long id) {
        return auditoriaRepository.findById(id).map(AuditoriaDTO::toDTO);
    }
	
	@Override
    public Page<AuditoriaDTO> obtenerTodosAuditoria(Pageable pageable, Map<String, Object> filtros) {
	
	    Map<String, Object> entityFilters = new HashMap<>();
        if (filtros != null) {
            entityFilters.putAll(filtros);
            entityFilters.remove("page");
            entityFilters.remove("size");
        }
	
        AuditoriaSpecification specification = new AuditoriaSpecification(entityFilters, filterTypeInferer);
        Page<Auditoria> auditoriaPage = auditoriaRepository.findAll(specification, pageable);
        return auditoriaPage.map(AuditoriaDTO::toDTO);
    }
	
	@Override
     public List<AuditoriaDTO> obtenerTodosMinAuditoria() {
         return auditoriaRepository.findAll().stream()
                 .map(AuditoriaDTO::toDTOMinimo)
                 .toList();
     }

	@Override
	@Transactional
	public Optional<AuditoriaDTO> actualizarAuditoria(Long id, AuditoriaDTO dto) {
		return auditoriaRepository.findById(id)
				.map(existingAuditoria -> {
					Auditoria entidad = AuditoriaDTO.toDomain(dto);
					entidad.setId(id);
					return auditoriaRepository.save(entidad);
				})
				.map(AuditoriaDTO::toDTO);
	}


    @Override
    public void eliminarAuditoria(Long id) {
        auditoriaRepository.deleteById(id);
    }
}