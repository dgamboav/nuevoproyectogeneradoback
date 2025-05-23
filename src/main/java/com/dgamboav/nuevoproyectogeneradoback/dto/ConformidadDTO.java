package com.dgamboav.nuevoproyectogeneradoback.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;


import java.time.*;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.dgamboav.nuevoproyectogeneradoback.entidad.Conformidad;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConformidadDTO {

	private Long id;	
	@NotBlank(message = "El campo descripcion es obligatorio")
	private String descripcion;	
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotBlank(message = "El campo fechaDeteccion es obligatorio")
	private LocalDate fechaDeteccion;	
	@NotBlank(message = "El campo Responsable es obligatorio")
	private Long responsableId;	
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;	
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updatedAt;	
	@NotBlank(message = "El campo Auditoría es obligatorio")
	private Long auditoriaId;	
	@NotBlank(message = "El campo Proceso es obligatorio")
	private Long procesoId;	

    public static ConformidadDTO toDTO(Conformidad conformidad) {
        return ConformidadDTO.builder()
            .id(conformidad.getId())
            .descripcion(conformidad.getDescripcion())
            .fechaDeteccion(conformidad.getFechaDeteccion())
            .responsableId(conformidad.getResponsableId())
            .createdAt(conformidad.getCreatedAt())
            .updatedAt(conformidad.getUpdatedAt())
            .auditoriaId(conformidad.getAuditoriaId())
            .procesoId(conformidad.getProcesoId())
            .build();
    }
	
	public static ConformidadDTO toDTOMinimo(Conformidad conformidad) {
        return ConformidadDTO.builder()
            .id(conformidad.getId())
            .build();
    }

    public static Conformidad toDomain(ConformidadDTO conformidadDTO) {
        return Conformidad.builder()
            .id(conformidadDTO.getId())
            .descripcion(conformidadDTO.getDescripcion())
            .fechaDeteccion(conformidadDTO.getFechaDeteccion())
            .responsableId(conformidadDTO.getResponsableId())
            .createdAt(conformidadDTO.getCreatedAt())
            .updatedAt(conformidadDTO.getUpdatedAt())
            .auditoriaId(conformidadDTO.getAuditoriaId())
            .procesoId(conformidadDTO.getProcesoId())
            .build();
    }
}