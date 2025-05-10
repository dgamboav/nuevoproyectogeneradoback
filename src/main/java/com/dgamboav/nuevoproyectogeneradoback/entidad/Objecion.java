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
public class Objecion {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fechaCreacion")
    private LocalDate fechaCreacion;

    @Column(name = "periodoConstitucional")
    private String periodoConstitucional;

    @Column(name = "periodoLegislativo")
    private String periodoLegislativo;

    @Column(name = "numeroProyecto")
    private Integer numeroProyecto;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "prohija")
    private String prohija;

    @Column(name = "fechaDeLaNota")
    private LocalDate fechaDeLaNota;

    @Column(name = "alcance")
    private String alcance;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "informeDeGobierno")
    private String informeDeGobierno;

    @Column(name = "informeDeTrabajo")
    private String informeDeTrabajo;

    @Column(name = "segundoDebate")
    private String segundoDebate;

    @Column(name = "tercerDebate")
    private String tercerDebate;

    @Column(name = "insistencia")
    private String insistencia;

    @Column(name = "pdfNroLey")
    private String pdfNroLey;

    @Column(name = "publicado")
    private LocalDate publicado;

    @Column(name = "gaceta")
    private String gaceta;

    @Column(name = "comentario")
    private String comentario;

}