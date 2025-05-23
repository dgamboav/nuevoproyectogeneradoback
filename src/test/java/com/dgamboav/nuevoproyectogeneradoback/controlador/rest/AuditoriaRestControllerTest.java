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

import com.dgamboav.nuevoproyectogeneradoback.dto.AuditoriaDTO;
import com.dgamboav.nuevoproyectogeneradoback.servicio.AuditoriaService;
@SpringBootTest
@AutoConfigureMockMvc
class AuditoriaRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Qualifier("mockAuditoriaService")
    private AuditoriaService auditoriaService;

    @Autowired
    private ObjectMapper objectMapper;
	
	@org.springframework.boot.test.context.TestConfiguration
    static class TestConfig {
        @Bean("mockAuditoriaService")
        @Primary
        public AuditoriaService auditoriaService() {
            return Mockito.mock(AuditoriaService.class);
        }
    }

    @Test
    void testCrearAuditoria() throws Exception {
		AuditoriaDTO auditoriaDTO = AuditoriaDTO.builder()
		.id(1L)
		
		 .fechaInicio(null)
		 .fechaFin(null)
		.tipoAuditoria("test")
		.auditorLiderId(1L)
		
		.objetivo("test")
		.alcance("test")
		.estado("test")
		 .createdAt(null)
		 .updatedAt(null).build();

        when(auditoriaService.crearAuditoria(any(AuditoriaDTO.class))).thenReturn(auditoriaDTO);

        mockMvc.perform(post("/api/auditorias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(auditoriaDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testObtenerAuditoriaPorId() throws Exception {

		AuditoriaDTO auditoriaDTO = AuditoriaDTO.builder()
		.id(1L)
		
		 .fechaInicio(null)
		 .fechaFin(null)
		.tipoAuditoria("test")
		.auditorLiderId(1L)
		
		.objetivo("test")
		.alcance("test")
		.estado("test")
		 .createdAt(null)
		 .updatedAt(null).build();

        when(auditoriaService.obtenerAuditoriaPorId(1L)).thenReturn(Optional.of(auditoriaDTO));

        mockMvc.perform(get("/api/auditorias/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testActualizarAuditoria() throws Exception {
		AuditoriaDTO auditoriaDTO = AuditoriaDTO.builder()
		.id(1L)
		
		 .fechaInicio(null)
		 .fechaFin(null)
		.tipoAuditoria("test")
		.auditorLiderId(1L)
		
		.objetivo("test")
		.alcance("test")
		.estado("test")
		 .createdAt(null)
		 .updatedAt(null).build();

        when(auditoriaService.actualizarAuditoria(eq(1L), any(AuditoriaDTO.class))).thenReturn(Optional.of(auditoriaDTO));

        mockMvc.perform(put("/api/auditorias/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(auditoriaDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testEliminarAuditoria() throws Exception {
        mockMvc.perform(delete("/api/auditorias/1"))
            .andExpect(status().isNoContent());
    }

    @Test
    void testObtenerTodosAuditoria() throws Exception {
        List<AuditoriaDTO> auditoriaDTOs = List.of(
            AuditoriaDTO.builder()
		.id(1L)
		
		 .fechaInicio(null)
		 .fechaFin(null)
		.tipoAuditoria("test")
		.auditorLiderId(1L)
		
		.objetivo("test")
		.alcance("test")
		.estado("test")
		 .createdAt(null)
		 .updatedAt(null).build(),
         AuditoriaDTO.builder()
		.id(2L)
		
		 .fechaInicio(null)
		 .fechaFin(null)
		.tipoAuditoria("test")
		.auditorLiderId(1L)
		
		.objetivo("test")
		.alcance("test")
		.estado("test")
		 .createdAt(null)
		 .updatedAt(null).build()
        );
		
		// Datos de prueba para la página
		PageRequest pageable = PageRequest.of(0, 10);
        Page<AuditoriaDTO> auditoriaDTOPage = new PageImpl<>(auditoriaDTOs, pageable, auditoriaDTOs.size());

		Map<String, Object> filtrosEsperados = new HashMap<>();
		filtrosEsperados.put("page", "0");
		filtrosEsperados.put("size", "10");

        when(auditoriaService.obtenerTodosAuditoria(pageable, filtrosEsperados))
		.thenReturn(auditoriaDTOPage);

        mockMvc.perform(get("/api/auditorias")
		        .param("page", "0")
                .param("size", "10"))
            .andExpect(status().isOk())
			.andExpect(jsonPath("$.content").exists())
            .andExpect(jsonPath("$.content").isArray())
            .andExpect(jsonPath("$.content[0].id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }
	
	@Test
     void testObtenerTodosMinAuditoria() throws Exception {
         List<AuditoriaDTO> auditoriaDTOs = List.of(
             AuditoriaDTO.builder()
 		.id(1L)
 		
 		 .fechaInicio(null)
 		 .fechaFin(null)
 		.tipoAuditoria("test")
 		.auditorLiderId(1L)
 		
 		.objetivo("test")
 		.alcance("test")
 		.estado("test")
 		 .createdAt(null)
 		 .updatedAt(null).build(),
          AuditoriaDTO.builder()
 		.id(2L)
 		
 		 .fechaInicio(null)
 		 .fechaFin(null)
 		.tipoAuditoria("test")
 		.auditorLiderId(1L)
 		
 		.objetivo("test")
 		.alcance("test")
 		.estado("test")
 		 .createdAt(null)
 		 .updatedAt(null).build()
         );
 
         when(auditoriaService.obtenerTodosMinAuditoria()).thenReturn(auditoriaDTOs);
 
         mockMvc.perform(get("/api/auditorias/todosMinimo"))
             .andExpect(status().isOk())
             .andExpect(jsonPath("$").isArray())
             .andExpect(jsonPath("$[0].id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
     }
	
}