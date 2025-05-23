package com.dgamboav.nuevoproyectogeneradoback.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import com.dgamboav.nuevoproyectogeneradoback.entidad.Proceso;

class ProcesoDTOTest {

    @Test
    void testDTO() {
        ProcesoDTO dto = ProcesoDTO.builder()
            .id(1L)
            .nombre("test")
            .descripcion("test")
            .codigoProceso("test")
            .responsableId(1L)
            .createdAt(null) // Valor por defecto para otros tipos
            .updatedAt(null) // Valor por defecto para otros tipos
            .empresaId(1L)
            .build();

            assertEquals(1L, dto.getId());
            assertEquals("test", dto.getNombre());
            assertEquals("test", dto.getDescripcion());
            assertEquals("test", dto.getCodigoProceso());
            assertEquals(1L, dto.getResponsableId());
            assertNull(dto.getCreatedAt()); // Valor por defecto para otros tipos
            assertNull(dto.getUpdatedAt()); // Valor por defecto para otros tipos
            assertEquals(1L, dto.getEmpresaId());

        ProcesoDTO dto2 = new ProcesoDTO(
				1L
				, 				
				"test"
				, 				
				"test"
				, 				
				"test"
				, 				
				1L
				, 				
				null
				, 				
				null
				, 				
				1L
								
        );

        assertEquals(dto, dto2);
        assertEquals(dto.hashCode(), dto2.hashCode());

        String toString = dto.toString();
        assertTrue(toString.contains("ProcesoDTO"));
        assertTrue(toString.contains("id"));
        assertTrue(toString.contains("nombre"));
        assertTrue(toString.contains("descripcion"));
        assertTrue(toString.contains("codigoProceso"));
        assertTrue(toString.contains("responsableId"));
        assertTrue(toString.contains("createdAt"));
        assertTrue(toString.contains("updatedAt"));
        assertTrue(toString.contains("empresaId"));
    }

    @Test
    void testToDTO() {
        Proceso proceso = Proceso.builder()
				.id(1L)
				.nombre("test")
				.descripcion("test")
				.codigoProceso("test")
				.responsableId(1L)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
				.empresaId(1L)
            .build();

        ProcesoDTO dto = ProcesoDTO.toDTO(proceso);

			assertEquals(1L, dto.getId());
			assertEquals("test", dto.getNombre());
			assertEquals("test", dto.getDescripcion());
			assertEquals("test", dto.getCodigoProceso());
			assertEquals(1L, dto.getResponsableId());
			assertNull(dto.getCreatedAt()); // Valor por defecto para otros tipos
			assertNull(dto.getUpdatedAt()); // Valor por defecto para otros tipos
			assertEquals(1L, dto.getEmpresaId());
    }
	
	@Test
    void testToDTOMinimo() {
        Proceso proceso = Proceso.builder()
			
				.id(1L)
			
            .build();

        ProcesoDTO dto = ProcesoDTO.toDTOMinimo(proceso);

		
			assertEquals(1L, dto.getId());
		
    }

    @Test
    void testToDomain() {
        ProcesoDTO dto = ProcesoDTO.builder()
				.id(1L)
				.nombre("test")				
				.descripcion("test")				
				.codigoProceso("test")				
				.responsableId(1L)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
				.empresaId(1L)
            .build();

        Proceso proceso = ProcesoDTO.toDomain(dto);
			assertEquals(1L, proceso.getId());
			
					assertEquals("test", proceso.getNombre());
			
			
					assertEquals("test", proceso.getDescripcion());
			
			
					assertEquals("test", proceso.getCodigoProceso());
			
			assertEquals(1L, proceso.getResponsableId());
			assertNull(proceso.getCreatedAt()); // Valor por defecto para otros tipos
			assertNull(proceso.getUpdatedAt()); // Valor por defecto para otros tipos
			assertEquals(1L, proceso.getEmpresaId());
    }
}