package com.dgamboav.nuevoproyectogeneradoback.servicio.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.PageImpl;

import com.dgamboav.nuevoproyectogeneradoback.repositorio.FaqRepository;
import com.dgamboav.nuevoproyectogeneradoback.dto.FaqDTO;
import com.dgamboav.nuevoproyectogeneradoback.entidad.Faq;

import org.springframework.data.jpa.domain.Specification;

@ExtendWith(MockitoExtension.class)
class FaqServiceImplTest {

    @Mock
    private FaqRepository faqRepository;

    @InjectMocks
    private FaqServiceImpl faqService;

    @Test
    void testCrearFaq() {
        FaqDTO faqDTO = FaqDTO.builder()
				.question("test")
				.answer(null) // Valor por defecto para otros tipos
				.specificOrder(1)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
            .build();

        Faq faq = FaqDTO.toDomain(faqDTO);
        when(faqRepository.save(any(Faq.class))).thenReturn(faq);

        FaqDTO resultado = faqService.crearFaq(faqDTO);

        assertEquals(FaqDTO.toDTO(faq), resultado);
        verify(faqRepository, times(1)).save(any(Faq.class));
    }

    @Test
    void testObtenerFaqPorId() {
        Faq faq = Faq.builder()
				.question("test")
				.answer(null) // Valor por defecto para otros tipos
				.specificOrder(1)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
            .build();

        when(faqRepository.findById(1L)).thenReturn(Optional.of(faq));

        Optional<FaqDTO> resultado = faqService.obtenerFaqPorId(1L);

        assertEquals(Optional.of(FaqDTO.toDTO(faq)), resultado);
        verify(faqRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerTodosFaq() {
        List<Faq> faqs = List.of(
            Faq.builder()
				.question("test")
				.answer(null) // Valor por defecto para otros tipos
				.specificOrder(1)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
                .build(),
            Faq.builder()
				.question("test")
				.answer(null) // Valor por defecto para otros tipos
				.specificOrder(1)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
                .build()
        );
		
		Page<Faq> faqPage = new PageImpl<>(faqs, PageRequest.of(0, 10), faqs.size());

        when(faqRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(faqPage);

        Pageable pageable = PageRequest.of(0, 10);
		
        Map<String, Object> filtros = null;
		
        Page<FaqDTO> resultadoPage = faqService.obtenerTodosFaq(pageable, filtros);

        List<FaqDTO> expectedDTOs = faqs.stream().map(FaqDTO::toDTO).toList();		
		
		for (int i = 0; i < expectedDTOs.size(); i++) {

			assertEquals(expectedDTOs.get(i).getId(), resultadoPage.getContent().get(i).getId());
			assertEquals(expectedDTOs.get(i).getQuestion(), resultadoPage.getContent().get(i).getQuestion());
			assertEquals(expectedDTOs.get(i).getAnswer(), resultadoPage.getContent().get(i).getAnswer());
			assertEquals(expectedDTOs.get(i).getSpecificOrder(), resultadoPage.getContent().get(i).getSpecificOrder());
			assertEquals(expectedDTOs.get(i).getCreatedAt(), resultadoPage.getContent().get(i).getCreatedAt());
			assertEquals(expectedDTOs.get(i).getUpdatedAt(), resultadoPage.getContent().get(i).getUpdatedAt());

        }
		
		ArgumentCaptor<Specification<Faq>> specificationCaptor = ArgumentCaptor.forClass(Specification.class);
		
		verify(faqRepository, times(1)).findAll(specificationCaptor.capture(), any(Pageable.class));
    }
	
	@Test
     void testObtenerTodosMinFaq() {
         List<Faq> faqs = List.of(
             Faq.builder()
					.question("test")
                 .build(),
             Faq.builder()
					.question("test")
                 .build()
         );
 
         when(faqRepository.findAll()).thenReturn(faqs);
 
         List<FaqDTO> resultado = faqService.obtenerTodosMinFaq();
 
         List<FaqDTO> expected = faqs.stream().map(FaqDTO::toDTO).toList();
         assertEquals(expected, resultado);
         verify(faqRepository, times(1)).findAll();
     }

    @Test
    void testActualizarFaq() {
        FaqDTO faqDTO = FaqDTO.builder()
				.question("test")
				.answer(null) // Valor por defecto para otros tipos
				.specificOrder(1)
				.createdAt(null) // Valor por defecto para otros tipos
				.updatedAt(null) // Valor por defecto para otros tipos
            .build();

        Faq faq = FaqDTO.toDomain(faqDTO);
        when(faqRepository.findById(1L)).thenReturn(Optional.of(faq));
        when(faqRepository.save(any(Faq.class))).thenReturn(faq);

        Optional<FaqDTO> resultado = faqService.actualizarFaq(1L, faqDTO);

        assertEquals(Optional.of(FaqDTO.toDTO(faq)), resultado);
        verify(faqRepository, times(1)).findById(1L);
        verify(faqRepository, times(1)).save(any(Faq.class));
    }

    @Test
    void testEliminarFaq() {
        faqService.eliminarFaq(1L);

        verify(faqRepository, times(1)).deleteById(1L);
    }
}