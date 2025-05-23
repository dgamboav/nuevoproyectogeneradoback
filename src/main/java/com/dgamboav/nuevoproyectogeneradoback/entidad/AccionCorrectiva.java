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
@Table(schema = "acciones_correctivas")
public class AccionCorrectiva {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
    

	private String descripcion;
    

	private LocalDate fechaImplementacion;
    

	private LocalDate fechaVerificacion;
    

	private String estado;
    

	private Long responsableId;
    

	private LocalDateTime createdAt;
    

	private LocalDateTime updatedAt;
    

	private Long noConformidadId;
	
}