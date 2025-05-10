package com.dgamboav.nuevoproyectogeneradoback.controlador;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.PageImpl;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import com.dgamboav.nuevoproyectogeneradoback.dto.ObjecionDTO;
import com.dgamboav.nuevoproyectogeneradoback.servicio.ObjecionService;

@SpringBootTest
@AutoConfigureMockMvc
class ObjecionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Qualifier("mockObjecionService")
    private ObjecionService objecionService;

	
	@org.springframework.boot.test.context.TestConfiguration
    static class TestConfig {
        @Bean("mockObjecionService")
        @Primary
        public ObjecionService objecionService() {
            return Mockito.mock(ObjecionService.class);
        }
    }

    @Test
    void testMostrarFormularioCrear() throws Exception {
        mockMvc.perform(get("/objecion/crear"))
                .andExpect(status().isOk())
                .andExpect(view().name("objecion/crear"))
                .andExpect(model().attributeExists("objecion"));
    }

    @Test
    void testCrearObjecion() throws Exception {
        ObjecionDTO objecionDTO = ObjecionDTO.builder().fechaCreacion(null).periodoConstitucional("test").periodoLegislativo("test").numeroProyecto(1).titulo("test").prohija("test").fechaDeLaNota(null).alcance("test").motivo("test").informeDeGobierno("test").informeDeTrabajo("test").segundoDebate("test").tercerDebate("test").insistencia("test").pdfNroLey("test").publicado(null).gaceta("test").comentario("test").build();

        mockMvc.perform(post("/objecion/crear")
                .flashAttr("objecion", objecionDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/objecion"));

        verify(objecionService, times(1)).crearObjecion(ArgumentMatchers.any(ObjecionDTO.class));
    }

    @Test
    void testMostrarObjecionExistente() throws Exception {
        Long id = 1L;
        ObjecionDTO objecionDTO = ObjecionDTO.builder().id(id).periodoConstitucional("test").periodoLegislativo("test").titulo("test").prohija("test").alcance("test").motivo("test").informeDeGobierno("test").informeDeTrabajo("test").segundoDebate("test").tercerDebate("test").insistencia("test").pdfNroLey("test").gaceta("test").comentario("test").build();

        when(objecionService.obtenerObjecionPorId(id)).thenReturn(Optional.of(objecionDTO));

        mockMvc.perform(get("/objecion/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("objecion/ver"))
                .andExpect(model().attributeExists("objecion"))
                .andExpect(model().attribute("objecion", hasProperty("id", is(1L))));
    }

    @Test
    void testMostrarObjecionNoExistente() throws Exception {
        Long id = 1L;
        when(objecionService.obtenerObjecionPorId(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/objecion/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andExpect(model().attributeExists("mensajeError"))
                .andExpect(model().attribute("mensajeError", "Objecion no encontrado"));
    }

    @Test
    void testListarObjecions() throws Exception {
        List<ObjecionDTO> objecions = Collections.singletonList(
                ObjecionDTO.builder().id(1L).periodoConstitucional("test").periodoLegislativo("test").titulo("test").prohija("test").alcance("test").motivo("test").informeDeGobierno("test").informeDeTrabajo("test").segundoDebate("test").tercerDebate("test").insistencia("test").pdfNroLey("test").gaceta("test").comentario("test").build()
        );
		
		// Datos de prueba para la página
		PageRequest pageable = PageRequest.of(0, 10);
        Page<ObjecionDTO> objecionDTOPage = new PageImpl<>(objecions, pageable, objecions.size());

		Map<String, Object> filtrosEsperados = new HashMap<>();
		filtrosEsperados.put("page", "0");
		filtrosEsperados.put("size", "10");

        when(objecionService.obtenerTodosObjecion(pageable, filtrosEsperados)).thenReturn(objecionDTOPage);

        mockMvc.perform(get("/objecion")
				.param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(view().name("objecion/lista"))
                .andExpect(model().attributeExists("objecions"))
                .andExpect(model().attribute("objecions", hasSize(1)));
    }

    @Test
    void testMostrarFormularioEditarExistente() throws Exception {
        Long id = 1L;
        ObjecionDTO objecionDTO = ObjecionDTO.builder().id(id).periodoConstitucional("test").periodoLegislativo("test").titulo("test").prohija("test").alcance("test").motivo("test").informeDeGobierno("test").informeDeTrabajo("test").segundoDebate("test").tercerDebate("test").insistencia("test").pdfNroLey("test").gaceta("test").comentario("test").build();
        when(objecionService.obtenerObjecionPorId(id)).thenReturn(Optional.of(objecionDTO));

        mockMvc.perform(get("/objecion/editar/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("objecion/editar"))
                .andExpect(model().attributeExists("objecion"));
    }

    @Test
    void testActualizarObjecion() throws Exception {
        Long id = 1L;
        ObjecionDTO objecionDTO = ObjecionDTO.builder().id(id).periodoConstitucional("updated").periodoLegislativo("updated").titulo("updated").prohija("updated").alcance("updated").motivo("updated").informeDeGobierno("updated").informeDeTrabajo("updated").segundoDebate("updated").tercerDebate("updated").insistencia("updated").pdfNroLey("updated").gaceta("updated").comentario("updated").build();

        mockMvc.perform(post("/objecion/editar/{id}", id)
                .flashAttr("objecion", objecionDTO)
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/objecion"));

        verify(objecionService, times(1)).actualizarObjecion(eq(id), ArgumentMatchers.any(ObjecionDTO.class));
    }

    @Test
    void testEliminarObjecion() throws Exception {
        Long id = 1L;

        mockMvc.perform(get("/objecion/eliminar/{id}", id))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/objecion"));

        verify(objecionService, times(1)).eliminarObjecion(id);
    }
}