package com.dgamboav.nuevoproyectogeneradoback.servicio.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.HashMap;
import java.util.Optional;
import java.util.Map;
import java.util.List;
import com.dgamboav.nuevoproyectogeneradoback.repositorio.UsuarioRepository;
import com.dgamboav.nuevoproyectogeneradoback.servicio.UsuarioService;
import com.dgamboav.nuevoproyectogeneradoback.dto.UsuarioDTO;
import com.dgamboav.nuevoproyectogeneradoback.entidad.Usuario;
import com.dgamboav.nuevoproyectogeneradoback.specification.UsuarioSpecification;
import com.dgamboav.nuevoproyectogeneradoback.utiles.FilterTypeInferer;
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final FilterTypeInferer filterTypeInferer;
    private final UsuarioRepository usuarioRepository;
	public UsuarioServiceImpl(
        UsuarioRepository usuarioRepository,FilterTypeInferer filterTypeInferer
    ) {
        this.usuarioRepository = usuarioRepository;
        this.filterTypeInferer = filterTypeInferer;
    }

	@Override
	@Transactional
	public UsuarioDTO crearUsuario(UsuarioDTO dto) {
		return UsuarioDTO.toDTO(usuarioRepository.save(UsuarioDTO.toDomain(dto)));
	}

    @Override
    public Optional<UsuarioDTO> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).map(UsuarioDTO::toDTO);
    }
	
	@Override
    public Page<UsuarioDTO> obtenerTodosUsuario(Pageable pageable, Map<String, Object> filtros) {
	
	    Map<String, Object> entityFilters = new HashMap<>();
        if (filtros != null) {
            entityFilters.putAll(filtros);
            entityFilters.remove("page");
            entityFilters.remove("size");
        }
	
        UsuarioSpecification specification = new UsuarioSpecification(entityFilters, filterTypeInferer);
        Page<Usuario> usuarioPage = usuarioRepository.findAll(specification, pageable);
        return usuarioPage.map(UsuarioDTO::toDTO);
    }
	
	@Override
     public List<UsuarioDTO> obtenerTodosMinUsuario() {
         return usuarioRepository.findAll().stream()
                 .map(UsuarioDTO::toDTOMinimo)
                 .toList();
     }

	@Override
	@Transactional
	public Optional<UsuarioDTO> actualizarUsuario(Long id, UsuarioDTO dto) {
		return usuarioRepository.findById(id)
				.map(existingUsuario -> {
					Usuario entidad = UsuarioDTO.toDomain(dto);
					entidad.setId(id);
					return usuarioRepository.save(entidad);
				})
				.map(UsuarioDTO::toDTO);
	}


    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}