package com.dgamboav.nuevoproyectogeneradoback.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;


import java.time.*;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.dgamboav.nuevoproyectogeneradoback.entidad.ProcesoAuditoria;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcesoAuditoriaDTO {

	private Long id;	
	@NotBlank(message = "El campo Auditoría es obligatorio")
	private Long auditoriaId;	
	@NotBlank(message = "El campo Proceso es obligatorio")
	private Long procesoId;	
	private String resultado;	
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;	
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updatedAt;	

    public static ProcesoAuditoriaDTO toDTO(ProcesoAuditoria procesoAuditoria) {
        return ProcesoAuditoriaDTO.builder()
            .id(procesoAuditoria.getId())
            .auditoriaId(procesoAuditoria.getAuditoriaId())
            .procesoId(procesoAuditoria.getProcesoId())
            .resultado(procesoAuditoria.getResultado())
            .createdAt(procesoAuditoria.getCreatedAt())
            .updatedAt(procesoAuditoria.getUpdatedAt())
            .build();
    }
	
	public static ProcesoAuditoriaDTO toDTOMinimo(ProcesoAuditoria procesoAuditoria) {
        return ProcesoAuditoriaDTO.builder()
            .id(procesoAuditoria.getId())
            .build();
    }

    public static ProcesoAuditoria toDomain(ProcesoAuditoriaDTO procesoAuditoriaDTO) {
        return ProcesoAuditoria.builder()
            .id(procesoAuditoriaDTO.getId())
            .auditoriaId(procesoAuditoriaDTO.getAuditoriaId())
            .procesoId(procesoAuditoriaDTO.getProcesoId())
            .resultado(procesoAuditoriaDTO.getResultado())
            .createdAt(procesoAuditoriaDTO.getCreatedAt())
            .updatedAt(procesoAuditoriaDTO.getUpdatedAt())
            .build();
    }
}