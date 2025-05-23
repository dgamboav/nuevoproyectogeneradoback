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

import com.dgamboav.nuevoproyectogeneradoback.repositorio.AccionCorrectivaRepository;
import com.dgamboav.nuevoproyectogeneradoback.dto.AccionCorrectivaDTO;
import com.dgamboav.nuevoproyectogeneradoback.entidad.AccionCorrectiva;

import org.springframework.data.jpa.domain.Specification;
@ExtendWith(MockitoExtension.class)
class AccionCorrectivaServiceImplTest {

    @Mock
    private AccionCorrectivaRepository accionCorrectivaRepository;

    @InjectMocks
    private AccionCorrectivaServiceImpl accionCorrectivaService;

    @Test
    void testCrearAccionCorrectiva() {
        AccionCorrectivaDTO accionCorrectivaDTO = AccionCorrectivaDTO.builder()
				.descripcion("test")
				.fechaImplementacion(null) // Valor por defecto para otros tipos
				.fechaVerificacion(null) // Valor por defecto para otros tipos
				.estado("test")
				.responsableId(1L)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
				.noConformidadId(1L)
            .build();

        AccionCorrectiva accionCorrectiva = AccionCorrectivaDTO.toDomain(accionCorrectivaDTO);
        when(accionCorrectivaRepository.save(any(AccionCorrectiva.class))).thenReturn(accionCorrectiva);

        AccionCorrectivaDTO resultado = accionCorrectivaService.crearAccionCorrectiva(accionCorrectivaDTO);

        assertEquals(AccionCorrectivaDTO.toDTO(accionCorrectiva), resultado);
        verify(accionCorrectivaRepository, times(1)).save(any(AccionCorrectiva.class));
    }

    @Test
    void testObtenerAccionCorrectivaPorId() {
        AccionCorrectiva accionCorrectiva = AccionCorrectiva.builder()
				.descripcion("test")
				.fechaImplementacion(null) // Valor por defecto para otros tipos
				.fechaVerificacion(null) // Valor por defecto para otros tipos
				.estado("test")
				.responsableId(1L)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
				.noConformidadId(1L)
            .build();

        when(accionCorrectivaRepository.findById(1L)).thenReturn(Optional.of(accionCorrectiva));

        Optional<AccionCorrectivaDTO> resultado = accionCorrectivaService.obtenerAccionCorrectivaPorId(1L);

        assertEquals(Optional.of(AccionCorrectivaDTO.toDTO(accionCorrectiva)), resultado);
        verify(accionCorrectivaRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerTodosAccionCorrectiva() {
        List<AccionCorrectiva> accionCorrectivas = List.of(
            AccionCorrectiva.builder()
				.descripcion("test")
				.fechaImplementacion(null) // Valor por defecto para otros tipos
				.fechaVerificacion(null) // Valor por defecto para otros tipos
				.estado("test")
				.responsableId(1L)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
				.noConformidadId(1L)
                .build(),
            AccionCorrectiva.builder()
				.descripcion("test")
				.fechaImplementacion(null) // Valor por defecto para otros tipos
				.fechaVerificacion(null) // Valor por defecto para otros tipos
				.estado("test")
				.responsableId(1L)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
				.noConformidadId(1L)
                .build()
        );
		
		Page<AccionCorrectiva> accionCorrectivaPage = new PageImpl<>(accionCorrectivas, PageRequest.of(0, 10), accionCorrectivas.size());

        when(accionCorrectivaRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(accionCorrectivaPage);

        Pageable pageable = PageRequest.of(0, 10);
		
        Map<String, Object> filtros = null;
		
        Page<AccionCorrectivaDTO> resultadoPage = accionCorrectivaService.obtenerTodosAccionCorrectiva(pageable, filtros);

        List<AccionCorrectivaDTO> expectedDTOs = accionCorrectivas.stream().map(AccionCorrectivaDTO::toDTO).toList();		
		
		for (int i = 0; i < expectedDTOs.size(); i++) {

			assertEquals(expectedDTOs.get(i).getId(), resultadoPage.getContent().get(i).getId());
			assertEquals(expectedDTOs.get(i).getDescripcion(), resultadoPage.getContent().get(i).getDescripcion());
			assertEquals(expectedDTOs.get(i).getFechaImplementacion(), resultadoPage.getContent().get(i).getFechaImplementacion());
			assertEquals(expectedDTOs.get(i).getFechaVerificacion(), resultadoPage.getContent().get(i).getFechaVerificacion());
			assertEquals(expectedDTOs.get(i).getEstado(), resultadoPage.getContent().get(i).getEstado());
			assertEquals(expectedDTOs.get(i).getResponsableId(), resultadoPage.getContent().get(i).getResponsableId());
			assertEquals(expectedDTOs.get(i).getCreatedAt(), resultadoPage.getContent().get(i).getCreatedAt());
			assertEquals(expectedDTOs.get(i).getUpdatedAt(), resultadoPage.getContent().get(i).getUpdatedAt());
			assertEquals(expectedDTOs.get(i).getNoConformidadId(), resultadoPage.getContent().get(i).getNoConformidadId());

        }
		
		ArgumentCaptor<Specification<AccionCorrectiva>> specificationCaptor = ArgumentCaptor.forClass(Specification.class);
		
		verify(accionCorrectivaRepository, times(1)).findAll(specificationCaptor.capture(), any(Pageable.class));
    }
	
	@Test
     void testObtenerTodosMinAccionCorrectiva() {
         List<AccionCorrectiva> accionCorrectivas = List.of(
             AccionCorrectiva.builder()
                 .build(),
             AccionCorrectiva.builder()
                 .build()
         );
 
         when(accionCorrectivaRepository.findAll()).thenReturn(accionCorrectivas);
 
         List<AccionCorrectivaDTO> resultado = accionCorrectivaService.obtenerTodosMinAccionCorrectiva();
 
         List<AccionCorrectivaDTO> expected = accionCorrectivas.stream().map(AccionCorrectivaDTO::toDTO).toList();
         assertEquals(expected, resultado);
         verify(accionCorrectivaRepository, times(1)).findAll();
     }

    @Test
    void testActualizarAccionCorrectiva() {
        AccionCorrectivaDTO accionCorrectivaDTO = AccionCorrectivaDTO.builder()
				.descripcion("test")
				.fechaImplementacion(null) // Valor por defecto para otros tipos
				.fechaVerificacion(null) // Valor por defecto para otros tipos
				.estado("test")
				.responsableId(1L)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
				.noConformidadId(1L)
            .build();

        AccionCorrectiva accionCorrectiva = AccionCorrectivaDTO.toDomain(accionCorrectivaDTO);
        when(accionCorrectivaRepository.findById(1L)).thenReturn(Optional.of(accionCorrectiva));
        when(accionCorrectivaRepository.save(any(AccionCorrectiva.class))).thenReturn(accionCorrectiva);

        Optional<AccionCorrectivaDTO> resultado = accionCorrectivaService.actualizarAccionCorrectiva(1L, accionCorrectivaDTO);

        assertEquals(Optional.of(AccionCorrectivaDTO.toDTO(accionCorrectiva)), resultado);
        verify(accionCorrectivaRepository, times(1)).findById(1L);
        verify(accionCorrectivaRepository, times(1)).save(any(AccionCorrectiva.class));
    }

    @Test
    void testEliminarAccionCorrectiva() {
        accionCorrectivaService.eliminarAccionCorrectiva(1L);

        verify(accionCorrectivaRepository, times(1)).deleteById(1L);
    }
}