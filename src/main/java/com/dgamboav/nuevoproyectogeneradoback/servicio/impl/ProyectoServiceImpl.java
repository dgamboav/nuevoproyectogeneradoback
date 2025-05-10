package com.dgamboav.nuevoproyectogeneradoback.servicio.impl;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.HashMap;
import java.util.Optional;
import java.util.Map;
import java.util.List;

import com.dgamboav.nuevoproyectogeneradoback.repositorio.ProyectoRepository;
import com.dgamboav.nuevoproyectogeneradoback.servicio.ProyectoService;
import com.dgamboav.nuevoproyectogeneradoback.dto.ProyectoDTO;
import com.dgamboav.nuevoproyectogeneradoback.entidad.Proyecto;
import com.dgamboav.nuevoproyectogeneradoback.specification.ProyectoSpecification;
import com.dgamboav.nuevoproyectogeneradoback.utiles.FilterTypeInferer;

@Service
public class ProyectoServiceImpl implements ProyectoService {

    private final FilterTypeInferer filterTypeInferer;
    private final ProyectoRepository proyectoRepository;
	
	ProyectoServiceImpl(ProyectoRepository proyectoRepository, FilterTypeInferer filterTypeInferer){
        this.proyectoRepository = proyectoRepository;
		this.filterTypeInferer = filterTypeInferer;
    }

    @Override
    public ProyectoDTO crearProyecto(ProyectoDTO proyectoDTO) {
        return ProyectoDTO.toDTO(proyectoRepository.save(ProyectoDTO.toDomain(proyectoDTO)));
    }

    @Override
    public Optional<ProyectoDTO> obtenerProyectoPorId(Long id) {
        return proyectoRepository.findById(id).map(ProyectoDTO::toDTO);
    }
	
	@Override
    public Page<ProyectoDTO> obtenerTodosProyecto(Pageable pageable, Map<String, Object> filtros) {
	
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
         return proyectoRepository.findAll().stream()
                 .map(ProyectoDTO::toDTOMinimo)
                 .toList();
     }

    @Override
    public Optional<ProyectoDTO> actualizarProyecto(Long id, ProyectoDTO proyectoDTO) {
        return proyectoRepository.findById(id)
                .map(existingProyecto -> {
                    Proyecto proyecto = ProyectoDTO.toDomain(proyectoDTO);
                    proyecto.setId(id);
                    return proyectoRepository.save(proyecto);
                })
                .map(ProyectoDTO::toDTO);
    }

    @Override
    public void eliminarProyecto(Long id) {
        proyectoRepository.deleteById(id);
    }
}