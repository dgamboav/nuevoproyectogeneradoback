package com.dgamboav.nuevoproyectogeneradoback.entidad;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(schema = "companies")
public class Empresa {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
    

	private String name;
	
}