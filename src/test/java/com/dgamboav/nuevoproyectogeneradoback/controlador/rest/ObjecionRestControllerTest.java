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

import com.dgamboav.nuevoproyectogeneradoback.dto.ObjecionDTO;
import com.dgamboav.nuevoproyectogeneradoback.servicio.ObjecionService;

@SpringBootTest
@AutoConfigureMockMvc
class ObjecionRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Qualifier("mockObjecionService")
    private ObjecionService objecionService;

    @Autowired
    private ObjectMapper objectMapper;
	
	@org.springframework.boot.test.context.TestConfiguration
    static class TestConfig {
        @Bean("mockObjecionService")
        @Primary
        public ObjecionService objecionService() {
            return Mockito.mock(ObjecionService.class);
        }
    }

    @Test
    void testCrearObjecion() throws Exception {
		ObjecionDTO objecionDTO = ObjecionDTO.builder()
		.id(1L)
		
		 .fechaCreacion(null)
		.periodoConstitucional("test")
		.periodoLegislativo("test")
		.numeroProyecto(1)
		.titulo("test")
		.prohija("test")
		 .fechaDeLaNota(null)
		.alcance("test")
		.motivo("test")
		.informeDeGobierno("test")
		.informeDeTrabajo("test")
		.segundoDebate("test")
		.tercerDebate("test")
		.insistencia("test")
		.pdfNroLey("test")
		 .publicado(null)
		.gaceta("test")
		.comentario("test").build();

        when(objecionService.crearObjecion(any(ObjecionDTO.class))).thenReturn(objecionDTO);

        mockMvc.perform(post("/api/objecions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(objecionDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testObtenerObjecionPorId() throws Exception {

		ObjecionDTO objecionDTO = ObjecionDTO.builder()
		.id(1L)
		
		 .fechaCreacion(null)
		.periodoConstitucional("test")
		.periodoLegislativo("test")
		.numeroProyecto(1)
		
		.titulo("test")
		.prohija("test")
		 .fechaDeLaNota(null)
		.alcance("test")
		.motivo("test")
		.informeDeGobierno("test")
		.informeDeTrabajo("test")
		.segundoDebate("test")
		.tercerDebate("test")
		.insistencia("test")
		.pdfNroLey("test")
		 .publicado(null)
		.gaceta("test")
		.comentario("test").build();

        when(objecionService.obtenerObjecionPorId(1L)).thenReturn(Optional.of(objecionDTO));

        mockMvc.perform(get("/api/objecions/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testActualizarObjecion() throws Exception {
		ObjecionDTO objecionDTO = ObjecionDTO.builder()
		.id(1L)
		
		 .fechaCreacion(null)
		.periodoConstitucional("test")
		.periodoLegislativo("test")
		.numeroProyecto(1)
		
		.titulo("test")
		.prohija("test")
		 .fechaDeLaNota(null)
		.alcance("test")
		.motivo("test")
		.informeDeGobierno("test")
		.informeDeTrabajo("test")
		.segundoDebate("test")
		.tercerDebate("test")
		.insistencia("test")
		.pdfNroLey("test")
		 .publicado(null)
		.gaceta("test")
		.comentario("test").build();

        when(objecionService.actualizarObjecion(eq(1L), any(ObjecionDTO.class))).thenReturn(Optional.of(objecionDTO));

        mockMvc.perform(put("/api/objecions/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(objecionDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testEliminarObjecion() throws Exception {
        mockMvc.perform(delete("/api/objecions/1"))
            .andExpect(status().isNoContent());
    }

    @Test
    void testObtenerTodosObjecion() throws Exception {
        List<ObjecionDTO> objecionDTOs = List.of(
            ObjecionDTO.builder()
		.id(1L)
		
		 .fechaCreacion(null)
		.periodoConstitucional("test")
		.periodoLegislativo("test")
		.numeroProyecto(1)
		
		.titulo("test")
		.prohija("test")
		 .fechaDeLaNota(null)
		.alcance("test")
		.motivo("test")
		.informeDeGobierno("test")
		.informeDeTrabajo("test")
		.segundoDebate("test")
		.tercerDebate("test")
		.insistencia("test")
		.pdfNroLey("test")
		 .publicado(null)
		.gaceta("test")
		.comentario("test").build(),
         ObjecionDTO.builder()
		.id(2L)
		
		 .fechaCreacion(null)
		.periodoConstitucional("test")
		.periodoLegislativo("test")
		.numeroProyecto(1)
		
		.titulo("test")
		.prohija("test")
		 .fechaDeLaNota(null)
		.alcance("test")
		.motivo("test")
		.informeDeGobierno("test")
		.informeDeTrabajo("test")
		.segundoDebate("test")
		.tercerDebate("test")
		.insistencia("test")
		.pdfNroLey("test")
		 .publicado(null)
		.gaceta("test")
		.comentario("test").build()
        );
		
		// Datos de prueba para la página
		PageRequest pageable = PageRequest.of(0, 10);
        Page<ObjecionDTO> objecionDTOPage = new PageImpl<>(objecionDTOs, pageable, objecionDTOs.size());

		Map<String, Object> filtrosEsperados = new HashMap<>();
		filtrosEsperados.put("page", "0");
		filtrosEsperados.put("size", "10");

        when(objecionService.obtenerTodosObjecion(pageable, filtrosEsperados))
		.thenReturn(objecionDTOPage);

        mockMvc.perform(get("/api/objecions")
		        .param("page", "0")
                .param("size", "10"))
            .andExpect(status().isOk())
			.andExpect(jsonPath("$.content").exists())
            .andExpect(jsonPath("$.content").isArray())
            .andExpect(jsonPath("$.content[0].id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }
	
	@Test
     void testObtenerTodosMinObjecion() throws Exception {
         List<ObjecionDTO> objecionDTOs = List.of(
             ObjecionDTO.builder()
 		.id(1L)
 		
 		 .fechaCreacion(null)
 		.periodoConstitucional("test")
 		.periodoLegislativo("test")
 		.numeroProyecto(1)
 		
 		.titulo("test")
 		.prohija("test")
 		 .fechaDeLaNota(null)
 		.alcance("test")
 		.motivo("test")
 		.informeDeGobierno("test")
 		.informeDeTrabajo("test")
 		.segundoDebate("test")
 		.tercerDebate("test")
 		.insistencia("test")
 		.pdfNroLey("test")
 		 .publicado(null)
 		.gaceta("test")
 		.comentario("test").build(),
          ObjecionDTO.builder()
 		.id(2L)
 		
 		 .fechaCreacion(null)
 		.periodoConstitucional("test")
 		.periodoLegislativo("test")
 		.numeroProyecto(1)
 		
 		.titulo("test")
 		.prohija("test")
 		 .fechaDeLaNota(null)
 		.alcance("test")
 		.motivo("test")
 		.informeDeGobierno("test")
 		.informeDeTrabajo("test")
 		.segundoDebate("test")
 		.tercerDebate("test")
 		.insistencia("test")
 		.pdfNroLey("test")
 		 .publicado(null)
 		.gaceta("test")
 		.comentario("test").build()
         );
 
         when(objecionService.obtenerTodosMinObjecion()).thenReturn(objecionDTOs);
 
         mockMvc.perform(get("/api/objecions/todosMinimo"))
             .andExpect(status().isOk())
             .andExpect(jsonPath("$").isArray())
             .andExpect(jsonPath("$[0].id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
     }
	
}