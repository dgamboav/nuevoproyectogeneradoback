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
@Table(schema = "no_conformidades")
public class NoConformidad {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
    

	private String descripcion;
    

	private String causaRaiz;
    

	private String clasificacion;
    

	private LocalDate fechaDeteccion;
    

	private Long responsableId;
    

	private String estado;
    

	private LocalDateTime createdAt;
    

	private LocalDateTime updatedAt;
    

	private Long auditoriaId;
    

	private Long procesoId;
	
}