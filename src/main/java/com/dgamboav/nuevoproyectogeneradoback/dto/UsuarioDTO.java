package com.dgamboav.nuevoproyectogeneradoback.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.dgamboav.nuevoproyectogeneradoback.entidad.Usuario;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {

	private Long id;	
	@NotBlank(message = "El campo nombre es obligatorio")
	private String nombre;	
	@NotBlank(message = "El campo correo es obligatorio")
	@Email(  regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", 
	message = "El email debe ser válido")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
	@Size(max = 255, message = "El correo debe tener máximo 255 caracteres")
	private String correo;	
	@NotBlank(message = "El campo Contraseña es obligatorio")
	private String contrasena;	

    public static UsuarioDTO toDTO(Usuario usuario) {
        return UsuarioDTO.builder()
            .id(usuario.getId())
            .nombre(usuario.getNombre())
            .correo(usuario.getCorreo())
            .contrasena(usuario.getContrasena())
            .build();
    }
	
	public static UsuarioDTO toDTOMinimo(Usuario usuario) {
        return UsuarioDTO.builder()
            .id(usuario.getId())
            .nombre(usuario.getNombre())
            .build();
    }

    public static Usuario toDomain(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
            .id(usuarioDTO.getId())
            .nombre(usuarioDTO.getNombre())
            .correo(usuarioDTO.getCorreo())
			.contrasena(new BCryptPasswordEncoder(8).encode(usuarioDTO.getContrasena()))
            .build();
    }
}