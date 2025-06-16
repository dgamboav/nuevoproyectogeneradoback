package com.dgamboav.nuevoproyectogeneradoback.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.dgamboav.nuevoproyectogeneradoback.entidad.Proyecto;

class ProyectoDTOTest {

    @Test
    void testDTO() {
        ProyectoDTO dto = ProyectoDTO.builder()
            .id(1L)
            .clienteId(1L)
            .empleadoId(1L)
            .fechaCreacion(null) // Valor por defecto para otros tipos
            .costo(1.0)
            .build();

            assertEquals(1L, dto.getId());
            assertEquals(1L, dto.getClienteId());
            assertEquals(1L, dto.getEmpleadoId());
            assertNull(dto.getFechaCreacion()); // Valor por defecto para otros tipos
            assertEquals(1.0, dto.getCosto());

        ProyectoDTO dto2 = new ProyectoDTO(
				1L
				, 				
				1L
				, 				
				1L
				, 				
				null
				, 				
				1.0
								
        );

        assertEquals(dto, dto2);
        assertEquals(dto.hashCode(), dto2.hashCode());

        String toString = dto.toString();
        assertTrue(toString.contains("ProyectoDTO"));
        assertTrue(toString.contains("id"));
        assertTrue(toString.contains("clienteId"));
        assertTrue(toString.contains("empleadoId"));
        assertTrue(toString.contains("fechaCreacion"));
        assertTrue(toString.contains("costo"));
    }

    @Test
    void testToDTO() {
        Proyecto proyecto = Proyecto.builder()
				.id(1L)
				.clienteId(1L)
				.empleadoId(1L)
				.fechaCreacion(null) // Valor por defecto para otros tipos
				.costo(1.0)
            .build();

        ProyectoDTO dto = ProyectoDTO.toDTO(proyecto);

			assertEquals(1L, dto.getId());
			assertEquals(1L, dto.getClienteId());
			assertEquals(1L, dto.getEmpleadoId());
			assertNull(dto.getFechaCreacion()); // Valor por defecto para otros tipos
			assertEquals(1.0, dto.getCosto());
    }
	
	@Test
    void testToDTOMinimo() {
        Proyecto proyecto = Proyecto.builder()
			
				.id(1L)
			
			
				.clienteId(1L)
			
			
				.empleadoId(1L)
			
            .build();

        ProyectoDTO dto = ProyectoDTO.toDTOMinimo(proyecto);

		
			assertEquals(1L, dto.getId());
		
		
			assertEquals(1L, dto.getClienteId());
		
		
			assertEquals(1L, dto.getEmpleadoId());
		
    }

    @Test
    void testToDomain() {
        ProyectoDTO dto = ProyectoDTO.builder()
				.id(1L)
				.clienteId(1L)
				.empleadoId(1L)
				.fechaCreacion(null) // Valor por defecto para otros tipos
				.costo(1.0)
            .build();

        Proyecto proyecto = ProyectoDTO.toDomain(dto);
			assertEquals(1L, proyecto.getId());
			assertEquals(1L, proyecto.getClienteId());
			assertEquals(1L, proyecto.getEmpleadoId());
			assertNull(proyecto.getFechaCreacion()); // Valor por defecto para otros tipos
			assertEquals(1.0, proyecto.getCosto());
    }
	
	@Test
    void testCopyProperties_successfulCopy() {
	
	
        Proyecto targetProyecto = Proyecto.builder()
				.id(100L)
				.clienteId(100L)
				.empleadoId(100L)
				.fechaCreacion(null) // Valor por defecto para otros tipos
				.costo(10.0)
            .build();
			
		Proyecto sourceProyecto = Proyecto.builder()
				.id(1L)
				.clienteId(1L)
				.empleadoId(1L)
				.fechaCreacion(null) // Valor por defecto para otros tipos
				.costo(1.0)
            .build();

        Proyecto result = ProyectoDTO.copyProperties(sourceProyecto, targetProyecto);

        assertEquals(sourceProyecto.getClienteId(), result.getClienteId());
        assertEquals(sourceProyecto.getEmpleadoId(), result.getEmpleadoId());
        assertEquals(sourceProyecto.getFechaCreacion(), result.getFechaCreacion());
        assertEquals(sourceProyecto.getCosto(), result.getCosto());

        assertEquals(100L, result.getId());
        assertSame(targetProyecto, result);
    }
	
	
}