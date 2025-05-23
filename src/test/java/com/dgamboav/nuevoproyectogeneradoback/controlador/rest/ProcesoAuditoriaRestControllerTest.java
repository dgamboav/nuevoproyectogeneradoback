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

import com.dgamboav.nuevoproyectogeneradoback.dto.ProcesoAuditoriaDTO;
import com.dgamboav.nuevoproyectogeneradoback.servicio.ProcesoAuditoriaService;
@SpringBootTest
@AutoConfigureMockMvc
class ProcesoAuditoriaRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Qualifier("mockProcesoAuditoriaService")
    private ProcesoAuditoriaService procesoAuditoriaService;

    @Autowired
    private ObjectMapper objectMapper;
	
	@org.springframework.boot.test.context.TestConfiguration
    static class TestConfig {
        @Bean("mockProcesoAuditoriaService")
        @Primary
        public ProcesoAuditoriaService procesoAuditoriaService() {
            return Mockito.mock(ProcesoAuditoriaService.class);
        }
    }

    @Test
    void testCrearProcesoAuditoria() throws Exception {
		ProcesoAuditoriaDTO procesoAuditoriaDTO = ProcesoAuditoriaDTO.builder()
		.id(1L)
		
		.auditoriaId(1L)
		
		.procesoId(1L)
		
		.resultado("test")
		 .createdAt(null)
		 .updatedAt(null).build();

        when(procesoAuditoriaService.crearProcesoAuditoria(any(ProcesoAuditoriaDTO.class))).thenReturn(procesoAuditoriaDTO);

        mockMvc.perform(post("/api/procesoauditorias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(procesoAuditoriaDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testObtenerProcesoAuditoriaPorId() throws Exception {

		ProcesoAuditoriaDTO procesoAuditoriaDTO = ProcesoAuditoriaDTO.builder()
		.id(1L)
		
		.auditoriaId(1L)
		
		.procesoId(1L)
		
		.resultado("test")
		 .createdAt(null)
		 .updatedAt(null).build();

        when(procesoAuditoriaService.obtenerProcesoAuditoriaPorId(1L)).thenReturn(Optional.of(procesoAuditoriaDTO));

        mockMvc.perform(get("/api/procesoauditorias/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testActualizarProcesoAuditoria() throws Exception {
		ProcesoAuditoriaDTO procesoAuditoriaDTO = ProcesoAuditoriaDTO.builder()
		.id(1L)
		
		.auditoriaId(1L)
		
		.procesoId(1L)
		
		.resultado("test")
		 .createdAt(null)
		 .updatedAt(null).build();

        when(procesoAuditoriaService.actualizarProcesoAuditoria(eq(1L), any(ProcesoAuditoriaDTO.class))).thenReturn(Optional.of(procesoAuditoriaDTO));

        mockMvc.perform(put("/api/procesoauditorias/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(procesoAuditoriaDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testEliminarProcesoAuditoria() throws Exception {
        mockMvc.perform(delete("/api/procesoauditorias/1"))
            .andExpect(status().isNoContent());
    }

    @Test
    void testObtenerTodosProcesoAuditoria() throws Exception {
        List<ProcesoAuditoriaDTO> procesoAuditoriaDTOs = List.of(
            ProcesoAuditoriaDTO.builder()
		.id(1L)
		
		.auditoriaId(1L)
		
		.procesoId(1L)
		
		.resultado("test")
		 .createdAt(null)
		 .updatedAt(null).build(),
         ProcesoAuditoriaDTO.builder()
		.id(2L)
		
		.auditoriaId(1L)
		
		.procesoId(1L)
		
		.resultado("test")
		 .createdAt(null)
		 .updatedAt(null).build()
        );
		
		// Datos de prueba para la página
		PageRequest pageable = PageRequest.of(0, 10);
        Page<ProcesoAuditoriaDTO> procesoAuditoriaDTOPage = new PageImpl<>(procesoAuditoriaDTOs, pageable, procesoAuditoriaDTOs.size());

		Map<String, Object> filtrosEsperados = new HashMap<>();
		filtrosEsperados.put("page", "0");
		filtrosEsperados.put("size", "10");

        when(procesoAuditoriaService.obtenerTodosProcesoAuditoria(pageable, filtrosEsperados))
		.thenReturn(procesoAuditoriaDTOPage);

        mockMvc.perform(get("/api/procesoauditorias")
		        .param("page", "0")
                .param("size", "10"))
            .andExpect(status().isOk())
			.andExpect(jsonPath("$.content").exists())
            .andExpect(jsonPath("$.content").isArray())
            .andExpect(jsonPath("$.content[0].id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }
	
	@Test
     void testObtenerTodosMinProcesoAuditoria() throws Exception {
         List<ProcesoAuditoriaDTO> procesoAuditoriaDTOs = List.of(
             ProcesoAuditoriaDTO.builder()
 		.id(1L)
 		
 		.auditoriaId(1L)
 		
 		.procesoId(1L)
 		
 		.resultado("test")
 		 .createdAt(null)
 		 .updatedAt(null).build(),
          ProcesoAuditoriaDTO.builder()
 		.id(2L)
 		
 		.auditoriaId(1L)
 		
 		.procesoId(1L)
 		
 		.resultado("test")
 		 .createdAt(null)
 		 .updatedAt(null).build()
         );
 
         when(procesoAuditoriaService.obtenerTodosMinProcesoAuditoria()).thenReturn(procesoAuditoriaDTOs);
 
         mockMvc.perform(get("/api/procesoauditorias/todosMinimo"))
             .andExpect(status().isOk())
             .andExpect(jsonPath("$").isArray())
             .andExpect(jsonPath("$[0].id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
     }
	
}