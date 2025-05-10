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

import com.dgamboav.nuevoproyectogeneradoback.dto.UsuarioDTO;
import com.dgamboav.nuevoproyectogeneradoback.servicio.UsuarioService;

@SpringBootTest
@AutoConfigureMockMvc
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Qualifier("mockUsuarioService")
    private UsuarioService usuarioService;

	
	@org.springframework.boot.test.context.TestConfiguration
    static class TestConfig {
        @Bean("mockUsuarioService")
        @Primary
        public UsuarioService usuarioService() {
            return Mockito.mock(UsuarioService.class);
        }
    }

    @Test
    void testMostrarFormularioCrear() throws Exception {
        mockMvc.perform(get("/usuario/crear"))
                .andExpect(status().isOk())
                .andExpect(view().name("usuario/crear"))
                .andExpect(model().attributeExists("usuario"));
    }

    @Test
    void testCrearUsuario() throws Exception {
        UsuarioDTO usuarioDTO = UsuarioDTO.builder().nombre("test").correo("test").contrasena("test").build();

        mockMvc.perform(post("/usuario/crear")
                .flashAttr("usuario", usuarioDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/usuario"));

        verify(usuarioService, times(1)).crearUsuario(ArgumentMatchers.any(UsuarioDTO.class));
    }

    @Test
    void testMostrarUsuarioExistente() throws Exception {
        Long id = 1L;
        UsuarioDTO usuarioDTO = UsuarioDTO.builder().id(id).nombre("test").correo("test").contrasena("test").build();

        when(usuarioService.obtenerUsuarioPorId(id)).thenReturn(Optional.of(usuarioDTO));

        mockMvc.perform(get("/usuario/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("usuario/ver"))
                .andExpect(model().attributeExists("usuario"))
                .andExpect(model().attribute("usuario", hasProperty("id", is(1L))));
    }

    @Test
    void testMostrarUsuarioNoExistente() throws Exception {
        Long id = 1L;
        when(usuarioService.obtenerUsuarioPorId(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/usuario/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andExpect(model().attributeExists("mensajeError"))
                .andExpect(model().attribute("mensajeError", "Usuario no encontrado"));
    }

    @Test
    void testListarUsuarios() throws Exception {
        List<UsuarioDTO> usuarios = Collections.singletonList(
                UsuarioDTO.builder().id(1L).nombre("test").correo("test").contrasena("test").build()
        );
		
		// Datos de prueba para la página
		PageRequest pageable = PageRequest.of(0, 10);
        Page<UsuarioDTO> usuarioDTOPage = new PageImpl<>(usuarios, pageable, usuarios.size());

		Map<String, Object> filtrosEsperados = new HashMap<>();
		filtrosEsperados.put("page", "0");
		filtrosEsperados.put("size", "10");

        when(usuarioService.obtenerTodosUsuario(pageable, filtrosEsperados)).thenReturn(usuarioDTOPage);

        mockMvc.perform(get("/usuario")
				.param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(view().name("usuario/lista"))
                .andExpect(model().attributeExists("usuarios"))
                .andExpect(model().attribute("usuarios", hasSize(1)));
    }

    @Test
    void testMostrarFormularioEditarExistente() throws Exception {
        Long id = 1L;
        UsuarioDTO usuarioDTO = UsuarioDTO.builder().id(id).nombre("test").correo("test").contrasena("test").build();
        when(usuarioService.obtenerUsuarioPorId(id)).thenReturn(Optional.of(usuarioDTO));

        mockMvc.perform(get("/usuario/editar/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("usuario/editar"))
                .andExpect(model().attributeExists("usuario"));
    }

    @Test
    void testActualizarUsuario() throws Exception {
        Long id = 1L;
        UsuarioDTO usuarioDTO = UsuarioDTO.builder().id(id).nombre("updated").correo("updated").contrasena("updated").build();

        mockMvc.perform(post("/usuario/editar/{id}", id)
                .flashAttr("usuario", usuarioDTO)
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/usuario"));

        verify(usuarioService, times(1)).actualizarUsuario(eq(id), ArgumentMatchers.any(UsuarioDTO.class));
    }

    @Test
    void testEliminarUsuario() throws Exception {
        Long id = 1L;

        mockMvc.perform(get("/usuario/eliminar/{id}", id))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/usuario"));

        verify(usuarioService, times(1)).eliminarUsuario(id);
    }
}