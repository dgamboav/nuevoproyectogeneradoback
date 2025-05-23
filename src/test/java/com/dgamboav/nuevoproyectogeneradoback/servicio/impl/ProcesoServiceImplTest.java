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

import com.dgamboav.nuevoproyectogeneradoback.repositorio.ProcesoRepository;
import com.dgamboav.nuevoproyectogeneradoback.dto.ProcesoDTO;
import com.dgamboav.nuevoproyectogeneradoback.entidad.Proceso;

import org.springframework.data.jpa.domain.Specification;
@ExtendWith(MockitoExtension.class)
class ProcesoServiceImplTest {

    @Mock
    private ProcesoRepository procesoRepository;

    @InjectMocks
    private ProcesoServiceImpl procesoService;

    @Test
    void testCrearProceso() {
        ProcesoDTO procesoDTO = ProcesoDTO.builder()
				.nombre("test")
				.descripcion("test")
				.codigoProceso("test")
				.responsableId(1L)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
				.empresaId(1L)
            .build();

        Proceso proceso = ProcesoDTO.toDomain(procesoDTO);
        when(procesoRepository.save(any(Proceso.class))).thenReturn(proceso);

        ProcesoDTO resultado = procesoService.crearProceso(procesoDTO);

        assertEquals(ProcesoDTO.toDTO(proceso), resultado);
        verify(procesoRepository, times(1)).save(any(Proceso.class));
    }

    @Test
    void testObtenerProcesoPorId() {
        Proceso proceso = Proceso.builder()
				.nombre("test")
				.descripcion("test")
				.codigoProceso("test")
				.responsableId(1L)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
				.empresaId(1L)
            .build();

        when(procesoRepository.findById(1L)).thenReturn(Optional.of(proceso));

        Optional<ProcesoDTO> resultado = procesoService.obtenerProcesoPorId(1L);

        assertEquals(Optional.of(ProcesoDTO.toDTO(proceso)), resultado);
        verify(procesoRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerTodosProceso() {
        List<Proceso> procesos = List.of(
            Proceso.builder()
				.nombre("test")
				.descripcion("test")
				.codigoProceso("test")
				.responsableId(1L)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
				.empresaId(1L)
                .build(),
            Proceso.builder()
				.nombre("test")
				.descripcion("test")
				.codigoProceso("test")
				.responsableId(1L)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
				.empresaId(1L)
                .build()
        );
		
		Page<Proceso> procesoPage = new PageImpl<>(procesos, PageRequest.of(0, 10), procesos.size());

        when(procesoRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(procesoPage);

        Pageable pageable = PageRequest.of(0, 10);
		
        Map<String, Object> filtros = null;
		
        Page<ProcesoDTO> resultadoPage = procesoService.obtenerTodosProceso(pageable, filtros);

        List<ProcesoDTO> expectedDTOs = procesos.stream().map(ProcesoDTO::toDTO).toList();		
		
		for (int i = 0; i < expectedDTOs.size(); i++) {

			assertEquals(expectedDTOs.get(i).getId(), resultadoPage.getContent().get(i).getId());
			assertEquals(expectedDTOs.get(i).getNombre(), resultadoPage.getContent().get(i).getNombre());
			assertEquals(expectedDTOs.get(i).getDescripcion(), resultadoPage.getContent().get(i).getDescripcion());
			assertEquals(expectedDTOs.get(i).getCodigoProceso(), resultadoPage.getContent().get(i).getCodigoProceso());
			assertEquals(expectedDTOs.get(i).getResponsableId(), resultadoPage.getContent().get(i).getResponsableId());
			assertEquals(expectedDTOs.get(i).getCreatedAt(), resultadoPage.getContent().get(i).getCreatedAt());
			assertEquals(expectedDTOs.get(i).getUpdatedAt(), resultadoPage.getContent().get(i).getUpdatedAt());
			assertEquals(expectedDTOs.get(i).getEmpresaId(), resultadoPage.getContent().get(i).getEmpresaId());

        }
		
		ArgumentCaptor<Specification<Proceso>> specificationCaptor = ArgumentCaptor.forClass(Specification.class);
		
		verify(procesoRepository, times(1)).findAll(specificationCaptor.capture(), any(Pageable.class));
    }
	
	@Test
     void testObtenerTodosMinProceso() {
         List<Proceso> procesos = List.of(
             Proceso.builder()
                 .build(),
             Proceso.builder()
                 .build()
         );
 
         when(procesoRepository.findAll()).thenReturn(procesos);
 
         List<ProcesoDTO> resultado = procesoService.obtenerTodosMinProceso();
 
         List<ProcesoDTO> expected = procesos.stream().map(ProcesoDTO::toDTO).toList();
         assertEquals(expected, resultado);
         verify(procesoRepository, times(1)).findAll();
     }

    @Test
    void testActualizarProceso() {
        ProcesoDTO procesoDTO = ProcesoDTO.builder()
				.nombre("test")
				.descripcion("test")
				.codigoProceso("test")
				.responsableId(1L)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
				.empresaId(1L)
            .build();

        Proceso proceso = ProcesoDTO.toDomain(procesoDTO);
        when(procesoRepository.findById(1L)).thenReturn(Optional.of(proceso));
        when(procesoRepository.save(any(Proceso.class))).thenReturn(proceso);

        Optional<ProcesoDTO> resultado = procesoService.actualizarProceso(1L, procesoDTO);

        assertEquals(Optional.of(ProcesoDTO.toDTO(proceso)), resultado);
        verify(procesoRepository, times(1)).findById(1L);
        verify(procesoRepository, times(1)).save(any(Proceso.class));
    }

    @Test
    void testEliminarProceso() {
        procesoService.eliminarProceso(1L);

        verify(procesoRepository, times(1)).deleteById(1L);
    }
}