package com.dgamboav.nuevoproyectogeneradoback.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.dgamboav.nuevoproyectogeneradoback.entidad.Faq;

class FaqDTOTest {

    @Test
    void testDTO() {
        FaqDTO dto = FaqDTO.builder()
            .id(1L)
            .question("test")
            .answer(null) // Valor por defecto para otros tipos
            .specificOrder(1)
            .createdAt(null) // Valor por defecto para otros tipos
            .updatedAt(null) // Valor por defecto para otros tipos
            .build();

            assertEquals(1L, dto.getId());
            assertEquals("test", dto.getQuestion());
            assertNull(dto.getAnswer()); // Valor por defecto para otros tipos
            assertEquals(1, dto.getSpecificOrder());
            assertNull(dto.getCreatedAt()); // Valor por defecto para otros tipos
            assertNull(dto.getUpdatedAt()); // Valor por defecto para otros tipos

        FaqDTO dto2 = new FaqDTO(
				1L
				, 				
				"test"
				, 				
				null
				, 				
				1
				, 				
				null
				, 				
				null
								
        );

        assertEquals(dto, dto2);
        assertEquals(dto.hashCode(), dto2.hashCode());

        String toString = dto.toString();
        assertTrue(toString.contains("FaqDTO"));
        assertTrue(toString.contains("id"));
        assertTrue(toString.contains("question"));
        assertTrue(toString.contains("answer"));
        assertTrue(toString.contains("specificOrder"));
        assertTrue(toString.contains("createdAt"));
        assertTrue(toString.contains("updatedAt"));
    }

    @Test
    void testToDTO() {
        Faq faq = Faq.builder()
				.id(1L)
				.question("test")
				.answer(null) // Valor por defecto para otros tipos
				.specificOrder(1)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
            .build();

        FaqDTO dto = FaqDTO.toDTO(faq);

			assertEquals(1L, dto.getId());
			assertEquals("test", dto.getQuestion());
			assertNull(dto.getAnswer()); // Valor por defecto para otros tipos
			assertEquals(1, dto.getSpecificOrder());
			assertNull(dto.getCreatedAt()); // Valor por defecto para otros tipos
			assertNull(dto.getUpdatedAt()); // Valor por defecto para otros tipos
    }
	
	@Test
    void testToDTOMinimo() {
        Faq faq = Faq.builder()
			
				.id(1L)
			
			
				.question("test")
			
            .build();

        FaqDTO dto = FaqDTO.toDTOMinimo(faq);

		
			assertEquals(1L, dto.getId());
		
		
			assertEquals("test", dto.getQuestion());
		
    }

    @Test
    void testToDomain() {
        FaqDTO dto = FaqDTO.builder()
				.id(1L)
				.question("test")				
				.answer(null) // Valor por defecto para otros tipos
				.specificOrder(1)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
            .build();

        Faq faq = FaqDTO.toDomain(dto);
			assertEquals(1L, faq.getId());
			
					assertEquals("test", faq.getQuestion());
			
			assertNull(faq.getAnswer()); // Valor por defecto para otros tipos
			assertEquals(1, faq.getSpecificOrder());
			assertNull(faq.getCreatedAt()); // Valor por defecto para otros tipos
			assertNull(faq.getUpdatedAt()); // Valor por defecto para otros tipos
    }
	
	@Test
    void testCopyProperties_successfulCopy() {
	
	
        Faq targetFaq = Faq.builder()
				.id(100L)
				.question("test")
				.answer(null) // Valor por defecto para otros tipos
				.specificOrder(100)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
            .build();
			
		Faq sourceFaq = Faq.builder()
				.id(1L)
				.question("test")
				.answer(null) // Valor por defecto para otros tipos
				.specificOrder(1)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
            .build();

        Faq result = FaqDTO.copyProperties(sourceFaq, targetFaq);

        assertEquals(sourceFaq.getQuestion(), result.getQuestion());
        assertEquals(sourceFaq.getAnswer(), result.getAnswer());
        assertEquals(sourceFaq.getSpecificOrder(), result.getSpecificOrder());
        assertEquals(sourceFaq.getCreatedAt(), result.getCreatedAt());
        assertEquals(sourceFaq.getUpdatedAt(), result.getUpdatedAt());

        assertEquals(100L, result.getId());
        assertSame(targetFaq, result);
    }
	
	
}