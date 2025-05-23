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

import com.dgamboav.nuevoproyectogeneradoback.dto.ProcesoDTO;
import com.dgamboav.nuevoproyectogeneradoback.servicio.ProcesoService;
@SpringBootTest
@AutoConfigureMockMvc
class ProcesoRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Qualifier("mockProcesoService")
    private ProcesoService procesoService;

    @Autowired
    private ObjectMapper objectMapper;
	
	@org.springframework.boot.test.context.TestConfiguration
    static class TestConfig {
        @Bean("mockProcesoService")
        @Primary
        public ProcesoService procesoService() {
            return Mockito.mock(ProcesoService.class);
        }
    }

    @Test
    void testCrearProceso() throws Exception {
		ProcesoDTO procesoDTO = ProcesoDTO.builder()
		.id(1L)
		
		.nombre("test")
		.descripcion("test")
		.codigoProceso("test")
		.responsableId(1L)
		
		 .createdAt(null)
		 .updatedAt(null)
		.empresaId(1L)
		.build();

        when(procesoService.crearProceso(any(ProcesoDTO.class))).thenReturn(procesoDTO);

        mockMvc.perform(post("/api/procesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(procesoDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testObtenerProcesoPorId() throws Exception {

		ProcesoDTO procesoDTO = ProcesoDTO.builder()
		.id(1L)
		
		.nombre("test")
		.descripcion("test")
		.codigoProceso("test")
		.responsableId(1L)
		
		 .createdAt(null)
		 .updatedAt(null)
		.empresaId(1L)
		.build();

        when(procesoService.obtenerProcesoPorId(1L)).thenReturn(Optional.of(procesoDTO));

        mockMvc.perform(get("/api/procesos/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testActualizarProceso() throws Exception {
		ProcesoDTO procesoDTO = ProcesoDTO.builder()
		.id(1L)
		
		.nombre("test")
		.descripcion("test")
		.codigoProceso("test")
		.responsableId(1L)
		
		 .createdAt(null)
		 .updatedAt(null)
		.empresaId(1L)
		.build();

        when(procesoService.actualizarProceso(eq(1L), any(ProcesoDTO.class))).thenReturn(Optional.of(procesoDTO));

        mockMvc.perform(put("/api/procesos/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(procesoDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testEliminarProceso() throws Exception {
        mockMvc.perform(delete("/api/procesos/1"))
            .andExpect(status().isNoContent());
    }

    @Test
    void testObtenerTodosProceso() throws Exception {
        List<ProcesoDTO> procesoDTOs = List.of(
            ProcesoDTO.builder()
		.id(1L)
		
		.nombre("test")
		.descripcion("test")
		.codigoProceso("test")
		.responsableId(1L)
		
		 .createdAt(null)
		 .updatedAt(null)
		.empresaId(1L)
		.build(),
         ProcesoDTO.builder()
		.id(2L)
		
		.nombre("test")
		.descripcion("test")
		.codigoProceso("test")
		.responsableId(1L)
		
		 .createdAt(null)
		 .updatedAt(null)
		.empresaId(1L)
		.build()
        );
		
		// Datos de prueba para la página
		PageRequest pageable = PageRequest.of(0, 10);
        Page<ProcesoDTO> procesoDTOPage = new PageImpl<>(procesoDTOs, pageable, procesoDTOs.size());

		Map<String, Object> filtrosEsperados = new HashMap<>();
		filtrosEsperados.put("page", "0");
		filtrosEsperados.put("size", "10");

        when(procesoService.obtenerTodosProceso(pageable, filtrosEsperados))
		.thenReturn(procesoDTOPage);

        mockMvc.perform(get("/api/procesos")
		        .param("page", "0")
                .param("size", "10"))
            .andExpect(status().isOk())
			.andExpect(jsonPath("$.content").exists())
            .andExpect(jsonPath("$.content").isArray())
            .andExpect(jsonPath("$.content[0].id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }
	
	@Test
     void testObtenerTodosMinProceso() throws Exception {
         List<ProcesoDTO> procesoDTOs = List.of(
             ProcesoDTO.builder()
 		.id(1L)
 		
 		.nombre("test")
 		.descripcion("test")
 		.codigoProceso("test")
 		.responsableId(1L)
 		
 		 .createdAt(null)
 		 .updatedAt(null)
 		.empresaId(1L)
 		.build(),
          ProcesoDTO.builder()
 		.id(2L)
 		
 		.nombre("test")
 		.descripcion("test")
 		.codigoProceso("test")
 		.responsableId(1L)
 		
 		 .createdAt(null)
 		 .updatedAt(null)
 		.empresaId(1L)
 		.build()
         );
 
         when(procesoService.obtenerTodosMinProceso()).thenReturn(procesoDTOs);
 
         mockMvc.perform(get("/api/procesos/todosMinimo"))
             .andExpect(status().isOk())
             .andExpect(jsonPath("$").isArray())
             .andExpect(jsonPath("$[0].id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
     }
	
}