package com.dgamboav.nuevoproyectogeneradoback.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import com.dgamboav.nuevoproyectogeneradoback.entidad.Conformidad;

class ConformidadDTOTest {

    @Test
    void testDTO() {
        ConformidadDTO dto = ConformidadDTO.builder()
            .id(1L)
            .descripcion("test")
            .fechaDeteccion(null) // Valor por defecto para otros tipos
            .responsableId(1L)
            .createdAt(null) // Valor por defecto para otros tipos
            .updatedAt(null) // Valor por defecto para otros tipos
            .auditoriaId(1L)
            .procesoId(1L)
            .build();

            assertEquals(1L, dto.getId());
            assertEquals("test", dto.getDescripcion());
            assertNull(dto.getFechaDeteccion()); // Valor por defecto para otros tipos
            assertEquals(1L, dto.getResponsableId());
            assertNull(dto.getCreatedAt()); // Valor por defecto para otros tipos
            assertNull(dto.getUpdatedAt()); // Valor por defecto para otros tipos
            assertEquals(1L, dto.getAuditoriaId());
            assertEquals(1L, dto.getProcesoId());

        ConformidadDTO dto2 = new ConformidadDTO(
				1L
				, 				
				"test"
				, 				
				null
				, 				
				1L
				, 				
				null
				, 				
				null
				, 				
				1L
				, 				
				1L
								
        );

        assertEquals(dto, dto2);
        assertEquals(dto.hashCode(), dto2.hashCode());

        String toString = dto.toString();
        assertTrue(toString.contains("ConformidadDTO"));
        assertTrue(toString.contains("id"));
        assertTrue(toString.contains("descripcion"));
        assertTrue(toString.contains("fechaDeteccion"));
        assertTrue(toString.contains("responsableId"));
        assertTrue(toString.contains("createdAt"));
        assertTrue(toString.contains("updatedAt"));
        assertTrue(toString.contains("auditoriaId"));
        assertTrue(toString.contains("procesoId"));
    }

    @Test
    void testToDTO() {
        Conformidad conformidad = Conformidad.builder()
				.id(1L)
				.descripcion("test")
				.fechaDeteccion(null) // Valor por defecto para otros tipos
				.responsableId(1L)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
				.auditoriaId(1L)
				.procesoId(1L)
            .build();

        ConformidadDTO dto = ConformidadDTO.toDTO(conformidad);

			assertEquals(1L, dto.getId());
			assertEquals("test", dto.getDescripcion());
			assertNull(dto.getFechaDeteccion()); // Valor por defecto para otros tipos
			assertEquals(1L, dto.getResponsableId());
			assertNull(dto.getCreatedAt()); // Valor por defecto para otros tipos
			assertNull(dto.getUpdatedAt()); // Valor por defecto para otros tipos
			assertEquals(1L, dto.getAuditoriaId());
			assertEquals(1L, dto.getProcesoId());
    }
	
	@Test
    void testToDTOMinimo() {
        Conformidad conformidad = Conformidad.builder()
			
				.id(1L)
			
            .build();

        ConformidadDTO dto = ConformidadDTO.toDTOMinimo(conformidad);

		
			assertEquals(1L, dto.getId());
		
    }

    @Test
    void testToDomain() {
        ConformidadDTO dto = ConformidadDTO.builder()
				.id(1L)
				.descripcion("test")				
				.fechaDeteccion(null) // Valor por defecto para otros tipos
				.responsableId(1L)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
				.auditoriaId(1L)
				.procesoId(1L)
            .build();

        Conformidad conformidad = ConformidadDTO.toDomain(dto);
			assertEquals(1L, conformidad.getId());
			
					assertEquals("test", conformidad.getDescripcion());
			
			assertNull(conformidad.getFechaDeteccion()); // Valor por defecto para otros tipos
			assertEquals(1L, conformidad.getResponsableId());
			assertNull(conformidad.getCreatedAt()); // Valor por defecto para otros tipos
			assertNull(conformidad.getUpdatedAt()); // Valor por defecto para otros tipos
			assertEquals(1L, conformidad.getAuditoriaId());
			assertEquals(1L, conformidad.getProcesoId());
    }
}