package com.dgamboav.nuevoproyectogeneradoback.servicio;

import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dgamboav.nuevoproyectogeneradoback.dto.EmpresaDTO;
public interface EmpresaService {

    EmpresaDTO crearEmpresa(EmpresaDTO empresaDTO);

    Optional<EmpresaDTO> obtenerEmpresaPorId(Long id);

	Page<EmpresaDTO> obtenerTodosEmpresa(Pageable pageable, Map<String, Object> filtros);
	
	List<EmpresaDTO> obtenerTodosMinEmpresa();

    Optional<EmpresaDTO> actualizarEmpresa(Long id, EmpresaDTO empresaDTO);

    void eliminarEmpresa(Long id);
}