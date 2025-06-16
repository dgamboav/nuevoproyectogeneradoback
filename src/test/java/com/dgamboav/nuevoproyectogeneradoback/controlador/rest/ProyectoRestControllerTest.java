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

import com.dgamboav.nuevoproyectogeneradoback.dto.ProyectoDTO;
import com.dgamboav.nuevoproyectogeneradoback.servicio.ProyectoService;

@SpringBootTest
@AutoConfigureMockMvc
class ProyectoRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Qualifier("mockProyectoService")
    private ProyectoService proyectoService;

    @Autowired
    private ObjectMapper objectMapper;
	
	@org.springframework.boot.test.context.TestConfiguration
    static class TestConfig {
        @Bean("mockProyectoService")
        @Primary
        public ProyectoService proyectoService() {
            return Mockito.mock(ProyectoService.class);
        }
    }

    @Test
    void testCrearProyecto() throws Exception {
		ProyectoDTO proyectoDTO = ProyectoDTO.builder()
		.id(1L)
		
		.clienteId(1L)
		
		.empleadoId(1L)
		
		 .fechaCreacion(null)
		.costo(1.0)
		.build();

        when(proyectoService.crearProyecto(any(ProyectoDTO.class))).thenReturn(proyectoDTO);

        mockMvc.perform(post("/api/proyectos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(proyectoDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testObtenerProyectoPorId() throws Exception {

		ProyectoDTO proyectoDTO = ProyectoDTO.builder()
		.id(1L)
		
		.clienteId(1L)
		
		.empleadoId(1L)
		
		 .fechaCreacion(null)
		.costo(1.0)
		.build();

        when(proyectoService.obtenerProyectoPorId(1L)).thenReturn(Optional.of(proyectoDTO));

        mockMvc.perform(get("/api/proyectos/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testActualizarProyecto() throws Exception {
		ProyectoDTO proyectoDTO = ProyectoDTO.builder()
		.id(1L)
		
		.clienteId(1L)
		
		.empleadoId(1L)
		
		 .fechaCreacion(null)
		.costo(1.0)
		.build();

        when(proyectoService.actualizarProyecto(eq(1L), any(ProyectoDTO.class))).thenReturn(Optional.of(proyectoDTO));

        mockMvc.perform(put("/api/proyectos/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(proyectoDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testEliminarProyecto() throws Exception {
        mockMvc.perform(delete("/api/proyectos/1"))
            .andExpect(status().isNoContent());
    }

    @Test
    void testObtenerTodosProyecto() throws Exception {
        List<ProyectoDTO> proyectoDTOs = List.of(
            ProyectoDTO.builder()
		.id(1L)
		
		.clienteId(1L)
		
		.empleadoId(1L)
		
		 .fechaCreacion(null)
		.costo(1.0)
		.build(),
         ProyectoDTO.builder()
		.id(2L)
		
		.clienteId(1L)
		
		.empleadoId(1L)
		
		 .fechaCreacion(null)
		.costo(1.0)
		.build()
        );
		
		// Datos de prueba para la página
		PageRequest pageable = PageRequest.of(0, 10);
        Page<ProyectoDTO> proyectoDTOPage = new PageImpl<>(proyectoDTOs, pageable, proyectoDTOs.size());

		Map<String, Object> filtrosEsperados = new HashMap<>();
		filtrosEsperados.put("page", "0");
		filtrosEsperados.put("size", "10");

        when(proyectoService.obtenerTodosProyecto(pageable, filtrosEsperados))
		.thenReturn(proyectoDTOPage);

        mockMvc.perform(get("/api/proyectos")
		        .param("page", "0")
                .param("size", "10"))
            .andExpect(status().isOk())
			.andExpect(jsonPath("$.content").exists())
            .andExpect(jsonPath("$.content").isArray())
            .andExpect(jsonPath("$.content[0].id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }
	
	@Test
     void testObtenerTodosMinProyecto() throws Exception {
         List<ProyectoDTO> proyectoDTOs = List.of(
             ProyectoDTO.builder()
 		.id(1L)
 		
 		.clienteId(1L)
 		
 		.empleadoId(1L)
 		
 		 .fechaCreacion(null)
 		.costo(1.0)
 		.build(),
          ProyectoDTO.builder()
 		.id(2L)
		
 		.clienteId(1L)
 		
 		.empleadoId(1L)
 		
 		 .fechaCreacion(null)
 		.costo(1.0)
 		.build()
         );
 
         when(proyectoService.obtenerTodosMinProyecto()).thenReturn(proyectoDTOs);
 
         mockMvc.perform(get("/api/proyectos/todosMinimo"))
             .andExpect(status().isOk())
             .andExpect(jsonPath("$").isArray())
             .andExpect(jsonPath("$[0].id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
     }
	
}