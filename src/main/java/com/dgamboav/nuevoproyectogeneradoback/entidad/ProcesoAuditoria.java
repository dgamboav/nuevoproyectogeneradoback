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
@Table(schema = "procesos_auditorias")
public class ProcesoAuditoria {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
    

	private Long auditoriaId;
    

	private Long procesoId;
    

	private String resultado;
    

	private LocalDateTime createdAt;
    

	private LocalDateTime updatedAt;
	
}