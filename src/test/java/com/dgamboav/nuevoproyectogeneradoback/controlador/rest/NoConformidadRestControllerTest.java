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

import com.dgamboav.nuevoproyectogeneradoback.dto.NoConformidadDTO;
import com.dgamboav.nuevoproyectogeneradoback.servicio.NoConformidadService;
@SpringBootTest
@AutoConfigureMockMvc
class NoConformidadRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Qualifier("mockNoConformidadService")
    private NoConformidadService noConformidadService;

    @Autowired
    private ObjectMapper objectMapper;
	
	@org.springframework.boot.test.context.TestConfiguration
    static class TestConfig {
        @Bean("mockNoConformidadService")
        @Primary
        public NoConformidadService noConformidadService() {
            return Mockito.mock(NoConformidadService.class);
        }
    }

    @Test
    void testCrearNoConformidad() throws Exception {
		NoConformidadDTO noConformidadDTO = NoConformidadDTO.builder()
		.id(1L)
		
		.descripcion("test")
		.causaRaiz("test")
		.clasificacion("test")
		 .fechaDeteccion(null)
		.responsableId(1L)
		
		.estado("test")
		 .createdAt(null)
		 .updatedAt(null)
		.auditoriaId(1L)
		
		.procesoId(1L)
		.build();

        when(noConformidadService.crearNoConformidad(any(NoConformidadDTO.class))).thenReturn(noConformidadDTO);

        mockMvc.perform(post("/api/noconformidads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(noConformidadDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testObtenerNoConformidadPorId() throws Exception {

		NoConformidadDTO noConformidadDTO = NoConformidadDTO.builder()
		.id(1L)
		
		.descripcion("test")
		.causaRaiz("test")
		.clasificacion("test")
		 .fechaDeteccion(null)
		.responsableId(1L)
		
		.estado("test")
		 .createdAt(null)
		 .updatedAt(null)
		.auditoriaId(1L)
		
		.procesoId(1L)
		.build();

        when(noConformidadService.obtenerNoConformidadPorId(1L)).thenReturn(Optional.of(noConformidadDTO));

        mockMvc.perform(get("/api/noconformidads/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testActualizarNoConformidad() throws Exception {
		NoConformidadDTO noConformidadDTO = NoConformidadDTO.builder()
		.id(1L)
		
		.descripcion("test")
		.causaRaiz("test")
		.clasificacion("test")
		 .fechaDeteccion(null)
		.responsableId(1L)
		
		.estado("test")
		 .createdAt(null)
		 .updatedAt(null)
		.auditoriaId(1L)
		
		.procesoId(1L)
		.build();

        when(noConformidadService.actualizarNoConformidad(eq(1L), any(NoConformidadDTO.class))).thenReturn(Optional.of(noConformidadDTO));

        mockMvc.perform(put("/api/noconformidads/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(noConformidadDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testEliminarNoConformidad() throws Exception {
        mockMvc.perform(delete("/api/noconformidads/1"))
            .andExpect(status().isNoContent());
    }

    @Test
    void testObtenerTodosNoConformidad() throws Exception {
        List<NoConformidadDTO> noConformidadDTOs = List.of(
            NoConformidadDTO.builder()
		.id(1L)
		
		.descripcion("test")
		.causaRaiz("test")
		.clasificacion("test")
		 .fechaDeteccion(null)
		.responsableId(1L)
		
		.estado("test")
		 .createdAt(null)
		 .updatedAt(null)
		.auditoriaId(1L)
		
		.procesoId(1L)
		.build(),
         NoConformidadDTO.builder()
		.id(2L)
		
		.descripcion("test")
		.causaRaiz("test")
		.clasificacion("test")
		 .fechaDeteccion(null)
		.responsableId(1L)
		
		.estado("test")
		 .createdAt(null)
		 .updatedAt(null)
		.auditoriaId(1L)
		
		.procesoId(1L)
		.build()
        );
		
		// Datos de prueba para la página
		PageRequest pageable = PageRequest.of(0, 10);
        Page<NoConformidadDTO> noConformidadDTOPage = new PageImpl<>(noConformidadDTOs, pageable, noConformidadDTOs.size());

		Map<String, Object> filtrosEsperados = new HashMap<>();
		filtrosEsperados.put("page", "0");
		filtrosEsperados.put("size", "10");

        when(noConformidadService.obtenerTodosNoConformidad(pageable, filtrosEsperados))
		.thenReturn(noConformidadDTOPage);

        mockMvc.perform(get("/api/noconformidads")
		        .param("page", "0")
                .param("size", "10"))
            .andExpect(status().isOk())
			.andExpect(jsonPath("$.content").exists())
            .andExpect(jsonPath("$.content").isArray())
            .andExpect(jsonPath("$.content[0].id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }
	
	@Test
     void testObtenerTodosMinNoConformidad() throws Exception {
         List<NoConformidadDTO> noConformidadDTOs = List.of(
             NoConformidadDTO.builder()
 		.id(1L)
 		
 		.descripcion("test")
 		.causaRaiz("test")
 		.clasificacion("test")
 		 .fechaDeteccion(null)
 		.responsableId(1L)
 		
 		.estado("test")
 		 .createdAt(null)
 		 .updatedAt(null)
 		.auditoriaId(1L)
 		
 		.procesoId(1L)
 		.build(),
          NoConformidadDTO.builder()
 		.id(2L)
 		
 		.descripcion("test")
 		.causaRaiz("test")
 		.clasificacion("test")
 		 .fechaDeteccion(null)
 		.responsableId(1L)
 		
 		.estado("test")
 		 .createdAt(null)
 		 .updatedAt(null)
 		.auditoriaId(1L)
 		
 		.procesoId(1L)
 		.build()
         );
 
         when(noConformidadService.obtenerTodosMinNoConformidad()).thenReturn(noConformidadDTOs);
 
         mockMvc.perform(get("/api/noconformidads/todosMinimo"))
             .andExpect(status().isOk())
             .andExpect(jsonPath("$").isArray())
             .andExpect(jsonPath("$[0].id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
     }
	
}