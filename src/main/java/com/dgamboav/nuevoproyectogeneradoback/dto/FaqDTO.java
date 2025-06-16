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
import com.dgamboav.nuevoproyectogeneradoback.entidad.Faq;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FaqDTO {

	private Long id;	
	@NotBlank(message = "El campo Pregunta es obligatorio")
	@Size(max = 500, message = "El question debe tener máximo 500 caracteres")
	private String question;	
	@NotBlank(message = "El campo Respuesta es obligatorio")
	private String answer;	
	@Size(min = 1, message = "El specificOrder debe tener minimo 1 caracteres")
	private Integer specificOrder;	
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime createdAt;	
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime updatedAt;	

    public static FaqDTO toDTO(Faq faq) {
        return FaqDTO.builder()
            .id(faq.getId())
            .question(faq.getQuestion())
            .answer(faq.getAnswer())
            .specificOrder(faq.getSpecificOrder())
            .createdAt(faq.getCreatedAt())
            .updatedAt(faq.getUpdatedAt())
            .build();
    }
	
	public static FaqDTO toDTOMinimo(Faq faq) {
        return FaqDTO.builder()
            .id(faq.getId())
            .question(faq.getQuestion())
            .build();
    }

    public static Faq toDomain(FaqDTO faqDTO) {
        return Faq.builder()
            .id(faqDTO.getId())
            .question(faqDTO.getQuestion())
            .answer(faqDTO.getAnswer())
            .specificOrder(faqDTO.getSpecificOrder())
            .createdAt(faqDTO.getCreatedAt())
            .updatedAt(faqDTO.getUpdatedAt())
            .build();
    }
	
	public static Faq copyProperties(Faq source, Faq target){
	    target.setQuestion(source.getQuestion());target.setAnswer(source.getAnswer());target.setSpecificOrder(source.getSpecificOrder());target.setCreatedAt(source.getCreatedAt());target.setUpdatedAt(source.getUpdatedAt());
		return target;
	}
}