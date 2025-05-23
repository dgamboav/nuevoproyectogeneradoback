package com.dgamboav.nuevoproyectogeneradoback.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;


import jakarta.validation.constraints.NotBlank;
import com.dgamboav.nuevoproyectogeneradoback.entidad.Empresa;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpresaDTO {

	private Long id;	
	@NotBlank(message = "El campo name es obligatorio")
	private String name;	

    public static EmpresaDTO toDTO(Empresa empresa) {
        return EmpresaDTO.builder()
            .id(empresa.getId())
            .name(empresa.getName())
            .build();
    }
	
	public static EmpresaDTO toDTOMinimo(Empresa empresa) {
        return EmpresaDTO.builder()
            .id(empresa.getId())
            .build();
    }

    public static Empresa toDomain(EmpresaDTO empresaDTO) {
        return Empresa.builder()
            .id(empresaDTO.getId())
            .name(empresaDTO.getName())
            .build();
    }
}