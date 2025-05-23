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

import com.dgamboav.nuevoproyectogeneradoback.repositorio.EmpresaRepository;
import com.dgamboav.nuevoproyectogeneradoback.dto.EmpresaDTO;
import com.dgamboav.nuevoproyectogeneradoback.entidad.Empresa;

import org.springframework.data.jpa.domain.Specification;
@ExtendWith(MockitoExtension.class)
class EmpresaServiceImplTest {

    @Mock
    private EmpresaRepository empresaRepository;

    @InjectMocks
    private EmpresaServiceImpl empresaService;

    @Test
    void testCrearEmpresa() {
        EmpresaDTO empresaDTO = EmpresaDTO.builder()
				.name("test")
            .build();

        Empresa empresa = EmpresaDTO.toDomain(empresaDTO);
        when(empresaRepository.save(any(Empresa.class))).thenReturn(empresa);

        EmpresaDTO resultado = empresaService.crearEmpresa(empresaDTO);

        assertEquals(EmpresaDTO.toDTO(empresa), resultado);
        verify(empresaRepository, times(1)).save(any(Empresa.class));
    }

    @Test
    void testObtenerEmpresaPorId() {
        Empresa empresa = Empresa.builder()
				.name("test")
            .build();

        when(empresaRepository.findById(1L)).thenReturn(Optional.of(empresa));

        Optional<EmpresaDTO> resultado = empresaService.obtenerEmpresaPorId(1L);

        assertEquals(Optional.of(EmpresaDTO.toDTO(empresa)), resultado);
        verify(empresaRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerTodosEmpresa() {
        List<Empresa> empresas = List.of(
            Empresa.builder()
				.name("test")
                .build(),
            Empresa.builder()
				.name("test")
                .build()
        );
		
		Page<Empresa> empresaPage = new PageImpl<>(empresas, PageRequest.of(0, 10), empresas.size());

        when(empresaRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(empresaPage);

        Pageable pageable = PageRequest.of(0, 10);
		
        Map<String, Object> filtros = null;
		
        Page<EmpresaDTO> resultadoPage = empresaService.obtenerTodosEmpresa(pageable, filtros);

        List<EmpresaDTO> expectedDTOs = empresas.stream().map(EmpresaDTO::toDTO).toList();		
		
		for (int i = 0; i < expectedDTOs.size(); i++) {

			assertEquals(expectedDTOs.get(i).getId(), resultadoPage.getContent().get(i).getId());
			assertEquals(expectedDTOs.get(i).getName(), resultadoPage.getContent().get(i).getName());

        }
		
		ArgumentCaptor<Specification<Empresa>> specificationCaptor = ArgumentCaptor.forClass(Specification.class);
		
		verify(empresaRepository, times(1)).findAll(specificationCaptor.capture(), any(Pageable.class));
    }
	
	@Test
     void testObtenerTodosMinEmpresa() {
         List<Empresa> empresas = List.of(
             Empresa.builder()
                 .build(),
             Empresa.builder()
                 .build()
         );
 
         when(empresaRepository.findAll()).thenReturn(empresas);
 
         List<EmpresaDTO> resultado = empresaService.obtenerTodosMinEmpresa();
 
         List<EmpresaDTO> expected = empresas.stream().map(EmpresaDTO::toDTO).toList();
         assertEquals(expected, resultado);
         verify(empresaRepository, times(1)).findAll();
     }

    @Test
    void testActualizarEmpresa() {
        EmpresaDTO empresaDTO = EmpresaDTO.builder()
				.name("test")
            .build();

        Empresa empresa = EmpresaDTO.toDomain(empresaDTO);
        when(empresaRepository.findById(1L)).thenReturn(Optional.of(empresa));
        when(empresaRepository.save(any(Empresa.class))).thenReturn(empresa);

        Optional<EmpresaDTO> resultado = empresaService.actualizarEmpresa(1L, empresaDTO);

        assertEquals(Optional.of(EmpresaDTO.toDTO(empresa)), resultado);
        verify(empresaRepository, times(1)).findById(1L);
        verify(empresaRepository, times(1)).save(any(Empresa.class));
    }

    @Test
    void testEliminarEmpresa() {
        empresaService.eliminarEmpresa(1L);

        verify(empresaRepository, times(1)).deleteById(1L);
    }
}