package com.dgamboav.nuevoproyectogeneradoback.servicio.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.PageImpl;

import com.dgamboav.nuevoproyectogeneradoback.repositorio.UsuarioRepository;
import com.dgamboav.nuevoproyectogeneradoback.dto.UsuarioDTO;
import com.dgamboav.nuevoproyectogeneradoback.entidad.Usuario;

import org.springframework.data.jpa.domain.Specification;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Test
    void testCrearUsuario() {
        UsuarioDTO usuarioDTO = UsuarioDTO.builder()
				.nombre("test")
				.correo("test")
				.contrasena("test")
				.fechaCreacion(null) // Valor por defecto para otros tipos
            .build();

        Usuario usuario = UsuarioDTO.toDomain(usuarioDTO);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        UsuarioDTO resultado = usuarioService.crearUsuario(usuarioDTO);

        assertEquals(UsuarioDTO.toDTO(usuario), resultado);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void testObtenerUsuarioPorId() {
        Usuario usuario = Usuario.builder()
				.nombre("test")
				.correo("test")
				.contrasena("test")
				.fechaCreacion(null) // Valor por defecto para otros tipos
            .build();

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Optional<UsuarioDTO> resultado = usuarioService.obtenerUsuarioPorId(1L);

        assertEquals(Optional.of(UsuarioDTO.toDTO(usuario)), resultado);
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerTodosUsuario() {
        List<Usuario> usuarios = List.of(
            Usuario.builder()
				.nombre("test")
				.correo("test")
				.contrasena("test")
				.fechaCreacion(null) // Valor por defecto para otros tipos
                .build(),
            Usuario.builder()
				.nombre("test")
				.correo("test")
				.contrasena("test")
				.fechaCreacion(null) // Valor por defecto para otros tipos
                .build()
        );
		
		Page<Usuario> usuarioPage = new PageImpl<>(usuarios, PageRequest.of(0, 10), usuarios.size());

        when(usuarioRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(usuarioPage);

        Pageable pageable = PageRequest.of(0, 10);
		
        Map<String, Object> filtros = null;
		
        Page<UsuarioDTO> resultadoPage = usuarioService.obtenerTodosUsuario(pageable, filtros);

        List<UsuarioDTO> expectedDTOs = usuarios.stream().map(UsuarioDTO::toDTO).toList();		
		
		for (int i = 0; i < expectedDTOs.size(); i++) {

			assertEquals(expectedDTOs.get(i).getId(), resultadoPage.getContent().get(i).getId());
			assertEquals(expectedDTOs.get(i).getNombre(), resultadoPage.getContent().get(i).getNombre());
			assertEquals(expectedDTOs.get(i).getCorreo(), resultadoPage.getContent().get(i).getCorreo());
			assertEquals(expectedDTOs.get(i).getContrasena(), resultadoPage.getContent().get(i).getContrasena());
			assertEquals(expectedDTOs.get(i).getFechaCreacion(), resultadoPage.getContent().get(i).getFechaCreacion());

        }
		
		ArgumentCaptor<Specification<Usuario>> specificationCaptor = ArgumentCaptor.forClass(Specification.class);
		
		verify(usuarioRepository, times(1)).findAll(specificationCaptor.capture(), any(Pageable.class));
    }
	
	@Test
     void testObtenerTodosMinUsuario() {
         List<Usuario> usuarios = List.of(
             Usuario.builder()
					.nombre("test")
                 .build(),
             Usuario.builder()
					.nombre("test")
                 .build()
         );
 
         when(usuarioRepository.findAll()).thenReturn(usuarios);
 
         List<UsuarioDTO> resultado = usuarioService.obtenerTodosMinUsuario();
 
         List<UsuarioDTO> expected = usuarios.stream().map(UsuarioDTO::toDTO).toList();
         assertEquals(expected, resultado);
         verify(usuarioRepository, times(1)).findAll();
     }

    @Test
    void testActualizarUsuario() {
        UsuarioDTO usuarioDTO = UsuarioDTO.builder()
				.nombre("test")
				.correo("test")
				.contrasena("test")
				.fechaCreacion(null) // Valor por defecto para otros tipos
            .build();

        Usuario usuario = UsuarioDTO.toDomain(usuarioDTO);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Optional<UsuarioDTO> resultado = usuarioService.actualizarUsuario(1L, usuarioDTO);

        assertEquals(Optional.of(UsuarioDTO.toDTO(usuario)), resultado);
        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void testEliminarUsuario() {
        usuarioService.eliminarUsuario(1L);

        verify(usuarioRepository, times(1)).deleteById(1L);
    }
}