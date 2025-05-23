package com.dgamboav.nuevoproyectogeneradoback.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;


import java.time.*;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.dgamboav.nuevoproyectogeneradoback.entidad.Proceso;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcesoDTO {

	private Long id;	
	@NotBlank(message = "El campo nombre es obligatorio")
	private String nombre;	
	private String descripcion;	
	@NotBlank(message = "El campo codigoProceso es obligatorio")
	private String codigoProceso;	
	@NotBlank(message = "El campo Responsable del Proceso es obligatorio")
	private Long responsableId;	
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;	
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updatedAt;	
	@NotBlank(message = "El campo Empresa es obligatorio")
	private Long empresaId;	

    public static ProcesoDTO toDTO(Proceso proceso) {
        return ProcesoDTO.builder()
            .id(proceso.getId())
            .nombre(proceso.getNombre())
            .descripcion(proceso.getDescripcion())
            .codigoProceso(proceso.getCodigoProceso())
            .responsableId(proceso.getResponsableId())
            .createdAt(proceso.getCreatedAt())
            .updatedAt(proceso.getUpdatedAt())
            .empresaId(proceso.getEmpresaId())
            .build();
    }
	
	public static ProcesoDTO toDTOMinimo(Proceso proceso) {
        return ProcesoDTO.builder()
            .id(proceso.getId())
            .build();
    }

    public static Proceso toDomain(ProcesoDTO procesoDTO) {
        return Proceso.builder()
            .id(procesoDTO.getId())
            .nombre(procesoDTO.getNombre())
            .descripcion(procesoDTO.getDescripcion())
            .codigoProceso(procesoDTO.getCodigoProceso())
            .responsableId(procesoDTO.getResponsableId())
            .createdAt(procesoDTO.getCreatedAt())
            .updatedAt(procesoDTO.getUpdatedAt())
            .empresaId(procesoDTO.getEmpresaId())
            .build();
    }
}