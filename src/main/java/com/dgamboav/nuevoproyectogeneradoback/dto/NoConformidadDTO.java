package com.dgamboav.nuevoproyectogeneradoback.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;


import java.time.*;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.dgamboav.nuevoproyectogeneradoback.entidad.NoConformidad;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoConformidadDTO {

	private Long id;	
	@NotBlank(message = "El campo descripcion es obligatorio")
	private String descripcion;	
	private String causaRaiz;	
	@NotBlank(message = "El campo clasificacion es obligatorio")
	private String clasificacion;	
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotBlank(message = "El campo fechaDeteccion es obligatorio")
	private LocalDate fechaDeteccion;	
	@NotBlank(message = "El campo Responsable es obligatorio")
	private Long responsableId;	
	@NotBlank(message = "El campo estado es obligatorio")
	private String estado;	
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

    public static NoConformidadDTO toDTO(NoConformidad noConformidad) {
        return NoConformidadDTO.builder()
            .id(noConformidad.getId())
            .descripcion(noConformidad.getDescripcion())
            .causaRaiz(noConformidad.getCausaRaiz())
            .clasificacion(noConformidad.getClasificacion())
            .fechaDeteccion(noConformidad.getFechaDeteccion())
            .responsableId(noConformidad.getResponsableId())
            .estado(noConformidad.getEstado())
            .createdAt(noConformidad.getCreatedAt())
            .updatedAt(noConformidad.getUpdatedAt())
            .auditoriaId(noConformidad.getAuditoriaId())
            .procesoId(noConformidad.getProcesoId())
            .build();
    }
	
	public static NoConformidadDTO toDTOMinimo(NoConformidad noConformidad) {
        return NoConformidadDTO.builder()
            .id(noConformidad.getId())
            .build();
    }

    public static NoConformidad toDomain(NoConformidadDTO noConformidadDTO) {
        return NoConformidad.builder()
            .id(noConformidadDTO.getId())
            .descripcion(noConformidadDTO.getDescripcion())
            .causaRaiz(noConformidadDTO.getCausaRaiz())
            .clasificacion(noConformidadDTO.getClasificacion())
            .fechaDeteccion(noConformidadDTO.getFechaDeteccion())
            .responsableId(noConformidadDTO.getResponsableId())
            .estado(noConformidadDTO.getEstado())
            .createdAt(noConformidadDTO.getCreatedAt())
            .updatedAt(noConformidadDTO.getUpdatedAt())
            .auditoriaId(noConformidadDTO.getAuditoriaId())
            .procesoId(noConformidadDTO.getProcesoId())
            .build();
    }
}