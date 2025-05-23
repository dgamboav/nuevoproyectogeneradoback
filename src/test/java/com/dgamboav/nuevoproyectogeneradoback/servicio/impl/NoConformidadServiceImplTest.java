package com.dgamboav.nuevoproyectogeneradoback.servicio.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.PageImpl;

import com.dgamboav.nuevoproyectogeneradoback.repositorio.NoConformidadRepository;
import com.dgamboav.nuevoproyectogeneradoback.dto.NoConformidadDTO;
import com.dgamboav.nuevoproyectogeneradoback.entidad.NoConformidad;

import org.springframework.data.jpa.domain.Specification;
@ExtendWith(MockitoExtension.class)
class NoConformidadServiceImplTest {

    @Mock
    private NoConformidadRepository noConformidadRepository;

    @InjectMocks
    private NoConformidadServiceImpl noConformidadService;

    @Test
    void testCrearNoConformidad() {
        NoConformidadDTO noConformidadDTO = NoConformidadDTO.builder()
				.descripcion("test")
				.causaRaiz("test")
				.clasificacion("test")
				.fechaDeteccion(null) // Valor por defecto para otros tipos
				.responsableId(1L)
				.estado("test")
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
				.auditoriaId(1L)
				.procesoId(1L)
            .build();

        NoConformidad noConformidad = NoConformidadDTO.toDomain(noConformidadDTO);
        when(noConformidadRepository.save(any(NoConformidad.class))).thenReturn(noConformidad);

        NoConformidadDTO resultado = noConformidadService.crearNoConformidad(noConformidadDTO);

        assertEquals(NoConformidadDTO.toDTO(noConformidad), resultado);
        verify(noConformidadRepository, times(1)).save(any(NoConformidad.class));
    }

    @Test
    void testObtenerNoConformidadPorId() {
        NoConformidad noConformidad = NoConformidad.builder()
				.descripcion("test")
				.causaRaiz("test")
				.clasificacion("test")
				.fechaDeteccion(null) // Valor por defecto para otros tipos
				.responsableId(1L)
				.estado("test")
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
				.auditoriaId(1L)
				.procesoId(1L)
            .build();

        when(noConformidadRepository.findById(1L)).thenReturn(Optional.of(noConformidad));

        Optional<NoConformidadDTO> resultado = noConformidadService.obtenerNoConformidadPorId(1L);

        assertEquals(Optional.of(NoConformidadDTO.toDTO(noConformidad)), resultado);
        verify(noConformidadRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerTodosNoConformidad() {
        List<NoConformidad> noConformidads = List.of(
            NoConformidad.builder()
				.descripcion("test")
				.causaRaiz("test")
				.clasificacion("test")
				.fechaDeteccion(null) // Valor por defecto para otros tipos
				.responsableId(1L)
				.estado("test")
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
				.auditoriaId(1L)
				.procesoId(1L)
                .build(),
            NoConformidad.builder()
				.descripcion("test")
				.causaRaiz("test")
				.clasificacion("test")
				.fechaDeteccion(null) // Valor por defecto para otros tipos
				.responsableId(1L)
				.estado("test")
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
				.auditoriaId(1L)
				.procesoId(1L)
                .build()
        );
		
		Page<NoConformidad> noConformidadPage = new PageImpl<>(noConformidads, PageRequest.of(0, 10), noConformidads.size());

        when(noConformidadRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(noConformidadPage);

        Pageable pageable = PageRequest.of(0, 10);
		
        Map<String, Object> filtros = null;
		
        Page<NoConformidadDTO> resultadoPage = noConformidadService.obtenerTodosNoConformidad(pageable, filtros);

        List<NoConformidadDTO> expectedDTOs = noConformidads.stream().map(NoConformidadDTO::toDTO).toList();		
		
		for (int i = 0; i < expectedDTOs.size(); i++) {

			assertEquals(expectedDTOs.get(i).getId(), resultadoPage.getContent().get(i).getId());
			assertEquals(expectedDTOs.get(i).getDescripcion(), resultadoPage.getContent().get(i).getDescripcion());
			assertEquals(expectedDTOs.get(i).getCausaRaiz(), resultadoPage.getContent().get(i).getCausaRaiz());
			assertEquals(expectedDTOs.get(i).getClasificacion(), resultadoPage.getContent().get(i).getClasificacion());
			assertEquals(expectedDTOs.get(i).getFechaDeteccion(), resultadoPage.getContent().get(i).getFechaDeteccion());
			assertEquals(expectedDTOs.get(i).getResponsableId(), resultadoPage.getContent().get(i).getResponsableId());
			assertEquals(expectedDTOs.get(i).getEstado(), resultadoPage.getContent().get(i).getEstado());
			assertEquals(expectedDTOs.get(i).getCreatedAt(), resultadoPage.getContent().get(i).getCreatedAt());
			assertEquals(expectedDTOs.get(i).getUpdatedAt(), resultadoPage.getContent().get(i).getUpdatedAt());
			assertEquals(expectedDTOs.get(i).getAuditoriaId(), resultadoPage.getContent().get(i).getAuditoriaId());
			assertEquals(expectedDTOs.get(i).getProcesoId(), resultadoPage.getContent().get(i).getProcesoId());

        }
		
		ArgumentCaptor<Specification<NoConformidad>> specificationCaptor = ArgumentCaptor.forClass(Specification.class);
		
		verify(noConformidadRepository, times(1)).findAll(specificationCaptor.capture(), any(Pageable.class));
    }
	
	@Test
     void testObtenerTodosMinNoConformidad() {
         List<NoConformidad> noConformidads = List.of(
             NoConformidad.builder()
                 .build(),
             NoConformidad.builder()
                 .build()
         );
 
         when(noConformidadRepository.findAll()).thenReturn(noConformidads);
 
         List<NoConformidadDTO> resultado = noConformidadService.obtenerTodosMinNoConformidad();
 
         List<NoConformidadDTO> expected = noConformidads.stream().map(NoConformidadDTO::toDTO).toList();
         assertEquals(expected, resultado);
         verify(noConformidadRepository, times(1)).findAll();
     }

    @Test
    void testActualizarNoConformidad() {
        NoConformidadDTO noConformidadDTO = NoConformidadDTO.builder()
				.descripcion("test")
				.causaRaiz("test")
				.clasificacion("test")
				.fechaDeteccion(null) // Valor por defecto para otros tipos
				.responsableId(1L)
				.estado("test")
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
				.auditoriaId(1L)
				.procesoId(1L)
            .build();

        NoConformidad noConformidad = NoConformidadDTO.toDomain(noConformidadDTO);
        when(noConformidadRepository.findById(1L)).thenReturn(Optional.of(noConformidad));
        when(noConformidadRepository.save(any(NoConformidad.class))).thenReturn(noConformidad);

        Optional<NoConformidadDTO> resultado = noConformidadService.actualizarNoConformidad(1L, noConformidadDTO);

        assertEquals(Optional.of(NoConformidadDTO.toDTO(noConformidad)), resultado);
        verify(noConformidadRepository, times(1)).findById(1L);
        verify(noConformidadRepository, times(1)).save(any(NoConformidad.class));
    }

    @Test
    void testEliminarNoConformidad() {
        noConformidadService.eliminarNoConformidad(1L);

        verify(noConformidadRepository, times(1)).deleteById(1L);
    }
}