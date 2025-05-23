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

import com.dgamboav.nuevoproyectogeneradoback.repositorio.AuditoriaRepository;
import com.dgamboav.nuevoproyectogeneradoback.dto.AuditoriaDTO;
import com.dgamboav.nuevoproyectogeneradoback.entidad.Auditoria;

import org.springframework.data.jpa.domain.Specification;
@ExtendWith(MockitoExtension.class)
class AuditoriaServiceImplTest {

    @Mock
    private AuditoriaRepository auditoriaRepository;

    @InjectMocks
    private AuditoriaServiceImpl auditoriaService;

    @Test
    void testCrearAuditoria() {
        AuditoriaDTO auditoriaDTO = AuditoriaDTO.builder()
				.fechaInicio(null) // Valor por defecto para otros tipos
				.fechaFin(null) // Valor por defecto para otros tipos
				.tipoAuditoria("test")
				.auditorLiderId(1L)
				.objetivo("test")
				.alcance("test")
				.estado("test")
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
            .build();

        Auditoria auditoria = AuditoriaDTO.toDomain(auditoriaDTO);
        when(auditoriaRepository.save(any(Auditoria.class))).thenReturn(auditoria);

        AuditoriaDTO resultado = auditoriaService.crearAuditoria(auditoriaDTO);

        assertEquals(AuditoriaDTO.toDTO(auditoria), resultado);
        verify(auditoriaRepository, times(1)).save(any(Auditoria.class));
    }

    @Test
    void testObtenerAuditoriaPorId() {
        Auditoria auditoria = Auditoria.builder()
				.fechaInicio(null) // Valor por defecto para otros tipos
				.fechaFin(null) // Valor por defecto para otros tipos
				.tipoAuditoria("test")
				.auditorLiderId(1L)
				.objetivo("test")
				.alcance("test")
				.estado("test")
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
            .build();

        when(auditoriaRepository.findById(1L)).thenReturn(Optional.of(auditoria));

        Optional<AuditoriaDTO> resultado = auditoriaService.obtenerAuditoriaPorId(1L);

        assertEquals(Optional.of(AuditoriaDTO.toDTO(auditoria)), resultado);
        verify(auditoriaRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerTodosAuditoria() {
        List<Auditoria> auditorias = List.of(
            Auditoria.builder()
				.fechaInicio(null) // Valor por defecto para otros tipos
				.fechaFin(null) // Valor por defecto para otros tipos
				.tipoAuditoria("test")
				.auditorLiderId(1L)
				.objetivo("test")
				.alcance("test")
				.estado("test")
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
                .build(),
            Auditoria.builder()
				.fechaInicio(null) // Valor por defecto para otros tipos
				.fechaFin(null) // Valor por defecto para otros tipos
				.tipoAuditoria("test")
				.auditorLiderId(1L)
				.objetivo("test")
				.alcance("test")
				.estado("test")
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
                .build()
        );
		
		Page<Auditoria> auditoriaPage = new PageImpl<>(auditorias, PageRequest.of(0, 10), auditorias.size());

        when(auditoriaRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(auditoriaPage);

        Pageable pageable = PageRequest.of(0, 10);
		
        Map<String, Object> filtros = null;
		
        Page<AuditoriaDTO> resultadoPage = auditoriaService.obtenerTodosAuditoria(pageable, filtros);

        List<AuditoriaDTO> expectedDTOs = auditorias.stream().map(AuditoriaDTO::toDTO).toList();		
		
		for (int i = 0; i < expectedDTOs.size(); i++) {

			assertEquals(expectedDTOs.get(i).getId(), resultadoPage.getContent().get(i).getId());
			assertEquals(expectedDTOs.get(i).getFechaInicio(), resultadoPage.getContent().get(i).getFechaInicio());
			assertEquals(expectedDTOs.get(i).getFechaFin(), resultadoPage.getContent().get(i).getFechaFin());
			assertEquals(expectedDTOs.get(i).getTipoAuditoria(), resultadoPage.getContent().get(i).getTipoAuditoria());
			assertEquals(expectedDTOs.get(i).getAuditorLiderId(), resultadoPage.getContent().get(i).getAuditorLiderId());
			assertEquals(expectedDTOs.get(i).getObjetivo(), resultadoPage.getContent().get(i).getObjetivo());
			assertEquals(expectedDTOs.get(i).getAlcance(), resultadoPage.getContent().get(i).getAlcance());
			assertEquals(expectedDTOs.get(i).getEstado(), resultadoPage.getContent().get(i).getEstado());
			assertEquals(expectedDTOs.get(i).getCreatedAt(), resultadoPage.getContent().get(i).getCreatedAt());
			assertEquals(expectedDTOs.get(i).getUpdatedAt(), resultadoPage.getContent().get(i).getUpdatedAt());

        }
		
		ArgumentCaptor<Specification<Auditoria>> specificationCaptor = ArgumentCaptor.forClass(Specification.class);
		
		verify(auditoriaRepository, times(1)).findAll(specificationCaptor.capture(), any(Pageable.class));
    }
	
	@Test
     void testObtenerTodosMinAuditoria() {
         List<Auditoria> auditorias = List.of(
             Auditoria.builder()
					.tipoAuditoria("test")
                 .build(),
             Auditoria.builder()
					.tipoAuditoria("test")
                 .build()
         );
 
         when(auditoriaRepository.findAll()).thenReturn(auditorias);
 
         List<AuditoriaDTO> resultado = auditoriaService.obtenerTodosMinAuditoria();
 
         List<AuditoriaDTO> expected = auditorias.stream().map(AuditoriaDTO::toDTO).toList();
         assertEquals(expected, resultado);
         verify(auditoriaRepository, times(1)).findAll();
     }

    @Test
    void testActualizarAuditoria() {
        AuditoriaDTO auditoriaDTO = AuditoriaDTO.builder()
				.fechaInicio(null) // Valor por defecto para otros tipos
				.fechaFin(null) // Valor por defecto para otros tipos
				.tipoAuditoria("test")
				.auditorLiderId(1L)
				.objetivo("test")
				.alcance("test")
				.estado("test")
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
            .build();

        Auditoria auditoria = AuditoriaDTO.toDomain(auditoriaDTO);
        when(auditoriaRepository.findById(1L)).thenReturn(Optional.of(auditoria));
        when(auditoriaRepository.save(any(Auditoria.class))).thenReturn(auditoria);

        Optional<AuditoriaDTO> resultado = auditoriaService.actualizarAuditoria(1L, auditoriaDTO);

        assertEquals(Optional.of(AuditoriaDTO.toDTO(auditoria)), resultado);
        verify(auditoriaRepository, times(1)).findById(1L);
        verify(auditoriaRepository, times(1)).save(any(Auditoria.class));
    }

    @Test
    void testEliminarAuditoria() {
        auditoriaService.eliminarAuditoria(1L);

        verify(auditoriaRepository, times(1)).deleteById(1L);
    }
}