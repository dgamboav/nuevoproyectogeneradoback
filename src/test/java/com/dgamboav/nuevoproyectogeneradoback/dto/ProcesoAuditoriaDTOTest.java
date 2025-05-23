package com.dgamboav.nuevoproyectogeneradoback.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import com.dgamboav.nuevoproyectogeneradoback.entidad.ProcesoAuditoria;

class ProcesoAuditoriaDTOTest {

    @Test
    void testDTO() {
        ProcesoAuditoriaDTO dto = ProcesoAuditoriaDTO.builder()
            .id(1L)
            .auditoriaId(1L)
            .procesoId(1L)
            .resultado("test")
            .createdAt(null) // Valor por defecto para otros tipos
            .updatedAt(null) // Valor por defecto para otros tipos
            .build();

            assertEquals(1L, dto.getId());
            assertEquals(1L, dto.getAuditoriaId());
            assertEquals(1L, dto.getProcesoId());
            assertEquals("test", dto.getResultado());
            assertNull(dto.getCreatedAt()); // Valor por defecto para otros tipos
            assertNull(dto.getUpdatedAt()); // Valor por defecto para otros tipos

        ProcesoAuditoriaDTO dto2 = new ProcesoAuditoriaDTO(
				1L
				, 				
				1L
				, 				
				1L
				, 				
				"test"
				, 				
				null
				, 				
				null
								
        );

        assertEquals(dto, dto2);
        assertEquals(dto.hashCode(), dto2.hashCode());

        String toString = dto.toString();
        assertTrue(toString.contains("ProcesoAuditoriaDTO"));
        assertTrue(toString.contains("id"));
        assertTrue(toString.contains("auditoriaId"));
        assertTrue(toString.contains("procesoId"));
        assertTrue(toString.contains("resultado"));
        assertTrue(toString.contains("createdAt"));
        assertTrue(toString.contains("updatedAt"));
    }

    @Test
    void testToDTO() {
        ProcesoAuditoria procesoAuditoria = ProcesoAuditoria.builder()
				.id(1L)
				.auditoriaId(1L)
				.procesoId(1L)
				.resultado("test")
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
            .build();

        ProcesoAuditoriaDTO dto = ProcesoAuditoriaDTO.toDTO(procesoAuditoria);

			assertEquals(1L, dto.getId());
			assertEquals(1L, dto.getAuditoriaId());
			assertEquals(1L, dto.getProcesoId());
			assertEquals("test", dto.getResultado());
			assertNull(dto.getCreatedAt()); // Valor por defecto para otros tipos
			assertNull(dto.getUpdatedAt()); // Valor por defecto para otros tipos
    }
	
	@Test
    void testToDTOMinimo() {
        ProcesoAuditoria procesoAuditoria = ProcesoAuditoria.builder()
			
				.id(1L)
			
            .build();

        ProcesoAuditoriaDTO dto = ProcesoAuditoriaDTO.toDTOMinimo(procesoAuditoria);

		
			assertEquals(1L, dto.getId());
		
    }

    @Test
    void testToDomain() {
        ProcesoAuditoriaDTO dto = ProcesoAuditoriaDTO.builder()
				.id(1L)
				.auditoriaId(1L)
				.procesoId(1L)
				.resultado("test")				
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
            .build();

        ProcesoAuditoria procesoAuditoria = ProcesoAuditoriaDTO.toDomain(dto);
			assertEquals(1L, procesoAuditoria.getId());
			assertEquals(1L, procesoAuditoria.getAuditoriaId());
			assertEquals(1L, procesoAuditoria.getProcesoId());
			
					assertEquals("test", procesoAuditoria.getResultado());
			
			assertNull(procesoAuditoria.getCreatedAt()); // Valor por defecto para otros tipos
			assertNull(procesoAuditoria.getUpdatedAt()); // Valor por defecto para otros tipos
    }
}