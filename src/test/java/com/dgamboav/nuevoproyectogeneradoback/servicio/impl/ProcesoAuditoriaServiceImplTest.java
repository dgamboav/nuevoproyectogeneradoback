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

import com.dgamboav.nuevoproyectogeneradoback.repositorio.ProcesoAuditoriaRepository;
import com.dgamboav.nuevoproyectogeneradoback.dto.ProcesoAuditoriaDTO;
import com.dgamboav.nuevoproyectogeneradoback.entidad.ProcesoAuditoria;

import org.springframework.data.jpa.domain.Specification;
@ExtendWith(MockitoExtension.class)
class ProcesoAuditoriaServiceImplTest {

    @Mock
    private ProcesoAuditoriaRepository procesoAuditoriaRepository;

    @InjectMocks
    private ProcesoAuditoriaServiceImpl procesoAuditoriaService;

    @Test
    void testCrearProcesoAuditoria() {
        ProcesoAuditoriaDTO procesoAuditoriaDTO = ProcesoAuditoriaDTO.builder()
				.auditoriaId(1L)
				.procesoId(1L)
				.resultado("test")
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
            .build();

        ProcesoAuditoria procesoAuditoria = ProcesoAuditoriaDTO.toDomain(procesoAuditoriaDTO);
        when(procesoAuditoriaRepository.save(any(ProcesoAuditoria.class))).thenReturn(procesoAuditoria);

        ProcesoAuditoriaDTO resultado = procesoAuditoriaService.crearProcesoAuditoria(procesoAuditoriaDTO);

        assertEquals(ProcesoAuditoriaDTO.toDTO(procesoAuditoria), resultado);
        verify(procesoAuditoriaRepository, times(1)).save(any(ProcesoAuditoria.class));
    }

    @Test
    void testObtenerProcesoAuditoriaPorId() {
        ProcesoAuditoria procesoAuditoria = ProcesoAuditoria.builder()
				.auditoriaId(1L)
				.procesoId(1L)
				.resultado("test")
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
            .build();

        when(procesoAuditoriaRepository.findById(1L)).thenReturn(Optional.of(procesoAuditoria));

        Optional<ProcesoAuditoriaDTO> resultado = procesoAuditoriaService.obtenerProcesoAuditoriaPorId(1L);

        assertEquals(Optional.of(ProcesoAuditoriaDTO.toDTO(procesoAuditoria)), resultado);
        verify(procesoAuditoriaRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerTodosProcesoAuditoria() {
        List<ProcesoAuditoria> procesoAuditorias = List.of(
            ProcesoAuditoria.builder()
				.auditoriaId(1L)
				.procesoId(1L)
				.resultado("test")
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
                .build(),
            ProcesoAuditoria.builder()
				.auditoriaId(1L)
				.procesoId(1L)
				.resultado("test")
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
                .build()
        );
		
		Page<ProcesoAuditoria> procesoAuditoriaPage = new PageImpl<>(procesoAuditorias, PageRequest.of(0, 10), procesoAuditorias.size());

        when(procesoAuditoriaRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(procesoAuditoriaPage);

        Pageable pageable = PageRequest.of(0, 10);
		
        Map<String, Object> filtros = null;
		
        Page<ProcesoAuditoriaDTO> resultadoPage = procesoAuditoriaService.obtenerTodosProcesoAuditoria(pageable, filtros);

        List<ProcesoAuditoriaDTO> expectedDTOs = procesoAuditorias.stream().map(ProcesoAuditoriaDTO::toDTO).toList();		
		
		for (int i = 0; i < expectedDTOs.size(); i++) {

			assertEquals(expectedDTOs.get(i).getId(), resultadoPage.getContent().get(i).getId());
			assertEquals(expectedDTOs.get(i).getAuditoriaId(), resultadoPage.getContent().get(i).getAuditoriaId());
			assertEquals(expectedDTOs.get(i).getProcesoId(), resultadoPage.getContent().get(i).getProcesoId());
			assertEquals(expectedDTOs.get(i).getResultado(), resultadoPage.getContent().get(i).getResultado());
			assertEquals(expectedDTOs.get(i).getCreatedAt(), resultadoPage.getContent().get(i).getCreatedAt());
			assertEquals(expectedDTOs.get(i).getUpdatedAt(), resultadoPage.getContent().get(i).getUpdatedAt());

        }
		
		ArgumentCaptor<Specification<ProcesoAuditoria>> specificationCaptor = ArgumentCaptor.forClass(Specification.class);
		
		verify(procesoAuditoriaRepository, times(1)).findAll(specificationCaptor.capture(), any(Pageable.class));
    }
	
	@Test
     void testObtenerTodosMinProcesoAuditoria() {
         List<ProcesoAuditoria> procesoAuditorias = List.of(
             ProcesoAuditoria.builder()
                 .build(),
             ProcesoAuditoria.builder()
                 .build()
         );
 
         when(procesoAuditoriaRepository.findAll()).thenReturn(procesoAuditorias);
 
         List<ProcesoAuditoriaDTO> resultado = procesoAuditoriaService.obtenerTodosMinProcesoAuditoria();
 
         List<ProcesoAuditoriaDTO> expected = procesoAuditorias.stream().map(ProcesoAuditoriaDTO::toDTO).toList();
         assertEquals(expected, resultado);
         verify(procesoAuditoriaRepository, times(1)).findAll();
     }

    @Test
    void testActualizarProcesoAuditoria() {
        ProcesoAuditoriaDTO procesoAuditoriaDTO = ProcesoAuditoriaDTO.builder()
				.auditoriaId(1L)
				.procesoId(1L)
				.resultado("test")
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
            .build();

        ProcesoAuditoria procesoAuditoria = ProcesoAuditoriaDTO.toDomain(procesoAuditoriaDTO);
        when(procesoAuditoriaRepository.findById(1L)).thenReturn(Optional.of(procesoAuditoria));
        when(procesoAuditoriaRepository.save(any(ProcesoAuditoria.class))).thenReturn(procesoAuditoria);

        Optional<ProcesoAuditoriaDTO> resultado = procesoAuditoriaService.actualizarProcesoAuditoria(1L, procesoAuditoriaDTO);

        assertEquals(Optional.of(ProcesoAuditoriaDTO.toDTO(procesoAuditoria)), resultado);
        verify(procesoAuditoriaRepository, times(1)).findById(1L);
        verify(procesoAuditoriaRepository, times(1)).save(any(ProcesoAuditoria.class));
    }

    @Test
    void testEliminarProcesoAuditoria() {
        procesoAuditoriaService.eliminarProcesoAuditoria(1L);

        verify(procesoAuditoriaRepository, times(1)).deleteById(1L);
    }
}