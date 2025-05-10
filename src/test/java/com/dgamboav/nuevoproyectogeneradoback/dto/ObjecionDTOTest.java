package com.dgamboav.nuevoproyectogeneradoback.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import com.dgamboav.nuevoproyectogeneradoback.entidad.Objecion;

class ObjecionDTOTest {

    @Test
    void testDTO() {
        ObjecionDTO dto = ObjecionDTO.builder()
            .id(1L)
            .fechaCreacion(null) // Valor por defecto para otros tipos
            .periodoConstitucional("test")
            .periodoLegislativo("test")
            .numeroProyecto(1)
            .titulo("test")
            .prohija("test")
            .fechaDeLaNota(null) // Valor por defecto para otros tipos
            .alcance("test")
            .motivo("test")
            .build();

            assertEquals(1L, dto.getId());
            assertNull(dto.getFechaCreacion()); // Valor por defecto para otros tipos
            assertEquals("test", dto.getPeriodoConstitucional());
            assertEquals("test", dto.getPeriodoLegislativo());
            assertEquals(1, dto.getNumeroProyecto());
            assertEquals("test", dto.getTitulo());
            assertEquals("test", dto.getProhija());
            assertNull(dto.getFechaDeLaNota()); // Valor por defecto para otros tipos
            assertEquals("test", dto.getAlcance());
            assertEquals("test", dto.getMotivo());

        ObjecionDTO dto2 = new ObjecionDTO(
            1L
			, 
            null
			, 
            "test"
			, 
            "test"
			, 
            1
			, 
            "test"
			, 
            "test"
			, 
            null
			, 
            "test"
			, 
            "test"
			, 
            null
			, 
            null
			, 
            null
			, 
            null
			, 
            null
			, 
            null
			, 
            null
			, 
            null
			, 
            null
			
        );

        assertEquals(dto, dto2);
        assertEquals(dto.hashCode(), dto2.hashCode());

        String toString = dto.toString();
        assertTrue(toString.contains("ObjecionDTO"));
        assertTrue(toString.contains("id"));
        assertTrue(toString.contains("fechaCreacion"));
        assertTrue(toString.contains("periodoConstitucional"));
        assertTrue(toString.contains("periodoLegislativo"));
        assertTrue(toString.contains("numeroProyecto"));
        assertTrue(toString.contains("titulo"));
        assertTrue(toString.contains("prohija"));
        assertTrue(toString.contains("fechaDeLaNota"));
        assertTrue(toString.contains("alcance"));
        assertTrue(toString.contains("motivo"));
    }

    @Test
    void testToDTO() {
        Objecion objecion = Objecion.builder()
				.id(1L)
				.fechaCreacion(null) // Valor por defecto para otros tipos
				.periodoConstitucional("test")
				.periodoLegislativo("test")
				.numeroProyecto(1)
				.titulo("test")
				.prohija("test")
				.fechaDeLaNota(null) // Valor por defecto para otros tipos
				.alcance("test")
				.motivo("test")
            .build();

        ObjecionDTO dto = ObjecionDTO.toDTO(objecion);

			assertEquals(1L, dto.getId());
			assertNull(dto.getFechaCreacion()); // Valor por defecto para otros tipos
			assertEquals("test", dto.getPeriodoConstitucional());
			assertEquals("test", dto.getPeriodoLegislativo());
			assertEquals(1, dto.getNumeroProyecto());
			assertEquals("test", dto.getTitulo());
			assertEquals("test", dto.getProhija());
			assertNull(dto.getFechaDeLaNota()); // Valor por defecto para otros tipos
			assertEquals("test", dto.getAlcance());
			assertEquals("test", dto.getMotivo());
    }
	
	@Test
    void testToDTOMinimo() {
        Objecion objecion = Objecion.builder()
			
				.id(1L)
			
			
				.periodoConstitucional("test")
			
			
				.periodoLegislativo("test")
			
			
				.informeDeGobierno("test")
			
			
				.informeDeTrabajo("test")
			
            .build();

        ObjecionDTO dto = ObjecionDTO.toDTOMinimo(objecion);

		
			assertEquals(1L, dto.getId());
		
		
			assertEquals("test", dto.getPeriodoConstitucional());
		
		
			assertEquals("test", dto.getPeriodoLegislativo());
		
		
			assertEquals("test", dto.getInformeDeGobierno());
		
		
			assertEquals("test", dto.getInformeDeTrabajo());
		
    }

    @Test
    void testToDomain() {
        ObjecionDTO dto = ObjecionDTO.builder()
				.id(1L)
				.fechaCreacion(null) // Valor por defecto para otros tipos
				.periodoConstitucional("test")				
				.periodoLegislativo("test")				
				.numeroProyecto(1)
				.titulo("test")				
				.prohija("test")				
				.fechaDeLaNota(null) // Valor por defecto para otros tipos
				.alcance("test")				
				.motivo("test")				
            .build();

        Objecion objecion = ObjecionDTO.toDomain(dto);
			assertEquals(1L, objecion.getId());
			assertNull(objecion.getFechaCreacion()); // Valor por defecto para otros tipos
			
					assertEquals("test", objecion.getPeriodoConstitucional());
			
			
					assertEquals("test", objecion.getPeriodoLegislativo());
			
			assertEquals(1, objecion.getNumeroProyecto());
			
					assertEquals("test", objecion.getTitulo());
			
			
					assertEquals("test", objecion.getProhija());
			
			assertNull(objecion.getFechaDeLaNota()); // Valor por defecto para otros tipos
			
					assertEquals("test", objecion.getAlcance());
			
			
					assertEquals("test", objecion.getMotivo());
			
    }
}