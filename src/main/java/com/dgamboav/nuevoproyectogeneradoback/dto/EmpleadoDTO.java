package com.dgamboav.nuevoproyectogeneradoback.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;


import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.dgamboav.nuevoproyectogeneradoback.entidad.Empleado;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpleadoDTO {

	private Long id;	
	@NotBlank(message = "El campo nombre es obligatorio")
	private String nombre;	
	@NotBlank(message = "El campo departamento es obligatorio")
	private String departamento;	
	@JsonFormat(pattern = "0,0.00 $")
	@Size(min = 0, max = 10, message = "El salario debe tener entre  0 y 10 caracteres")
	private Double salario;	

    public static EmpleadoDTO toDTO(Empleado empleado) {
        return EmpleadoDTO.builder()
            .id(empleado.getId())
            .nombre(empleado.getNombre())
            .departamento(empleado.getDepartamento())
            .salario(empleado.getSalario())
            .build();
    }
	
	public static EmpleadoDTO toDTOMinimo(Empleado empleado) {
        return EmpleadoDTO.builder()
            .id(empleado.getId())
            .nombre(empleado.getNombre())
            .build();
    }

    public static Empleado toDomain(EmpleadoDTO empleadoDTO) {
        return Empleado.builder()
            .id(empleadoDTO.getId())
            .nombre(empleadoDTO.getNombre())
            .departamento(empleadoDTO.getDepartamento())
            .salario(empleadoDTO.getSalario())
            .build();
    }
	
	public static Empleado copyProperties(Empleado source, Empleado target){
	    target.setNombre(source.getNombre());target.setDepartamento(source.getDepartamento());target.setSalario(source.getSalario());
		return target;
	}
}