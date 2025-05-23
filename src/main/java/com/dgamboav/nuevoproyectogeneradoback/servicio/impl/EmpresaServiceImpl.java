package com.dgamboav.nuevoproyectogeneradoback.servicio.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.HashMap;
import java.util.Optional;
import java.util.Map;
import java.util.List;
import com.dgamboav.nuevoproyectogeneradoback.repositorio.EmpresaRepository;
import com.dgamboav.nuevoproyectogeneradoback.servicio.EmpresaService;
import com.dgamboav.nuevoproyectogeneradoback.dto.EmpresaDTO;
import com.dgamboav.nuevoproyectogeneradoback.entidad.Empresa;
import com.dgamboav.nuevoproyectogeneradoback.specification.EmpresaSpecification;
import com.dgamboav.nuevoproyectogeneradoback.utiles.FilterTypeInferer;
@Service
public class EmpresaServiceImpl implements EmpresaService {

    private final FilterTypeInferer filterTypeInferer;
    private final EmpresaRepository empresaRepository;
	public EmpresaServiceImpl(
        EmpresaRepository empresaRepository,FilterTypeInferer filterTypeInferer
    ) {
        this.empresaRepository = empresaRepository;
        this.filterTypeInferer = filterTypeInferer;
    }

	@Override
	@Transactional
	public EmpresaDTO crearEmpresa(EmpresaDTO dto) {
		return EmpresaDTO.toDTO(empresaRepository.save(EmpresaDTO.toDomain(dto)));
	}

    @Override
    public Optional<EmpresaDTO> obtenerEmpresaPorId(Long id) {
        return empresaRepository.findById(id).map(EmpresaDTO::toDTO);
    }
	
	@Override
    public Page<EmpresaDTO> obtenerTodosEmpresa(Pageable pageable, Map<String, Object> filtros) {
	
	    Map<String, Object> entityFilters = new HashMap<>();
        if (filtros != null) {
            entityFilters.putAll(filtros);
            entityFilters.remove("page");
            entityFilters.remove("size");
        }
	
        EmpresaSpecification specification = new EmpresaSpecification(entityFilters, filterTypeInferer);
        Page<Empresa> empresaPage = empresaRepository.findAll(specification, pageable);
        return empresaPage.map(EmpresaDTO::toDTO);
    }
	
	@Override
     public List<EmpresaDTO> obtenerTodosMinEmpresa() {
         return empresaRepository.findAll().stream()
                 .map(EmpresaDTO::toDTOMinimo)
                 .toList();
     }

	@Override
	@Transactional
	public Optional<EmpresaDTO> actualizarEmpresa(Long id, EmpresaDTO dto) {
		return empresaRepository.findById(id)
				.map(existingEmpresa -> {
					Empresa entidad = EmpresaDTO.toDomain(dto);
					entidad.setId(id);
					return empresaRepository.save(entidad);
				})
				.map(EmpresaDTO::toDTO);
	}


    @Override
    public void eliminarEmpresa(Long id) {
        empresaRepository.deleteById(id);
    }
}