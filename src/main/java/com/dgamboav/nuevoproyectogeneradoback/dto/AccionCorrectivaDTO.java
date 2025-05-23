package com.dgamboav.nuevoproyectogeneradoback.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;


import java.time.*;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.dgamboav.nuevoproyectogeneradoback.entidad.AccionCorrectiva;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccionCorrectivaDTO {

	private Long id;	
	@NotBlank(message = "El campo descripcion es obligatorio")
	private String descripcion;	
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaImplementacion;	
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaVerificacion;	
	@NotBlank(message = "El campo estado es obligatorio")
	private String estado;	
	@NotBlank(message = "El campo Responsable es obligatorio")
	private Long responsableId;	
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;	
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updatedAt;	
	@NotBlank(message = "El campo No Conformidad es obligatorio")
	private Long noConformidadId;	

    public static AccionCorrectivaDTO toDTO(AccionCorrectiva accionCorrectiva) {
        return AccionCorrectivaDTO.builder()
            .id(accionCorrectiva.getId())
            .descripcion(accionCorrectiva.getDescripcion())
            .fechaImplementacion(accionCorrectiva.getFechaImplementacion())
            .fechaVerificacion(accionCorrectiva.getFechaVerificacion())
            .estado(accionCorrectiva.getEstado())
            .responsableId(accionCorrectiva.getResponsableId())
            .createdAt(accionCorrectiva.getCreatedAt())
            .updatedAt(accionCorrectiva.getUpdatedAt())
            .noConformidadId(accionCorrectiva.getNoConformidadId())
            .build();
    }
	
	public static AccionCorrectivaDTO toDTOMinimo(AccionCorrectiva accionCorrectiva) {
        return AccionCorrectivaDTO.builder()
            .id(accionCorrectiva.getId())
            .build();
    }

    public static AccionCorrectiva toDomain(AccionCorrectivaDTO accionCorrectivaDTO) {
        return AccionCorrectiva.builder()
            .id(accionCorrectivaDTO.getId())
            .descripcion(accionCorrectivaDTO.getDescripcion())
            .fechaImplementacion(accionCorrectivaDTO.getFechaImplementacion())
            .fechaVerificacion(accionCorrectivaDTO.getFechaVerificacion())
            .estado(accionCorrectivaDTO.getEstado())
            .responsableId(accionCorrectivaDTO.getResponsableId())
            .createdAt(accionCorrectivaDTO.getCreatedAt())
            .updatedAt(accionCorrectivaDTO.getUpdatedAt())
            .noConformidadId(accionCorrectivaDTO.getNoConformidadId())
            .build();
    }
}