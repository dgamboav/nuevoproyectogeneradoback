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

import com.dgamboav.nuevoproyectogeneradoback.dto.AccionCorrectivaDTO;
import com.dgamboav.nuevoproyectogeneradoback.servicio.AccionCorrectivaService;
@SpringBootTest
@AutoConfigureMockMvc
class AccionCorrectivaRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Qualifier("mockAccionCorrectivaService")
    private AccionCorrectivaService accionCorrectivaService;

    @Autowired
    private ObjectMapper objectMapper;
	
	@org.springframework.boot.test.context.TestConfiguration
    static class TestConfig {
        @Bean("mockAccionCorrectivaService")
        @Primary
        public AccionCorrectivaService accionCorrectivaService() {
            return Mockito.mock(AccionCorrectivaService.class);
        }
    }

    @Test
    void testCrearAccionCorrectiva() throws Exception {
		AccionCorrectivaDTO accionCorrectivaDTO = AccionCorrectivaDTO.builder()
		.id(1L)
		
		.descripcion("test")
		 .fechaImplementacion(null)
		 .fechaVerificacion(null)
		.estado("test")
		.responsableId(1L)
		
		 .createdAt(null)
		 .updatedAt(null)
		.noConformidadId(1L)
		.build();

        when(accionCorrectivaService.crearAccionCorrectiva(any(AccionCorrectivaDTO.class))).thenReturn(accionCorrectivaDTO);

        mockMvc.perform(post("/api/accioncorrectivas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(accionCorrectivaDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testObtenerAccionCorrectivaPorId() throws Exception {

		AccionCorrectivaDTO accionCorrectivaDTO = AccionCorrectivaDTO.builder()
		.id(1L)
		
		.descripcion("test")
		 .fechaImplementacion(null)
		 .fechaVerificacion(null)
		.estado("test")
		.responsableId(1L)
		
		 .createdAt(null)
		 .updatedAt(null)
		.noConformidadId(1L)
		.build();

        when(accionCorrectivaService.obtenerAccionCorrectivaPorId(1L)).thenReturn(Optional.of(accionCorrectivaDTO));

        mockMvc.perform(get("/api/accioncorrectivas/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testActualizarAccionCorrectiva() throws Exception {
		AccionCorrectivaDTO accionCorrectivaDTO = AccionCorrectivaDTO.builder()
		.id(1L)
		
		.descripcion("test")
		 .fechaImplementacion(null)
		 .fechaVerificacion(null)
		.estado("test")
		.responsableId(1L)
		
		 .createdAt(null)
		 .updatedAt(null)
		.noConformidadId(1L)
		.build();

        when(accionCorrectivaService.actualizarAccionCorrectiva(eq(1L), any(AccionCorrectivaDTO.class))).thenReturn(Optional.of(accionCorrectivaDTO));

        mockMvc.perform(put("/api/accioncorrectivas/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(accionCorrectivaDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testEliminarAccionCorrectiva() throws Exception {
        mockMvc.perform(delete("/api/accioncorrectivas/1"))
            .andExpect(status().isNoContent());
    }

    @Test
    void testObtenerTodosAccionCorrectiva() throws Exception {
        List<AccionCorrectivaDTO> accionCorrectivaDTOs = List.of(
            AccionCorrectivaDTO.builder()
		.id(1L)
		
		.descripcion("test")
		 .fechaImplementacion(null)
		 .fechaVerificacion(null)
		.estado("test")
		.responsableId(1L)
		
		 .createdAt(null)
		 .updatedAt(null)
		.noConformidadId(1L)
		.build(),
         AccionCorrectivaDTO.builder()
		.id(2L)
		
		.descripcion("test")
		 .fechaImplementacion(null)
		 .fechaVerificacion(null)
		.estado("test")
		.responsableId(1L)
		
		 .createdAt(null)
		 .updatedAt(null)
		.noConformidadId(1L)
		.build()
        );
		
		// Datos de prueba para la página
		PageRequest pageable = PageRequest.of(0, 10);
        Page<AccionCorrectivaDTO> accionCorrectivaDTOPage = new PageImpl<>(accionCorrectivaDTOs, pageable, accionCorrectivaDTOs.size());

		Map<String, Object> filtrosEsperados = new HashMap<>();
		filtrosEsperados.put("page", "0");
		filtrosEsperados.put("size", "10");

        when(accionCorrectivaService.obtenerTodosAccionCorrectiva(pageable, filtrosEsperados))
		.thenReturn(accionCorrectivaDTOPage);

        mockMvc.perform(get("/api/accioncorrectivas")
		        .param("page", "0")
                .param("size", "10"))
            .andExpect(status().isOk())
			.andExpect(jsonPath("$.content").exists())
            .andExpect(jsonPath("$.content").isArray())
            .andExpect(jsonPath("$.content[0].id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }
	
	@Test
     void testObtenerTodosMinAccionCorrectiva() throws Exception {
         List<AccionCorrectivaDTO> accionCorrectivaDTOs = List.of(
             AccionCorrectivaDTO.builder()
 		.id(1L)
 		
 		.descripcion("test")
 		 .fechaImplementacion(null)
 		 .fechaVerificacion(null)
 		.estado("test")
 		.responsableId(1L)
 		
 		 .createdAt(null)
 		 .updatedAt(null)
 		.noConformidadId(1L)
 		.build(),
          AccionCorrectivaDTO.builder()
 		.id(2L)
 		
 		.descripcion("test")
 		 .fechaImplementacion(null)
 		 .fechaVerificacion(null)
 		.estado("test")
 		.responsableId(1L)
 		
 		 .createdAt(null)
 		 .updatedAt(null)
 		.noConformidadId(1L)
 		.build()
         );
 
         when(accionCorrectivaService.obtenerTodosMinAccionCorrectiva()).thenReturn(accionCorrectivaDTOs);
 
         mockMvc.perform(get("/api/accioncorrectivas/todosMinimo"))
             .andExpect(status().isOk())
             .andExpect(jsonPath("$").isArray())
             .andExpect(jsonPath("$[0].id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
     }
	
}