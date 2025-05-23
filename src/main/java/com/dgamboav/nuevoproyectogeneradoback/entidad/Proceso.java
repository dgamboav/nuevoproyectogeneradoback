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
@Table(schema = "procesos")
public class Proceso {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
    

	private String nombre;
    

	private String descripcion;
    

	private String codigoProceso;
    

	private Long responsableId;
    

	private LocalDateTime createdAt;
    

	private LocalDateTime updatedAt;
    

	private Long empresaId;
	
}