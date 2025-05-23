package com.dgamboav.nuevoproyectogeneradoback.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import com.dgamboav.nuevoproyectogeneradoback.entidad.NoConformidad;

class NoConformidadDTOTest {

    @Test
    void testDTO() {
        NoConformidadDTO dto = NoConformidadDTO.builder()
            .id(1L)
            .descripcion("test")
            .causaRaiz("test")
            .clasificacion("test")
            .fechaDeteccion(null) // Valor por defecto para otros tipos
            .responsableId(1L)
            .estado("test")
            .createdAt(null) // Valor por defecto para otros tipos
            .updatedAt(null) // Valor por defecto para otros tipos
            .auditoriaId(1L)
            .build();

            assertEquals(1L, dto.getId());
            assertEquals("test", dto.getDescripcion());
            assertEquals("test", dto.getCausaRaiz());
            assertEquals("test", dto.getClasificacion());
            assertNull(dto.getFechaDeteccion()); // Valor por defecto para otros tipos
            assertEquals(1L, dto.getResponsableId());
            assertEquals("test", dto.getEstado());
            assertNull(dto.getCreatedAt()); // Valor por defecto para otros tipos
            assertNull(dto.getUpdatedAt()); // Valor por defecto para otros tipos
            assertEquals(1L, dto.getAuditoriaId());

        NoConformidadDTO dto2 = new NoConformidadDTO(
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
				1L
				, 				
				"test"
				, 				
				null
				, 				
				null
				, 				
				1L
				, 				
				null
						
        );

        assertEquals(dto, dto2);
        assertEquals(dto.hashCode(), dto2.hashCode());

        String toString = dto.toString();
        assertTrue(toString.contains("NoConformidadDTO"));
        assertTrue(toString.contains("id"));
        assertTrue(toString.contains("descripcion"));
        assertTrue(toString.contains("causaRaiz"));
        assertTrue(toString.contains("clasificacion"));
        assertTrue(toString.contains("fechaDeteccion"));
        assertTrue(toString.contains("responsableId"));
        assertTrue(toString.contains("estado"));
        assertTrue(toString.contains("createdAt"));
        assertTrue(toString.contains("updatedAt"));
        assertTrue(toString.contains("auditoriaId"));
    }

    @Test
    void testToDTO() {
        NoConformidad noConformidad = NoConformidad.builder()
				.id(1L)
				.descripcion("test")
				.causaRaiz("test")
				.clasificacion("test")
				.fechaDeteccion(null) // Valor por defecto para otros tipos
				.responsableId(1L)
				.estado("test")
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
				.auditoriaId(1L)
            .build();

        NoConformidadDTO dto = NoConformidadDTO.toDTO(noConformidad);

			assertEquals(1L, dto.getId());
			assertEquals("test", dto.getDescripcion());
			assertEquals("test", dto.getCausaRaiz());
			assertEquals("test", dto.getClasificacion());
			assertNull(dto.getFechaDeteccion()); // Valor por defecto para otros tipos
			assertEquals(1L, dto.getResponsableId());
			assertEquals("test", dto.getEstado());
			assertNull(dto.getCreatedAt()); // Valor por defecto para otros tipos
			assertNull(dto.getUpdatedAt()); // Valor por defecto para otros tipos
			assertEquals(1L, dto.getAuditoriaId());
    }
	
	@Test
    void testToDTOMinimo() {
        NoConformidad noConformidad = NoConformidad.builder()
			
				.id(1L)
			
            .build();

        NoConformidadDTO dto = NoConformidadDTO.toDTOMinimo(noConformidad);

		
			assertEquals(1L, dto.getId());
		
    }

    @Test
    void testToDomain() {
        NoConformidadDTO dto = NoConformidadDTO.builder()
				.id(1L)
				.descripcion("test")				
				.causaRaiz("test")				
				.clasificacion("test")				
				.fechaDeteccion(null) // Valor por defecto para otros tipos
				.responsableId(1L)
				.estado("test")				
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
				.auditoriaId(1L)
            .build();

        NoConformidad noConformidad = NoConformidadDTO.toDomain(dto);
			assertEquals(1L, noConformidad.getId());
			
					assertEquals("test", noConformidad.getDescripcion());
			
			
					assertEquals("test", noConformidad.getCausaRaiz());
			
			
					assertEquals("test", noConformidad.getClasificacion());
			
			assertNull(noConformidad.getFechaDeteccion()); // Valor por defecto para otros tipos
			assertEquals(1L, noConformidad.getResponsableId());
			
					assertEquals("test", noConformidad.getEstado());
			
			assertNull(noConformidad.getCreatedAt()); // Valor por defecto para otros tipos
			assertNull(noConformidad.getUpdatedAt()); // Valor por defecto para otros tipos
			assertEquals(1L, noConformidad.getAuditoriaId());
    }
}