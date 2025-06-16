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
import com.dgamboav.nuevoproyectogeneradoback.repositorio.ProyectoRepository;
import com.dgamboav.nuevoproyectogeneradoback.servicio.ProyectoService;
import com.dgamboav.nuevoproyectogeneradoback.dto.ProyectoDTO;
import com.dgamboav.nuevoproyectogeneradoback.entidad.Proyecto;
import com.dgamboav.nuevoproyectogeneradoback.specification.ProyectoSpecification;
import com.dgamboav.nuevoproyectogeneradoback.utiles.FilterTypeInferer;

@Slf4j
@Service
public class ProyectoServiceImpl implements ProyectoService {

    private final FilterTypeInferer filterTypeInferer;
    private final ProyectoRepository proyectoRepository;
	public ProyectoServiceImpl(
        ProyectoRepository proyectoRepository,FilterTypeInferer filterTypeInferer
    ) {
        this.proyectoRepository = proyectoRepository;
        this.filterTypeInferer = filterTypeInferer;
    }

	@Override
	@Transactional
	public ProyectoDTO crearProyecto(ProyectoDTO dto) {
		log.info("Implementación de la operación de crear Proyecto");
		return ProyectoDTO.toDTO(proyectoRepository.save(ProyectoDTO.toDomain(dto)));
	}

    @Override
	@Transactional
    public Optional<ProyectoDTO> obtenerProyectoPorId(Long id) {
		log.info("Implementación de la operación de obtener Proyecto {}", id);
        return proyectoRepository.findById(id).map(ProyectoDTO::toDTO);
    }
	
	@Override
    public Page<ProyectoDTO> obtenerTodosProyecto(Pageable pageable, Map<String, Object> filtros) {
		log.info("Implementación de la operación de obtener todos Proyecto");
	    Map<String, Object> entityFilters = new HashMap<>();
        if (filtros != null) {
            entityFilters.putAll(filtros);
            entityFilters.remove("page");
            entityFilters.remove("size");
        }
	
        ProyectoSpecification specification = new ProyectoSpecification(entityFilters, filterTypeInferer);
        Page<Proyecto> proyectoPage = proyectoRepository.findAll(specification, pageable);
        return proyectoPage.map(ProyectoDTO::toDTO);
    }
	
	@Override
     public List<ProyectoDTO> obtenerTodosMinProyecto() {
		 log.info("Implementación de la operación para obtener todos los valores minimos de Proyecto");
         return proyectoRepository.findAll().stream()
                 .map(ProyectoDTO::toDTOMinimo)
                 .toList();
     }

	@Override
	@Transactional
	public Optional<ProyectoDTO> actualizarProyecto(Long id, ProyectoDTO dto) {
		log.info("Implementación de la operación de actualizar Proyecto Id: {}", id);
		return proyectoRepository.findById(id)
				.map(existingProyecto -> {
					Proyecto entidad = ProyectoDTO.toDomain(dto);
					BeanUtils.copyProperties(entidad, existingProyecto,"id"
					
					);
					return proyectoRepository.save(existingProyecto);
				})
				.map(ProyectoDTO::toDTO);
	}


    @Override
    public void eliminarProyecto(Long id) {
		log.info("Implementación de la operación de eliminar Proyecto Id: {}", id);
        proyectoRepository.deleteById(id);
    }
}