package com.dgamboav.nuevoproyectogeneradoback.servicio.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.HashMap;
import java.util.Optional;
import java.util.Map;
import java.util.List;
import com.dgamboav.nuevoproyectogeneradoback.repositorio.EmpleadoRepository;
import com.dgamboav.nuevoproyectogeneradoback.servicio.EmpleadoService;
import com.dgamboav.nuevoproyectogeneradoback.dto.EmpleadoDTO;
import com.dgamboav.nuevoproyectogeneradoback.entidad.Empleado;
import com.dgamboav.nuevoproyectogeneradoback.specification.EmpleadoSpecification;
import com.dgamboav.nuevoproyectogeneradoback.utiles.FilterTypeInferer;
@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final FilterTypeInferer filterTypeInferer;
    private final EmpleadoRepository empleadoRepository;
	public EmpleadoServiceImpl(
        EmpleadoRepository empleadoRepository,FilterTypeInferer filterTypeInferer
    ) {
        this.empleadoRepository = empleadoRepository;
        this.filterTypeInferer = filterTypeInferer;
    }

	@Override
	@Transactional
	public EmpleadoDTO crearEmpleado(EmpleadoDTO dto) {
		return EmpleadoDTO.toDTO(empleadoRepository.save(EmpleadoDTO.toDomain(dto)));
	}

    @Override
    public Optional<EmpleadoDTO> obtenerEmpleadoPorId(Long id) {
        return empleadoRepository.findById(id).map(EmpleadoDTO::toDTO);
    }
	
	@Override
    public Page<EmpleadoDTO> obtenerTodosEmpleado(Pageable pageable, Map<String, Object> filtros) {
	
	    Map<String, Object> entityFilters = new HashMap<>();
        if (filtros != null) {
            entityFilters.putAll(filtros);
            entityFilters.remove("page");
            entityFilters.remove("size");
        }
	
        EmpleadoSpecification specification = new EmpleadoSpecification(entityFilters, filterTypeInferer);
        Page<Empleado> empleadoPage = empleadoRepository.findAll(specification, pageable);
        return empleadoPage.map(EmpleadoDTO::toDTO);
    }
	
	@Override
     public List<EmpleadoDTO> obtenerTodosMinEmpleado() {
         return empleadoRepository.findAll().stream()
                 .map(EmpleadoDTO::toDTOMinimo)
                 .toList();
     }

	@Override
	@Transactional
	public Optional<EmpleadoDTO> actualizarEmpleado(Long id, EmpleadoDTO dto) {
		return empleadoRepository.findById(id)
				.map(existingEmpleado -> {
					Empleado entidad = EmpleadoDTO.toDomain(dto);
					entidad.setId(id);
					return empleadoRepository.save(entidad);
				})
				.map(EmpleadoDTO::toDTO);
	}


    @Override
    public void eliminarEmpleado(Long id) {
        empleadoRepository.deleteById(id);
    }
}