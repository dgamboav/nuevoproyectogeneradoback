package com.dgamboav.nuevoproyectogeneradoback.entidad;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Faq {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
    

	
	private String question;
    

	@Column(columnDefinition = "TEXT")
	private String answer;
    

	
	private Integer specificOrder;
    

	
	private LocalDateTime createdAt;
    

	
	private LocalDateTime updatedAt;
}