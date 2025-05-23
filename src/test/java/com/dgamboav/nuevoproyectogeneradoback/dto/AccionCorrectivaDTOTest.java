package com.dgamboav.nuevoproyectogeneradoback.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import com.dgamboav.nuevoproyectogeneradoback.entidad.AccionCorrectiva;

class AccionCorrectivaDTOTest {

    @Test
    void testDTO() {
        AccionCorrectivaDTO dto = AccionCorrectivaDTO.builder()
            .id(1L)
            .descripcion("test")
            .fechaImplementacion(null) // Valor por defecto para otros tipos
            .fechaVerificacion(null) // Valor por defecto para otros tipos
            .estado("test")
            .responsableId(1L)
            .createdAt(null) // Valor por defecto para otros tipos
            .updatedAt(null) // Valor por defecto para otros tipos
            .noConformidadId(1L)
            .build();

            assertEquals(1L, dto.getId());
            assertEquals("test", dto.getDescripcion());
            assertNull(dto.getFechaImplementacion()); // Valor por defecto para otros tipos
            assertNull(dto.getFechaVerificacion()); // Valor por defecto para otros tipos
            assertEquals("test", dto.getEstado());
            assertEquals(1L, dto.getResponsableId());
            assertNull(dto.getCreatedAt()); // Valor por defecto para otros tipos
            assertNull(dto.getUpdatedAt()); // Valor por defecto para otros tipos
            assertEquals(1L, dto.getNoConformidadId());

        AccionCorrectivaDTO dto2 = new AccionCorrectivaDTO(
				1L
				, 				
				"test"
				, 				
				null
				, 				
				null
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
        assertTrue(toString.contains("AccionCorrectivaDTO"));
        assertTrue(toString.contains("id"));
        assertTrue(toString.contains("descripcion"));
        assertTrue(toString.contains("fechaImplementacion"));
        assertTrue(toString.contains("fechaVerificacion"));
        assertTrue(toString.contains("estado"));
        assertTrue(toString.contains("responsableId"));
        assertTrue(toString.contains("createdAt"));
        assertTrue(toString.contains("updatedAt"));
        assertTrue(toString.contains("noConformidadId"));
    }

    @Test
    void testToDTO() {
        AccionCorrectiva accionCorrectiva = AccionCorrectiva.builder()
				.id(1L)
				.descripcion("test")
				.fechaImplementacion(null) // Valor por defecto para otros tipos
				.fechaVerificacion(null) // Valor por defecto para otros tipos
				.estado("test")
				.responsableId(1L)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
				.noConformidadId(1L)
            .build();

        AccionCorrectivaDTO dto = AccionCorrectivaDTO.toDTO(accionCorrectiva);

			assertEquals(1L, dto.getId());
			assertEquals("test", dto.getDescripcion());
			assertNull(dto.getFechaImplementacion()); // Valor por defecto para otros tipos
			assertNull(dto.getFechaVerificacion()); // Valor por defecto para otros tipos
			assertEquals("test", dto.getEstado());
			assertEquals(1L, dto.getResponsableId());
			assertNull(dto.getCreatedAt()); // Valor por defecto para otros tipos
			assertNull(dto.getUpdatedAt()); // Valor por defecto para otros tipos
			assertEquals(1L, dto.getNoConformidadId());
    }
	
	@Test
    void testToDTOMinimo() {
        AccionCorrectiva accionCorrectiva = AccionCorrectiva.builder()
			
				.id(1L)
			
            .build();

        AccionCorrectivaDTO dto = AccionCorrectivaDTO.toDTOMinimo(accionCorrectiva);

		
			assertEquals(1L, dto.getId());
		
    }

    @Test
    void testToDomain() {
        AccionCorrectivaDTO dto = AccionCorrectivaDTO.builder()
				.id(1L)
				.descripcion("test")				
				.fechaImplementacion(null) // Valor por defecto para otros tipos
				.fechaVerificacion(null) // Valor por defecto para otros tipos
				.estado("test")				
				.responsableId(1L)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
				.noConformidadId(1L)
            .build();

        AccionCorrectiva accionCorrectiva = AccionCorrectivaDTO.toDomain(dto);
			assertEquals(1L, accionCorrectiva.getId());
			
					assertEquals("test", accionCorrectiva.getDescripcion());
			
			assertNull(accionCorrectiva.getFechaImplementacion()); // Valor por defecto para otros tipos
			assertNull(accionCorrectiva.getFechaVerificacion()); // Valor por defecto para otros tipos
			
					assertEquals("test", accionCorrectiva.getEstado());
			
			assertEquals(1L, accionCorrectiva.getResponsableId());
			assertNull(accionCorrectiva.getCreatedAt()); // Valor por defecto para otros tipos
			assertNull(accionCorrectiva.getUpdatedAt()); // Valor por defecto para otros tipos
			assertEquals(1L, accionCorrectiva.getNoConformidadId());
    }
}