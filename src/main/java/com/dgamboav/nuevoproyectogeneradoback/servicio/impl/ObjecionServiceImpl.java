package com.dgamboav.nuevoproyectogeneradoback.servicio.impl;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.HashMap;
import java.util.Optional;
import java.util.Map;
import java.util.List;

import com.dgamboav.nuevoproyectogeneradoback.repositorio.ObjecionRepository;
import com.dgamboav.nuevoproyectogeneradoback.servicio.ObjecionService;
import com.dgamboav.nuevoproyectogeneradoback.dto.ObjecionDTO;
import com.dgamboav.nuevoproyectogeneradoback.entidad.Objecion;
import com.dgamboav.nuevoproyectogeneradoback.specification.ObjecionSpecification;
import com.dgamboav.nuevoproyectogeneradoback.utiles.FilterTypeInferer;

@Service
public class ObjecionServiceImpl implements ObjecionService {

    private final FilterTypeInferer filterTypeInferer;
    private final ObjecionRepository objecionRepository;
	
	ObjecionServiceImpl(ObjecionRepository objecionRepository, FilterTypeInferer filterTypeInferer){
        this.objecionRepository = objecionRepository;
		this.filterTypeInferer = filterTypeInferer;
    }

    @Override
    public ObjecionDTO crearObjecion(ObjecionDTO objecionDTO) {
        return ObjecionDTO.toDTO(objecionRepository.save(ObjecionDTO.toDomain(objecionDTO)));
    }

    @Override
    public Optional<ObjecionDTO> obtenerObjecionPorId(Long id) {
        return objecionRepository.findById(id).map(ObjecionDTO::toDTO);
    }
	
	@Override
    public Page<ObjecionDTO> obtenerTodosObjecion(Pageable pageable, Map<String, Object> filtros) {
	
	    Map<String, Object> entityFilters = new HashMap<>();
        if (filtros != null) {
            entityFilters.putAll(filtros);
            entityFilters.remove("page");
            entityFilters.remove("size");
        }
	
        ObjecionSpecification specification = new ObjecionSpecification(entityFilters, filterTypeInferer);
        Page<Objecion> objecionPage = objecionRepository.findAll(specification, pageable);
        return objecionPage.map(ObjecionDTO::toDTO);
    }
	
	@Override
     public List<ObjecionDTO> obtenerTodosMinObjecion() {
         return objecionRepository.findAll().stream()
                 .map(ObjecionDTO::toDTOMinimo)
                 .toList();
     }

    @Override
    public Optional<ObjecionDTO> actualizarObjecion(Long id, ObjecionDTO objecionDTO) {
        return objecionRepository.findById(id)
                .map(existingObjecion -> {
                    Objecion objecion = ObjecionDTO.toDomain(objecionDTO);
                    objecion.setId(id);
                    return objecionRepository.save(objecion);
                })
                .map(ObjecionDTO::toDTO);
    }

    @Override
    public void eliminarObjecion(Long id) {
        objecionRepository.deleteById(id);
    }
}