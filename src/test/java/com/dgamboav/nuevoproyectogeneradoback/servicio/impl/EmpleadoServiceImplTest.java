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

import com.dgamboav.nuevoproyectogeneradoback.repositorio.EmpleadoRepository;
import com.dgamboav.nuevoproyectogeneradoback.dto.EmpleadoDTO;
import com.dgamboav.nuevoproyectogeneradoback.entidad.Empleado;

import org.springframework.data.jpa.domain.Specification;

@ExtendWith(MockitoExtension.class)
class EmpleadoServiceImplTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoServiceImpl empleadoService;

    @Test
    void testCrearEmpleado() {
        EmpleadoDTO empleadoDTO = EmpleadoDTO.builder()
				.nombre("test")
				.departamento("test")
				.salario(1.0)
            .build();

        Empleado empleado = EmpleadoDTO.toDomain(empleadoDTO);
        when(empleadoRepository.save(any(Empleado.class))).thenReturn(empleado);

        EmpleadoDTO resultado = empleadoService.crearEmpleado(empleadoDTO);

        assertEquals(EmpleadoDTO.toDTO(empleado), resultado);
        verify(empleadoRepository, times(1)).save(any(Empleado.class));
    }

    @Test
    void testObtenerEmpleadoPorId() {
        Empleado empleado = Empleado.builder()
				.nombre("test")
				.departamento("test")
				.salario(1.0)
            .build();

        when(empleadoRepository.findById(1L)).thenReturn(Optional.of(empleado));

        Optional<EmpleadoDTO> resultado = empleadoService.obtenerEmpleadoPorId(1L);

        assertEquals(Optional.of(EmpleadoDTO.toDTO(empleado)), resultado);
        verify(empleadoRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerTodosEmpleado() {
        List<Empleado> empleados = List.of(
            Empleado.builder()
				.nombre("test")
				.departamento("test")
				.salario(1.0)
                .build(),
            Empleado.builder()
				.nombre("test")
				.departamento("test")
				.salario(1.0)
                .build()
        );
		
		Page<Empleado> empleadoPage = new PageImpl<>(empleados, PageRequest.of(0, 10), empleados.size());

        when(empleadoRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(empleadoPage);

        Pageable pageable = PageRequest.of(0, 10);
		
        Map<String, Object> filtros = null;
		
        Page<EmpleadoDTO> resultadoPage = empleadoService.obtenerTodosEmpleado(pageable, filtros);

        List<EmpleadoDTO> expectedDTOs = empleados.stream().map(EmpleadoDTO::toDTO).toList();		
		
		for (int i = 0; i < expectedDTOs.size(); i++) {

			assertEquals(expectedDTOs.get(i).getId(), resultadoPage.getContent().get(i).getId());
			assertEquals(expectedDTOs.get(i).getNombre(), resultadoPage.getContent().get(i).getNombre());
			assertEquals(expectedDTOs.get(i).getDepartamento(), resultadoPage.getContent().get(i).getDepartamento());
			assertEquals(expectedDTOs.get(i).getSalario(), resultadoPage.getContent().get(i).getSalario());

        }
		
		ArgumentCaptor<Specification<Empleado>> specificationCaptor = ArgumentCaptor.forClass(Specification.class);
		
		verify(empleadoRepository, times(1)).findAll(specificationCaptor.capture(), any(Pageable.class));
    }
	
	@Test
     void testObtenerTodosMinEmpleado() {
         List<Empleado> empleados = List.of(
             Empleado.builder()
					.nombre("test")
                 .build(),
             Empleado.builder()
					.nombre("test")
                 .build()
         );
 
         when(empleadoRepository.findAll()).thenReturn(empleados);
 
         List<EmpleadoDTO> resultado = empleadoService.obtenerTodosMinEmpleado();
 
         List<EmpleadoDTO> expected = empleados.stream().map(EmpleadoDTO::toDTO).toList();
         assertEquals(expected, resultado);
         verify(empleadoRepository, times(1)).findAll();
     }

    @Test
    void testActualizarEmpleado() {
        EmpleadoDTO empleadoDTO = EmpleadoDTO.builder()
				.nombre("test")
				.departamento("test")
				.salario(1.0)
            .build();

        Empleado empleado = EmpleadoDTO.toDomain(empleadoDTO);
        when(empleadoRepository.findById(1L)).thenReturn(Optional.of(empleado));
        when(empleadoRepository.save(any(Empleado.class))).thenReturn(empleado);

        Optional<EmpleadoDTO> resultado = empleadoService.actualizarEmpleado(1L, empleadoDTO);

        assertEquals(Optional.of(EmpleadoDTO.toDTO(empleado)), resultado);
        verify(empleadoRepository, times(1)).findById(1L);
        verify(empleadoRepository, times(1)).save(any(Empleado.class));
    }

    @Test
    void testEliminarEmpleado() {
        empleadoService.eliminarEmpleado(1L);

        verify(empleadoRepository, times(1)).deleteById(1L);
    }
}