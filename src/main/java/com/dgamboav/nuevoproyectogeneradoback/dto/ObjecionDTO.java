package com.dgamboav.nuevoproyectogeneradoback.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.*;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.dgamboav.nuevoproyectogeneradoback.entidad.Objecion;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ObjecionDTO {

	private Long id;	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate fechaCreacion;	
	@NotBlank(message = "El campo Periodo Constitucional es obligatorio")
	private String periodoConstitucional;	
	@NotBlank(message = "El campo Periodo Legislativo es obligatorio")
	private String periodoLegislativo;	
	private Integer numeroProyecto;	
	private String titulo;	
	private String prohija;	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate fechaDeLaNota;	
	private String alcance;	
	private String motivo;	
	private String informeDeGobierno;	
	private String informeDeTrabajo;	
	private String segundoDebate;	
	private String tercerDebate;	
	private String insistencia;	
	private String pdfNroLey;	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate publicado;	
	private String gaceta;	
	private String comentario;	

    public static ObjecionDTO toDTO(Objecion objecion) {
        return ObjecionDTO.builder()
            .id(objecion.getId())
            .fechaCreacion(objecion.getFechaCreacion())
            .periodoConstitucional(objecion.getPeriodoConstitucional())
            .periodoLegislativo(objecion.getPeriodoLegislativo())
            .numeroProyecto(objecion.getNumeroProyecto())
            .titulo(objecion.getTitulo())
            .prohija(objecion.getProhija())
            .fechaDeLaNota(objecion.getFechaDeLaNota())
            .alcance(objecion.getAlcance())
            .motivo(objecion.getMotivo())
            .informeDeGobierno(objecion.getInformeDeGobierno())
            .informeDeTrabajo(objecion.getInformeDeTrabajo())
            .segundoDebate(objecion.getSegundoDebate())
            .tercerDebate(objecion.getTercerDebate())
            .insistencia(objecion.getInsistencia())
            .pdfNroLey(objecion.getPdfNroLey())
            .publicado(objecion.getPublicado())
            .gaceta(objecion.getGaceta())
            .comentario(objecion.getComentario())
            .build();
    }
	
	public static ObjecionDTO toDTOMinimo(Objecion objecion) {
        return ObjecionDTO.builder()
            .id(objecion.getId())
            .periodoConstitucional(objecion.getPeriodoConstitucional())
            .periodoLegislativo(objecion.getPeriodoLegislativo())
            .informeDeGobierno(objecion.getInformeDeGobierno())
            .informeDeTrabajo(objecion.getInformeDeTrabajo())
            .build();
    }

    public static Objecion toDomain(ObjecionDTO objecionDTO) {
        return Objecion.builder()
            .id(objecionDTO.getId())
            .fechaCreacion(objecionDTO.getFechaCreacion())
            .periodoConstitucional(objecionDTO.getPeriodoConstitucional())
            .periodoLegislativo(objecionDTO.getPeriodoLegislativo())
            .numeroProyecto(objecionDTO.getNumeroProyecto())
            .titulo(objecionDTO.getTitulo())
            .prohija(objecionDTO.getProhija())
            .fechaDeLaNota(objecionDTO.getFechaDeLaNota())
            .alcance(objecionDTO.getAlcance())
            .motivo(objecionDTO.getMotivo())
            .informeDeGobierno(objecionDTO.getInformeDeGobierno())
            .informeDeTrabajo(objecionDTO.getInformeDeTrabajo())
            .segundoDebate(objecionDTO.getSegundoDebate())
            .tercerDebate(objecionDTO.getTercerDebate())
            .insistencia(objecionDTO.getInsistencia())
            .pdfNroLey(objecionDTO.getPdfNroLey())
            .publicado(objecionDTO.getPublicado())
            .gaceta(objecionDTO.getGaceta())
            .comentario(objecionDTO.getComentario())
            .build();
    }
}