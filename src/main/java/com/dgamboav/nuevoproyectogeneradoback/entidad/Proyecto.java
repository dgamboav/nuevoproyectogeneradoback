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
public class Proyecto {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "clienteId")
    private Long clienteId;

    @Column(name = "empleadoId")
    private Long empleadoId;

    @Column(name = "fechaCreacion")
    private LocalDate fechaCreacion;

    @Column(name = "costo")
    private Double costo;

}