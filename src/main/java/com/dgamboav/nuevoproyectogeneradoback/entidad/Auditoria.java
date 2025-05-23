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
@Table(schema = "auditorias")
public class Auditoria {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
    

	private LocalDate fechaInicio;
    

	private LocalDate fechaFin;
    

	private String tipoAuditoria;
    

	private Long auditorLiderId;
    

	private String objetivo;
    

	private String alcance;
    

	private String estado;
    

	private LocalDateTime createdAt;
    

	private LocalDateTime updatedAt;
	
}