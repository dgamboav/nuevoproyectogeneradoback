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

import com.dgamboav.nuevoproyectogeneradoback.repositorio.ProyectoRepository;
import com.dgamboav.nuevoproyectogeneradoback.dto.ProyectoDTO;
import com.dgamboav.nuevoproyectogeneradoback.entidad.Proyecto;

import org.springframework.data.jpa.domain.Specification;

@ExtendWith(MockitoExtension.class)
class ProyectoServiceImplTest {

    @Mock
    private ProyectoRepository proyectoRepository;

    @InjectMocks
    private ProyectoServiceImpl proyectoService;

    @Test
    void testCrearProyecto() {
        ProyectoDTO proyectoDTO = ProyectoDTO.builder()
				.clienteId(1L)
				.empleadoId(1L)
				.fechaCreacion(null) // Valor por defecto para otros tipos
				.costo(1.0)
            .build();

        Proyecto proyecto = ProyectoDTO.toDomain(proyectoDTO);
        when(proyectoRepository.save(any(Proyecto.class))).thenReturn(proyecto);

        ProyectoDTO resultado = proyectoService.crearProyecto(proyectoDTO);

        assertEquals(ProyectoDTO.toDTO(proyecto), resultado);
        verify(proyectoRepository, times(1)).save(any(Proyecto.class));
    }

    @Test
    void testObtenerProyectoPorId() {
        Proyecto proyecto = Proyecto.builder()
				.clienteId(1L)
				.empleadoId(1L)
				.fechaCreacion(null) // Valor por defecto para otros tipos
				.costo(1.0)
            .build();

        when(proyectoRepository.findById(1L)).thenReturn(Optional.of(proyecto));

        Optional<ProyectoDTO> resultado = proyectoService.obtenerProyectoPorId(1L);

        assertEquals(Optional.of(ProyectoDTO.toDTO(proyecto)), resultado);
        verify(proyectoRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerTodosProyecto() {
        List<Proyecto> proyectos = List.of(
            Proyecto.builder()
				.clienteId(1L)
				.empleadoId(1L)
				.fechaCreacion(null) // Valor por defecto para otros tipos
				.costo(1.0)
                .build(),
            Proyecto.builder()
				.clienteId(1L)
				.empleadoId(1L)
				.fechaCreacion(null) // Valor por defecto para otros tipos
				.costo(1.0)
                .build()
        );
		
		Page<Proyecto> proyectoPage = new PageImpl<>(proyectos, PageRequest.of(0, 10), proyectos.size());

        when(proyectoRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(proyectoPage);

        Pageable pageable = PageRequest.of(0, 10);
		
        Map<String, Object> filtros = null;
		
        Page<ProyectoDTO> resultadoPage = proyectoService.obtenerTodosProyecto(pageable, filtros);

        List<ProyectoDTO> expectedDTOs = proyectos.stream().map(ProyectoDTO::toDTO).toList();		
		
		for (int i = 0; i < expectedDTOs.size(); i++) {

			assertEquals(expectedDTOs.get(i).getId(), resultadoPage.getContent().get(i).getId());
			assertEquals(expectedDTOs.get(i).getClienteId(), resultadoPage.getContent().get(i).getClienteId());
			assertEquals(expectedDTOs.get(i).getEmpleadoId(), resultadoPage.getContent().get(i).getEmpleadoId());
			assertEquals(expectedDTOs.get(i).getFechaCreacion(), resultadoPage.getContent().get(i).getFechaCreacion());
			assertEquals(expectedDTOs.get(i).getCosto(), resultadoPage.getContent().get(i).getCosto());

        }
		
		ArgumentCaptor<Specification<Proyecto>> specificationCaptor = ArgumentCaptor.forClass(Specification.class);
		
		verify(proyectoRepository, times(1)).findAll(specificationCaptor.capture(), any(Pageable.class));
    }
	
	@Test
     void testObtenerTodosMinProyecto() {
         List<Proyecto> proyectos = List.of(
             Proyecto.builder()
					.clienteId(1L)
					.empleadoId(1L)
                 .build(),
             Proyecto.builder()
					.clienteId(1L)
					.empleadoId(1L)
                 .build()
         );
 
         when(proyectoRepository.findAll()).thenReturn(proyectos);
 
         List<ProyectoDTO> resultado = proyectoService.obtenerTodosMinProyecto();
 
         List<ProyectoDTO> expected = proyectos.stream().map(ProyectoDTO::toDTO).toList();
         assertEquals(expected, resultado);
         verify(proyectoRepository, times(1)).findAll();
     }

    @Test
    void testActualizarProyecto() {
        ProyectoDTO proyectoDTO = ProyectoDTO.builder()
				.clienteId(1L)
				.empleadoId(1L)
				.fechaCreacion(null) // Valor por defecto para otros tipos
				.costo(1.0)
            .build();

        Proyecto proyecto = ProyectoDTO.toDomain(proyectoDTO);
        when(proyectoRepository.findById(1L)).thenReturn(Optional.of(proyecto));
        when(proyectoRepository.save(any(Proyecto.class))).thenReturn(proyecto);

        Optional<ProyectoDTO> resultado = proyectoService.actualizarProyecto(1L, proyectoDTO);

        assertEquals(Optional.of(ProyectoDTO.toDTO(proyecto)), resultado);
        verify(proyectoRepository, times(1)).findById(1L);
        verify(proyectoRepository, times(1)).save(any(Proyecto.class));
    }

    @Test
    void testEliminarProyecto() {
        proyectoService.eliminarProyecto(1L);

        verify(proyectoRepository, times(1)).deleteById(1L);
    }
}