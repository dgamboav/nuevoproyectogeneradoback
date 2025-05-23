package com.dgamboav.nuevoproyectogeneradoback.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import com.dgamboav.nuevoproyectogeneradoback.entidad.Auditoria;

class AuditoriaDTOTest {

    @Test
    void testDTO() {
        AuditoriaDTO dto = AuditoriaDTO.builder()
            .id(1L)
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

            assertEquals(1L, dto.getId());
            assertNull(dto.getFechaInicio()); // Valor por defecto para otros tipos
            assertNull(dto.getFechaFin()); // Valor por defecto para otros tipos
            assertEquals("test", dto.getTipoAuditoria());
            assertEquals(1L, dto.getAuditorLiderId());
            assertEquals("test", dto.getObjetivo());
            assertEquals("test", dto.getAlcance());
            assertEquals("test", dto.getEstado());
            assertNull(dto.getCreatedAt()); // Valor por defecto para otros tipos
            assertNull(dto.getUpdatedAt()); // Valor por defecto para otros tipos

        AuditoriaDTO dto2 = new AuditoriaDTO(
				1L
				, 				
				null
				, 				
				null
				, 				
				"test"
				, 				
				1L
				, 				
				"test"
				, 				
				"test"
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
        assertTrue(toString.contains("AuditoriaDTO"));
        assertTrue(toString.contains("id"));
        assertTrue(toString.contains("fechaInicio"));
        assertTrue(toString.contains("fechaFin"));
        assertTrue(toString.contains("tipoAuditoria"));
        assertTrue(toString.contains("auditorLiderId"));
        assertTrue(toString.contains("objetivo"));
        assertTrue(toString.contains("alcance"));
        assertTrue(toString.contains("estado"));
        assertTrue(toString.contains("createdAt"));
        assertTrue(toString.contains("updatedAt"));
    }

    @Test
    void testToDTO() {
        Auditoria auditoria = Auditoria.builder()
				.id(1L)
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

        AuditoriaDTO dto = AuditoriaDTO.toDTO(auditoria);

			assertEquals(1L, dto.getId());
			assertNull(dto.getFechaInicio()); // Valor por defecto para otros tipos
			assertNull(dto.getFechaFin()); // Valor por defecto para otros tipos
			assertEquals("test", dto.getTipoAuditoria());
			assertEquals(1L, dto.getAuditorLiderId());
			assertEquals("test", dto.getObjetivo());
			assertEquals("test", dto.getAlcance());
			assertEquals("test", dto.getEstado());
			assertNull(dto.getCreatedAt()); // Valor por defecto para otros tipos
			assertNull(dto.getUpdatedAt()); // Valor por defecto para otros tipos
    }
	
	@Test
    void testToDTOMinimo() {
        Auditoria auditoria = Auditoria.builder()
			
				.id(1L)
			
			
				.tipoAuditoria("test")
			
            .build();

        AuditoriaDTO dto = AuditoriaDTO.toDTOMinimo(auditoria);

		
			assertEquals(1L, dto.getId());
		
		
			assertEquals("test", dto.getTipoAuditoria());
		
    }

    @Test
    void testToDomain() {
        AuditoriaDTO dto = AuditoriaDTO.builder()
				.id(1L)
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

        Auditoria auditoria = AuditoriaDTO.toDomain(dto);
			assertEquals(1L, auditoria.getId());
			assertNull(auditoria.getFechaInicio()); // Valor por defecto para otros tipos
			assertNull(auditoria.getFechaFin()); // Valor por defecto para otros tipos
			
					assertEquals("test", auditoria.getTipoAuditoria());
			
			assertEquals(1L, auditoria.getAuditorLiderId());
			
					assertEquals("test", auditoria.getObjetivo());
			
			
					assertEquals("test", auditoria.getAlcance());
			
			
					assertEquals("test", auditoria.getEstado());
			
			assertNull(auditoria.getCreatedAt()); // Valor por defecto para otros tipos
			assertNull(auditoria.getUpdatedAt()); // Valor por defecto para otros tipos
    }
}