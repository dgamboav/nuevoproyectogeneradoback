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

import com.dgamboav.nuevoproyectogeneradoback.repositorio.ConformidadRepository;
import com.dgamboav.nuevoproyectogeneradoback.dto.ConformidadDTO;
import com.dgamboav.nuevoproyectogeneradoback.entidad.Conformidad;

import org.springframework.data.jpa.domain.Specification;
@ExtendWith(MockitoExtension.class)
class ConformidadServiceImplTest {

    @Mock
    private ConformidadRepository conformidadRepository;

    @InjectMocks
    private ConformidadServiceImpl conformidadService;

    @Test
    void testCrearConformidad() {
        ConformidadDTO conformidadDTO = ConformidadDTO.builder()
				.descripcion("test")
				.fechaDeteccion(null) // Valor por defecto para otros tipos
				.responsableId(1L)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
				.auditoriaId(1L)
				.procesoId(1L)
            .build();

        Conformidad conformidad = ConformidadDTO.toDomain(conformidadDTO);
        when(conformidadRepository.save(any(Conformidad.class))).thenReturn(conformidad);

        ConformidadDTO resultado = conformidadService.crearConformidad(conformidadDTO);

        assertEquals(ConformidadDTO.toDTO(conformidad), resultado);
        verify(conformidadRepository, times(1)).save(any(Conformidad.class));
    }

    @Test
    void testObtenerConformidadPorId() {
        Conformidad conformidad = Conformidad.builder()
				.descripcion("test")
				.fechaDeteccion(null) // Valor por defecto para otros tipos
				.responsableId(1L)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
				.auditoriaId(1L)
				.procesoId(1L)
            .build();

        when(conformidadRepository.findById(1L)).thenReturn(Optional.of(conformidad));

        Optional<ConformidadDTO> resultado = conformidadService.obtenerConformidadPorId(1L);

        assertEquals(Optional.of(ConformidadDTO.toDTO(conformidad)), resultado);
        verify(conformidadRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerTodosConformidad() {
        List<Conformidad> conformidads = List.of(
            Conformidad.builder()
				.descripcion("test")
				.fechaDeteccion(null) // Valor por defecto para otros tipos
				.responsableId(1L)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
				.auditoriaId(1L)
				.procesoId(1L)
                .build(),
            Conformidad.builder()
				.descripcion("test")
				.fechaDeteccion(null) // Valor por defecto para otros tipos
				.responsableId(1L)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
				.auditoriaId(1L)
				.procesoId(1L)
                .build()
        );
		
		Page<Conformidad> conformidadPage = new PageImpl<>(conformidads, PageRequest.of(0, 10), conformidads.size());

        when(conformidadRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(conformidadPage);

        Pageable pageable = PageRequest.of(0, 10);
		
        Map<String, Object> filtros = null;
		
        Page<ConformidadDTO> resultadoPage = conformidadService.obtenerTodosConformidad(pageable, filtros);

        List<ConformidadDTO> expectedDTOs = conformidads.stream().map(ConformidadDTO::toDTO).toList();		
		
		for (int i = 0; i < expectedDTOs.size(); i++) {

			assertEquals(expectedDTOs.get(i).getId(), resultadoPage.getContent().get(i).getId());
			assertEquals(expectedDTOs.get(i).getDescripcion(), resultadoPage.getContent().get(i).getDescripcion());
			assertEquals(expectedDTOs.get(i).getFechaDeteccion(), resultadoPage.getContent().get(i).getFechaDeteccion());
			assertEquals(expectedDTOs.get(i).getResponsableId(), resultadoPage.getContent().get(i).getResponsableId());
			assertEquals(expectedDTOs.get(i).getCreatedAt(), resultadoPage.getContent().get(i).getCreatedAt());
			assertEquals(expectedDTOs.get(i).getUpdatedAt(), resultadoPage.getContent().get(i).getUpdatedAt());
			assertEquals(expectedDTOs.get(i).getAuditoriaId(), resultadoPage.getContent().get(i).getAuditoriaId());
			assertEquals(expectedDTOs.get(i).getProcesoId(), resultadoPage.getContent().get(i).getProcesoId());

        }
		
		ArgumentCaptor<Specification<Conformidad>> specificationCaptor = ArgumentCaptor.forClass(Specification.class);
		
		verify(conformidadRepository, times(1)).findAll(specificationCaptor.capture(), any(Pageable.class));
    }
	
	@Test
     void testObtenerTodosMinConformidad() {
         List<Conformidad> conformidads = List.of(
             Conformidad.builder()
                 .build(),
             Conformidad.builder()
                 .build()
         );
 
         when(conformidadRepository.findAll()).thenReturn(conformidads);
 
         List<ConformidadDTO> resultado = conformidadService.obtenerTodosMinConformidad();
 
         List<ConformidadDTO> expected = conformidads.stream().map(ConformidadDTO::toDTO).toList();
         assertEquals(expected, resultado);
         verify(conformidadRepository, times(1)).findAll();
     }

    @Test
    void testActualizarConformidad() {
        ConformidadDTO conformidadDTO = ConformidadDTO.builder()
				.descripcion("test")
				.fechaDeteccion(null) // Valor por defecto para otros tipos
				.responsableId(1L)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
				.auditoriaId(1L)
				.procesoId(1L)
            .build();

        Conformidad conformidad = ConformidadDTO.toDomain(conformidadDTO);
        when(conformidadRepository.findById(1L)).thenReturn(Optional.of(conformidad));
        when(conformidadRepository.save(any(Conformidad.class))).thenReturn(conformidad);

        Optional<ConformidadDTO> resultado = conformidadService.actualizarConformidad(1L, conformidadDTO);

        assertEquals(Optional.of(ConformidadDTO.toDTO(conformidad)), resultado);
        verify(conformidadRepository, times(1)).findById(1L);
        verify(conformidadRepository, times(1)).save(any(Conformidad.class));
    }

    @Test
    void testEliminarConformidad() {
        conformidadService.eliminarConformidad(1L);

        verify(conformidadRepository, times(1)).deleteById(1L);
    }
}