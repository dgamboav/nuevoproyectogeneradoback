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
@Table(schema = "conformidades")
public class Conformidad {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
    

	private String descripcion;
    

	private LocalDate fechaDeteccion;
    

	private Long responsableId;
    

	private LocalDateTime createdAt;
    

	private LocalDateTime updatedAt;
    

	private Long auditoriaId;
    

	private Long procesoId;
	
}