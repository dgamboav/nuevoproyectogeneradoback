package com.dgamboav.nuevoproyectogeneradoback.controlador.rest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.PageImpl;

import com.dgamboav.nuevoproyectogeneradoback.dto.FaqDTO;
import com.dgamboav.nuevoproyectogeneradoback.servicio.FaqService;

@SpringBootTest
@AutoConfigureMockMvc
class FaqRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Qualifier("mockFaqService")
    private FaqService faqService;

    @Autowired
    private ObjectMapper objectMapper;
	
	@org.springframework.boot.test.context.TestConfiguration
    static class TestConfig {
        @Bean("mockFaqService")
        @Primary
        public FaqService faqService() {
            return Mockito.mock(FaqService.class);
        }
    }

    @Test
    void testCrearFaq() throws Exception {
		FaqDTO faqDTO = FaqDTO.builder()
		.id(1L)
		
		.question("test")
		 .answer(null)
		.specificOrder(1)
		 .createdAt(null)
		 .updatedAt(null).build();

        when(faqService.crearFaq(any(FaqDTO.class))).thenReturn(faqDTO);

        mockMvc.perform(post("/api/faqs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(faqDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testObtenerFaqPorId() throws Exception {

		FaqDTO faqDTO = FaqDTO.builder()
		.id(1L)
		
		.question("test")
		 .answer(null)
		.specificOrder(1)
		
		 .createdAt(null)
		 .updatedAt(null).build();

        when(faqService.obtenerFaqPorId(1L)).thenReturn(Optional.of(faqDTO));

        mockMvc.perform(get("/api/faqs/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testActualizarFaq() throws Exception {
		FaqDTO faqDTO = FaqDTO.builder()
		.id(1L)
		
		.question("test")
		 .answer(null)
		.specificOrder(1)
		
		 .createdAt(null)
		 .updatedAt(null).build();

        when(faqService.actualizarFaq(eq(1L), any(FaqDTO.class))).thenReturn(Optional.of(faqDTO));

        mockMvc.perform(put("/api/faqs/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(faqDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testEliminarFaq() throws Exception {
        mockMvc.perform(delete("/api/faqs/1"))
            .andExpect(status().isNoContent());
    }

    @Test
    void testObtenerTodosFaq() throws Exception {
        List<FaqDTO> faqDTOs = List.of(
            FaqDTO.builder()
		.id(1L)
		
		.question("test")
		 .answer(null)
		.specificOrder(1)
		
		 .createdAt(null)
		 .updatedAt(null).build(),
         FaqDTO.builder()
		.id(2L)
		
		.question("test")
		 .answer(null)
		.specificOrder(1)
		
		 .createdAt(null)
		 .updatedAt(null).build()
        );
		
		// Datos de prueba para la página
		PageRequest pageable = PageRequest.of(0, 10);
        Page<FaqDTO> faqDTOPage = new PageImpl<>(faqDTOs, pageable, faqDTOs.size());

		Map<String, Object> filtrosEsperados = new HashMap<>();
		filtrosEsperados.put("page", "0");
		filtrosEsperados.put("size", "10");

        when(faqService.obtenerTodosFaq(pageable, filtrosEsperados))
		.thenReturn(faqDTOPage);

        mockMvc.perform(get("/api/faqs")
		        .param("page", "0")
                .param("size", "10"))
            .andExpect(status().isOk())
			.andExpect(jsonPath("$.content").exists())
            .andExpect(jsonPath("$.content").isArray())
            .andExpect(jsonPath("$.content[0].id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }
	
	@Test
     void testObtenerTodosMinFaq() throws Exception {
         List<FaqDTO> faqDTOs = List.of(
             FaqDTO.builder()
 		.id(1L)
 		
 		.question("test")
 		 .answer(null)
 		.specificOrder(1)
 		
 		 .createdAt(null)
 		 .updatedAt(null).build(),
          FaqDTO.builder()
 		.id(2L)
		
 		.question("test")
 		 .answer(null)
 		.specificOrder(1)
 		
 		 .createdAt(null)
 		 .updatedAt(null).build()
         );
 
         when(faqService.obtenerTodosMinFaq()).thenReturn(faqDTOs);
 
         mockMvc.perform(get("/api/faqs/todosMinimo"))
             .andExpect(status().isOk())
             .andExpect(jsonPath("$").isArray())
             .andExpect(jsonPath("$[0].id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
     }
	
}