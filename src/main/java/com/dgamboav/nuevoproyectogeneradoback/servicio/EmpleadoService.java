package com.dgamboav.nuevoproyectogeneradoback.servicio;

import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dgamboav.nuevoproyectogeneradoback.dto.EmpleadoDTO;

public interface EmpleadoService {

    EmpleadoDTO crearEmpleado(EmpleadoDTO empleadoDTO);

    Optional<EmpleadoDTO> obtenerEmpleadoPorId(Long id);

	Page<EmpleadoDTO> obtenerTodosEmpleado(Pageable pageable, Map<String, Object> filtros);
	
	List<EmpleadoDTO> obtenerTodosMinEmpleado();

    Optional<EmpleadoDTO> actualizarEmpleado(Long id, EmpleadoDTO empleadoDTO);

    void eliminarEmpleado(Long id);
}