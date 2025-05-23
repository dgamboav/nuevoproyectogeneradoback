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

import com.dgamboav.nuevoproyectogeneradoback.dto.EmpresaDTO;
import com.dgamboav.nuevoproyectogeneradoback.servicio.EmpresaService;
@SpringBootTest
@AutoConfigureMockMvc
class EmpresaRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Qualifier("mockEmpresaService")
    private EmpresaService empresaService;

    @Autowired
    private ObjectMapper objectMapper;
	
	@org.springframework.boot.test.context.TestConfiguration
    static class TestConfig {
        @Bean("mockEmpresaService")
        @Primary
        public EmpresaService empresaService() {
            return Mockito.mock(EmpresaService.class);
        }
    }

    @Test
    void testCrearEmpresa() throws Exception {
		EmpresaDTO empresaDTO = EmpresaDTO.builder()
		.id(1L)
		
		.name("test").build();

        when(empresaService.crearEmpresa(any(EmpresaDTO.class))).thenReturn(empresaDTO);

        mockMvc.perform(post("/api/empresas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(empresaDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testObtenerEmpresaPorId() throws Exception {

		EmpresaDTO empresaDTO = EmpresaDTO.builder()
		.id(1L)
		
		.name("test").build();

        when(empresaService.obtenerEmpresaPorId(1L)).thenReturn(Optional.of(empresaDTO));

        mockMvc.perform(get("/api/empresas/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testActualizarEmpresa() throws Exception {
		EmpresaDTO empresaDTO = EmpresaDTO.builder()
		.id(1L)
		
		.name("test").build();

        when(empresaService.actualizarEmpresa(eq(1L), any(EmpresaDTO.class))).thenReturn(Optional.of(empresaDTO));

        mockMvc.perform(put("/api/empresas/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(empresaDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testEliminarEmpresa() throws Exception {
        mockMvc.perform(delete("/api/empresas/1"))
            .andExpect(status().isNoContent());
    }

    @Test
    void testObtenerTodosEmpresa() throws Exception {
        List<EmpresaDTO> empresaDTOs = List.of(
            EmpresaDTO.builder()
		.id(1L)
		
		.name("test").build(),
         EmpresaDTO.builder()
		.id(2L)
		
		.name("test").build()
        );
		
		// Datos de prueba para la página
		PageRequest pageable = PageRequest.of(0, 10);
        Page<EmpresaDTO> empresaDTOPage = new PageImpl<>(empresaDTOs, pageable, empresaDTOs.size());

		Map<String, Object> filtrosEsperados = new HashMap<>();
		filtrosEsperados.put("page", "0");
		filtrosEsperados.put("size", "10");

        when(empresaService.obtenerTodosEmpresa(pageable, filtrosEsperados))
		.thenReturn(empresaDTOPage);

        mockMvc.perform(get("/api/empresas")
		        .param("page", "0")
                .param("size", "10"))
            .andExpect(status().isOk())
			.andExpect(jsonPath("$.content").exists())
            .andExpect(jsonPath("$.content").isArray())
            .andExpect(jsonPath("$.content[0].id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }
	
	@Test
     void testObtenerTodosMinEmpresa() throws Exception {
         List<EmpresaDTO> empresaDTOs = List.of(
             EmpresaDTO.builder()
 		.id(1L)
 		
 		.name("test").build(),
          EmpresaDTO.builder()
 		.id(2L)
 		
 		.name("test").build()
         );
 
         when(empresaService.obtenerTodosMinEmpresa()).thenReturn(empresaDTOs);
 
         mockMvc.perform(get("/api/empresas/todosMinimo"))
             .andExpect(status().isOk())
             .andExpect(jsonPath("$").isArray())
             .andExpect(jsonPath("$[0].id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
     }
	
}