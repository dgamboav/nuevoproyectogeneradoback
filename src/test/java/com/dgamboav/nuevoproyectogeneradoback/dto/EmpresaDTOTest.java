package com.dgamboav.nuevoproyectogeneradoback.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import com.dgamboav.nuevoproyectogeneradoback.entidad.Empresa;

class EmpresaDTOTest {

    @Test
    void testDTO() {
        EmpresaDTO dto = EmpresaDTO.builder()
            .id(1L)
            .name("test")
            .build();

            assertEquals(1L, dto.getId());
            assertEquals("test", dto.getName());

        EmpresaDTO dto2 = new EmpresaDTO(
				1L
				, 				
				"test"
								
        );

        assertEquals(dto, dto2);
        assertEquals(dto.hashCode(), dto2.hashCode());

        String toString = dto.toString();
        assertTrue(toString.contains("EmpresaDTO"));
        assertTrue(toString.contains("id"));
        assertTrue(toString.contains("name"));
    }

    @Test
    void testToDTO() {
        Empresa empresa = Empresa.builder()
				.id(1L)
				.name("test")
            .build();

        EmpresaDTO dto = EmpresaDTO.toDTO(empresa);

			assertEquals(1L, dto.getId());
			assertEquals("test", dto.getName());
    }
	
	@Test
    void testToDTOMinimo() {
        Empresa empresa = Empresa.builder()
			
				.id(1L)
			
            .build();

        EmpresaDTO dto = EmpresaDTO.toDTOMinimo(empresa);

		
			assertEquals(1L, dto.getId());
		
    }

    @Test
    void testToDomain() {
        EmpresaDTO dto = EmpresaDTO.builder()
				.id(1L)
				.name("test")				
            .build();

        Empresa empresa = EmpresaDTO.toDomain(dto);
			assertEquals(1L, empresa.getId());
			
					assertEquals("test", empresa.getName());
			
    }
}