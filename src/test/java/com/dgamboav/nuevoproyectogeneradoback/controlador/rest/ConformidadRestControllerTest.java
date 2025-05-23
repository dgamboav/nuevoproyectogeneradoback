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

import com.dgamboav.nuevoproyectogeneradoback.dto.ConformidadDTO;
import com.dgamboav.nuevoproyectogeneradoback.servicio.ConformidadService;
@SpringBootTest
@AutoConfigureMockMvc
class ConformidadRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Qualifier("mockConformidadService")
    private ConformidadService conformidadService;

    @Autowired
    private ObjectMapper objectMapper;
	
	@org.springframework.boot.test.context.TestConfiguration
    static class TestConfig {
        @Bean("mockConformidadService")
        @Primary
        public ConformidadService conformidadService() {
            return Mockito.mock(ConformidadService.class);
        }
    }

    @Test
    void testCrearConformidad() throws Exception {
		ConformidadDTO conformidadDTO = ConformidadDTO.builder()
		.id(1L)
		
		.descripcion("test")
		 .fechaDeteccion(null)
		.responsableId(1L)
		
		 .createdAt(null)
		 .updatedAt(null)
		.auditoriaId(1L)
		
		.procesoId(1L)
		.build();

        when(conformidadService.crearConformidad(any(ConformidadDTO.class))).thenReturn(conformidadDTO);

        mockMvc.perform(post("/api/conformidads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(conformidadDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testObtenerConformidadPorId() throws Exception {

		ConformidadDTO conformidadDTO = ConformidadDTO.builder()
		.id(1L)
		
		.descripcion("test")
		 .fechaDeteccion(null)
		.responsableId(1L)
		
		 .createdAt(null)
		 .updatedAt(null)
		.auditoriaId(1L)
		
		.procesoId(1L)
		.build();

        when(conformidadService.obtenerConformidadPorId(1L)).thenReturn(Optional.of(conformidadDTO));

        mockMvc.perform(get("/api/conformidads/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testActualizarConformidad() throws Exception {
		ConformidadDTO conformidadDTO = ConformidadDTO.builder()
		.id(1L)
		
		.descripcion("test")
		 .fechaDeteccion(null)
		.responsableId(1L)
		
		 .createdAt(null)
		 .updatedAt(null)
		.auditoriaId(1L)
		
		.procesoId(1L)
		.build();

        when(conformidadService.actualizarConformidad(eq(1L), any(ConformidadDTO.class))).thenReturn(Optional.of(conformidadDTO));

        mockMvc.perform(put("/api/conformidads/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(conformidadDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testEliminarConformidad() throws Exception {
        mockMvc.perform(delete("/api/conformidads/1"))
            .andExpect(status().isNoContent());
    }

    @Test
    void testObtenerTodosConformidad() throws Exception {
        List<ConformidadDTO> conformidadDTOs = List.of(
            ConformidadDTO.builder()
		.id(1L)
		
		.descripcion("test")
		 .fechaDeteccion(null)
		.responsableId(1L)
		
		 .createdAt(null)
		 .updatedAt(null)
		.auditoriaId(1L)
		
		.procesoId(1L)
		.build(),
         ConformidadDTO.builder()
		.id(2L)
		
		.descripcion("test")
		 .fechaDeteccion(null)
		.responsableId(1L)
		
		 .createdAt(null)
		 .updatedAt(null)
		.auditoriaId(1L)
		
		.procesoId(1L)
		.build()
        );
		
		// Datos de prueba para la página
		PageRequest pageable = PageRequest.of(0, 10);
        Page<ConformidadDTO> conformidadDTOPage = new PageImpl<>(conformidadDTOs, pageable, conformidadDTOs.size());

		Map<String, Object> filtrosEsperados = new HashMap<>();
		filtrosEsperados.put("page", "0");
		filtrosEsperados.put("size", "10");

        when(conformidadService.obtenerTodosConformidad(pageable, filtrosEsperados))
		.thenReturn(conformidadDTOPage);

        mockMvc.perform(get("/api/conformidads")
		        .param("page", "0")
                .param("size", "10"))
            .andExpect(status().isOk())
			.andExpect(jsonPath("$.content").exists())
            .andExpect(jsonPath("$.content").isArray())
            .andExpect(jsonPath("$.content[0].id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }
	
	@Test
     void testObtenerTodosMinConformidad() throws Exception {
         List<ConformidadDTO> conformidadDTOs = List.of(
             ConformidadDTO.builder()
 		.id(1L)
 		
 		.descripcion("test")
 		 .fechaDeteccion(null)
 		.responsableId(1L)
 		
 		 .createdAt(null)
 		 .updatedAt(null)
 		.auditoriaId(1L)
 		
 		.procesoId(1L)
 		.build(),
          ConformidadDTO.builder()
 		.id(2L)
 		
 		.descripcion("test")
 		 .fechaDeteccion(null)
 		.responsableId(1L)
 		
 		 .createdAt(null)
 		 .updatedAt(null)
 		.auditoriaId(1L)
 		
 		.procesoId(1L)
 		.build()
         );
 
         when(conformidadService.obtenerTodosMinConformidad()).thenReturn(conformidadDTOs);
 
         mockMvc.perform(get("/api/conformidads/todosMinimo"))
             .andExpect(status().isOk())
             .andExpect(jsonPath("$").isArray())
             .andExpect(jsonPath("$[0].id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
     }
	
}