package com.dgamboav.nuevoproyectogeneradoback.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.dgamboav.nuevoproyectogeneradoback.entidad.Usuario;

class UsuarioDTOTest {

    @Test
    void testDTO() {
        UsuarioDTO dto = UsuarioDTO.builder()
            .id(1L)
            .nombre("test")
            .correo("test")
            .contrasena("test")
            .fechaCreacion(null) // Valor por defecto para otros tipos
            .build();

            assertEquals(1L, dto.getId());
            assertEquals("test", dto.getNombre());
            assertEquals("test", dto.getCorreo());
            assertEquals("test", dto.getContrasena());
            assertNull(dto.getFechaCreacion()); // Valor por defecto para otros tipos

        UsuarioDTO dto2 = new UsuarioDTO(
				1L
				, 				
				"test"
				, 				
				"test"
				, 				
				"test"
				, 				
				null
								
        );

        assertEquals(dto, dto2);
        assertEquals(dto.hashCode(), dto2.hashCode());

        String toString = dto.toString();
        assertTrue(toString.contains("UsuarioDTO"));
        assertTrue(toString.contains("id"));
        assertTrue(toString.contains("nombre"));
        assertTrue(toString.contains("correo"));
        assertTrue(toString.contains("contrasena"));
        assertTrue(toString.contains("fechaCreacion"));
    }

    @Test
    void testToDTO() {
        Usuario usuario = Usuario.builder()
				.id(1L)
				.nombre("test")
				.correo("test")
				.contrasena("test")
				.fechaCreacion(null) // Valor por defecto para otros tipos
            .build();

        UsuarioDTO dto = UsuarioDTO.toDTO(usuario);

			assertEquals(1L, dto.getId());
			assertEquals("test", dto.getNombre());
			assertEquals("test", dto.getCorreo());
			assertEquals("test", dto.getContrasena());
			assertNull(dto.getFechaCreacion()); // Valor por defecto para otros tipos
    }
	
	@Test
    void testToDTOMinimo() {
        Usuario usuario = Usuario.builder()
			
				.id(1L)
			
			
				.nombre("test")
			
            .build();

        UsuarioDTO dto = UsuarioDTO.toDTOMinimo(usuario);

		
			assertEquals(1L, dto.getId());
		
		
			assertEquals("test", dto.getNombre());
		
    }

    @Test
    void testToDomain() {
        UsuarioDTO dto = UsuarioDTO.builder()
				.id(1L)
				.nombre("test")				
				.correo("test")				
				.contrasena("test")				
				.fechaCreacion(null) // Valor por defecto para otros tipos
            .build();

        Usuario usuario = UsuarioDTO.toDomain(dto);
			assertEquals(1L, usuario.getId());
			
					assertEquals("test", usuario.getNombre());
			
			
					assertEquals("test", usuario.getCorreo());
			
			
					assertTrue(new BCryptPasswordEncoder(8).matches("test", usuario.getContrasena()));
			
			assertNull(usuario.getFechaCreacion()); // Valor por defecto para otros tipos
    }
	
	@Test
    void testCopyProperties_successfulCopy() {
	
	
        Usuario targetUsuario = Usuario.builder()
				.id(100L)
				.nombre("test")
				.correo("test")
				.contrasena("test")
				.fechaCreacion(null) // Valor por defecto para otros tipos
            .build();
			
		Usuario sourceUsuario = Usuario.builder()
				.id(1L)
				.nombre("test")
				.correo("test")
				.contrasena("test")
				.fechaCreacion(null) // Valor por defecto para otros tipos
            .build();

        Usuario result = UsuarioDTO.copyProperties(sourceUsuario, targetUsuario);

        assertEquals(sourceUsuario.getNombre(), result.getNombre());
        assertEquals(sourceUsuario.getCorreo(), result.getCorreo());
        assertEquals(sourceUsuario.getContrasena(), result.getContrasena());
        assertEquals(sourceUsuario.getFechaCreacion(), result.getFechaCreacion());

        assertEquals(100L, result.getId());
        assertSame(targetUsuario, result);
    }
	
	
}