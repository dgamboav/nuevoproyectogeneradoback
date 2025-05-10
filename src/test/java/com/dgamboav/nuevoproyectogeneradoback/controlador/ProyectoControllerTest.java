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

import com.dgamboav.nuevoproyectogeneradoback.dto.ProyectoDTO;
import com.dgamboav.nuevoproyectogeneradoback.servicio.ProyectoService;

@SpringBootTest
@AutoConfigureMockMvc
class ProyectoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Qualifier("mockProyectoService")
    private ProyectoService proyectoService;

	
	@org.springframework.boot.test.context.TestConfiguration
    static class TestConfig {
        @Bean("mockProyectoService")
        @Primary
        public ProyectoService proyectoService() {
            return Mockito.mock(ProyectoService.class);
        }
    }

    @Test
    void testMostrarFormularioCrear() throws Exception {
        mockMvc.perform(get("/proyecto/crear"))
                .andExpect(status().isOk())
                .andExpect(view().name("proyecto/crear"))
                .andExpect(model().attributeExists("proyecto"));
    }

    @Test
    void testCrearProyecto() throws Exception {
        ProyectoDTO proyectoDTO = ProyectoDTO.builder().clienteId(1L).empleadoId(1L).fechaCreacion(null).costo(1.0).build();

        mockMvc.perform(post("/proyecto/crear")
                .flashAttr("proyecto", proyectoDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/proyecto"));

        verify(proyectoService, times(1)).crearProyecto(ArgumentMatchers.any(ProyectoDTO.class));
    }

    @Test
    void testMostrarProyectoExistente() throws Exception {
        Long id = 1L;
        ProyectoDTO proyectoDTO = ProyectoDTO.builder().id(id).build();

        when(proyectoService.obtenerProyectoPorId(id)).thenReturn(Optional.of(proyectoDTO));

        mockMvc.perform(get("/proyecto/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("proyecto/ver"))
                .andExpect(model().attributeExists("proyecto"))
                .andExpect(model().attribute("proyecto", hasProperty("id", is(1L))));
    }

    @Test
    void testMostrarProyectoNoExistente() throws Exception {
        Long id = 1L;
        when(proyectoService.obtenerProyectoPorId(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/proyecto/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andExpect(model().attributeExists("mensajeError"))
                .andExpect(model().attribute("mensajeError", "Proyecto no encontrado"));
    }

    @Test
    void testListarProyectos() throws Exception {
        List<ProyectoDTO> proyectos = Collections.singletonList(
                ProyectoDTO.builder().id(1L).build()
        );
		
		// Datos de prueba para la página
		PageRequest pageable = PageRequest.of(0, 10);
        Page<ProyectoDTO> proyectoDTOPage = new PageImpl<>(proyectos, pageable, proyectos.size());

		Map<String, Object> filtrosEsperados = new HashMap<>();
		filtrosEsperados.put("page", "0");
		filtrosEsperados.put("size", "10");

        when(proyectoService.obtenerTodosProyecto(pageable, filtrosEsperados)).thenReturn(proyectoDTOPage);

        mockMvc.perform(get("/proyecto")
				.param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(view().name("proyecto/lista"))
                .andExpect(model().attributeExists("proyectos"))
                .andExpect(model().attribute("proyectos", hasSize(1)));
    }

    @Test
    void testMostrarFormularioEditarExistente() throws Exception {
        Long id = 1L;
        ProyectoDTO proyectoDTO = ProyectoDTO.builder().id(id).build();
        when(proyectoService.obtenerProyectoPorId(id)).thenReturn(Optional.of(proyectoDTO));

        mockMvc.perform(get("/proyecto/editar/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("proyecto/editar"))
                .andExpect(model().attributeExists("proyecto"));
    }

    @Test
    void testActualizarProyecto() throws Exception {
        Long id = 1L;
        ProyectoDTO proyectoDTO = ProyectoDTO.builder().id(id).build();

        mockMvc.perform(post("/proyecto/editar/{id}", id)
                .flashAttr("proyecto", proyectoDTO)
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/proyecto"));

        verify(proyectoService, times(1)).actualizarProyecto(eq(id), ArgumentMatchers.any(ProyectoDTO.class));
    }

    @Test
    void testEliminarProyecto() throws Exception {
        Long id = 1L;

        mockMvc.perform(get("/proyecto/eliminar/{id}", id))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/proyecto"));

        verify(proyectoService, times(1)).eliminarProyecto(id);
    }
}