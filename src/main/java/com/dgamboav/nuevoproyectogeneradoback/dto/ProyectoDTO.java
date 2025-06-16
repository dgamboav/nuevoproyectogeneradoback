package com.dgamboav.nuevoproyectogeneradoback.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;


import java.time.*;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.dgamboav.nuevoproyectogeneradoback.entidad.Proyecto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProyectoDTO {

	private Long id;	
	@NotBlank(message = "El campo Cliente es obligatorio")
	private Long clienteId;	
	@NotBlank(message = "El campo Empleado es obligatorio")
	private Long empleadoId;	
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime fechaCreacion;	
	@JsonFormat(pattern = "0,0.00 $")
	@Size(min = 0, message = "El costo debe tener minimo 0 caracteres")
	private Double costo;	

    public static ProyectoDTO toDTO(Proyecto proyecto) {
        return ProyectoDTO.builder()
            .id(proyecto.getId())
            .clienteId(proyecto.getClienteId())
            .empleadoId(proyecto.getEmpleadoId())
            .fechaCreacion(proyecto.getFechaCreacion())
            .costo(proyecto.getCosto())
            .build();
    }
	
	public static ProyectoDTO toDTOMinimo(Proyecto proyecto) {
        return ProyectoDTO.builder()
            .id(proyecto.getId())
            .clienteId(proyecto.getClienteId())
            .empleadoId(proyecto.getEmpleadoId())
            .build();
    }

    public static Proyecto toDomain(ProyectoDTO proyectoDTO) {
        return Proyecto.builder()
            .id(proyectoDTO.getId())
            .clienteId(proyectoDTO.getClienteId())
            .empleadoId(proyectoDTO.getEmpleadoId())
            .fechaCreacion(proyectoDTO.getFechaCreacion())
            .costo(proyectoDTO.getCosto())
            .build();
    }
	
	public static Proyecto copyProperties(Proyecto source, Proyecto target){
	    target.setClienteId(source.getClienteId());target.setEmpleadoId(source.getEmpleadoId());target.setFechaCreacion(source.getFechaCreacion());target.setCosto(source.getCosto());
		return target;
	}
}