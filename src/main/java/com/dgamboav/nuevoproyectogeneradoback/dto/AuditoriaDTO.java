package com.dgamboav.nuevoproyectogeneradoback.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;


import java.time.*;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.dgamboav.nuevoproyectogeneradoback.entidad.Auditoria;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuditoriaDTO {

	private Long id;	
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotBlank(message = "El campo fechaInicio es obligatorio")
	private LocalDate fechaInicio;	
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotBlank(message = "El campo fechaFin es obligatorio")
	private LocalDate fechaFin;	
	@NotBlank(message = "El campo tipoAuditoria es obligatorio")
	private String tipoAuditoria;	
	@NotBlank(message = "El campo Auditor Líder es obligatorio")
	private Long auditorLiderId;	
	@NotBlank(message = "El campo objetivo es obligatorio")
	private String objetivo;	
	@NotBlank(message = "El campo alcance es obligatorio")
	private String alcance;	
	@NotBlank(message = "El campo estado es obligatorio")
	private String estado;	
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;	
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updatedAt;	

    public static AuditoriaDTO toDTO(Auditoria auditoria) {
        return AuditoriaDTO.builder()
            .id(auditoria.getId())
            .fechaInicio(auditoria.getFechaInicio())
            .fechaFin(auditoria.getFechaFin())
            .tipoAuditoria(auditoria.getTipoAuditoria())
            .auditorLiderId(auditoria.getAuditorLiderId())
            .objetivo(auditoria.getObjetivo())
            .alcance(auditoria.getAlcance())
            .estado(auditoria.getEstado())
            .createdAt(auditoria.getCreatedAt())
            .updatedAt(auditoria.getUpdatedAt())
            .build();
    }
	
	public static AuditoriaDTO toDTOMinimo(Auditoria auditoria) {
        return AuditoriaDTO.builder()
            .id(auditoria.getId())
            .tipoAuditoria(auditoria.getTipoAuditoria())
            .build();
    }

    public static Auditoria toDomain(AuditoriaDTO auditoriaDTO) {
        return Auditoria.builder()
            .id(auditoriaDTO.getId())
            .fechaInicio(auditoriaDTO.getFechaInicio())
            .fechaFin(auditoriaDTO.getFechaFin())
            .tipoAuditoria(auditoriaDTO.getTipoAuditoria())
            .auditorLiderId(auditoriaDTO.getAuditorLiderId())
            .objetivo(auditoriaDTO.getObjetivo())
            .alcance(auditoriaDTO.getAlcance())
            .estado(auditoriaDTO.getEstado())
            .createdAt(auditoriaDTO.getCreatedAt())
            .updatedAt(auditoriaDTO.getUpdatedAt())
            .build();
    }
}