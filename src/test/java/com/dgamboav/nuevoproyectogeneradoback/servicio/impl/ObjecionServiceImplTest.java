package com.dgamboav.nuevoproyectogeneradoback.servicio.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.PageImpl;

import com.dgamboav.nuevoproyectogeneradoback.repositorio.ObjecionRepository;
import com.dgamboav.nuevoproyectogeneradoback.dto.ObjecionDTO;
import com.dgamboav.nuevoproyectogeneradoback.entidad.Objecion;

import org.springframework.data.jpa.domain.Specification;

@ExtendWith(MockitoExtension.class)
class ObjecionServiceImplTest {

    @Mock
    private ObjecionRepository objecionRepository;

    @InjectMocks
    private ObjecionServiceImpl objecionService;

    @Test
    void testCrearObjecion() {
        ObjecionDTO objecionDTO = ObjecionDTO.builder()
				.fechaCreacion(null) // Valor por defecto para otros tipos
				.periodoConstitucional("test")
				.periodoLegislativo("test")
				.numeroProyecto(1)
				.titulo("test")
				.prohija("test")
				.fechaDeLaNota(null) // Valor por defecto para otros tipos
				.alcance("test")
				.motivo("test")
				.informeDeGobierno("test")
				.informeDeTrabajo("test")
				.segundoDebate("test")
				.tercerDebate("test")
				.insistencia("test")
				.pdfNroLey("test")
				.publicado(null) // Valor por defecto para otros tipos
				.gaceta("test")
				.comentario("test")
            .build();

        Objecion objecion = ObjecionDTO.toDomain(objecionDTO);
        when(objecionRepository.save(any(Objecion.class))).thenReturn(objecion);

        ObjecionDTO resultado = objecionService.crearObjecion(objecionDTO);

        assertEquals(ObjecionDTO.toDTO(objecion), resultado);
        verify(objecionRepository, times(1)).save(any(Objecion.class));
    }

    @Test
    void testObtenerObjecionPorId() {
        Objecion objecion = Objecion.builder()
				.fechaCreacion(null) // Valor por defecto para otros tipos
				.periodoConstitucional("test")
				.periodoLegislativo("test")
				.numeroProyecto(1)
				.titulo("test")
				.prohija("test")
				.fechaDeLaNota(null) // Valor por defecto para otros tipos
				.alcance("test")
				.motivo("test")
				.informeDeGobierno("test")
				.informeDeTrabajo("test")
				.segundoDebate("test")
				.tercerDebate("test")
				.insistencia("test")
				.pdfNroLey("test")
				.publicado(null) // Valor por defecto para otros tipos
				.gaceta("test")
				.comentario("test")
            .build();

        when(objecionRepository.findById(1L)).thenReturn(Optional.of(objecion));

        Optional<ObjecionDTO> resultado = objecionService.obtenerObjecionPorId(1L);

        assertEquals(Optional.of(ObjecionDTO.toDTO(objecion)), resultado);
        verify(objecionRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerTodosObjecion() {
        List<Objecion> objecions = List.of(
            Objecion.builder()
				.fechaCreacion(null) // Valor por defecto para otros tipos
				.periodoConstitucional("test")
				.periodoLegislativo("test")
				.numeroProyecto(1)
				.titulo("test")
				.prohija("test")
				.fechaDeLaNota(null) // Valor por defecto para otros tipos
				.alcance("test")
				.motivo("test")
				.informeDeGobierno("test")
				.informeDeTrabajo("test")
				.segundoDebate("test")
				.tercerDebate("test")
				.insistencia("test")
				.pdfNroLey("test")
				.publicado(null) // Valor por defecto para otros tipos
				.gaceta("test")
				.comentario("test")
                .build(),
            Objecion.builder()
				.fechaCreacion(null) // Valor por defecto para otros tipos
				.periodoConstitucional("test")
				.periodoLegislativo("test")
				.numeroProyecto(1)
				.titulo("test")
				.prohija("test")
				.fechaDeLaNota(null) // Valor por defecto para otros tipos
				.alcance("test")
				.motivo("test")
				.informeDeGobierno("test")
				.informeDeTrabajo("test")
				.segundoDebate("test")
				.tercerDebate("test")
				.insistencia("test")
				.pdfNroLey("test")
				.publicado(null) // Valor por defecto para otros tipos
				.gaceta("test")
				.comentario("test")
                .build()
        );
		
		Page<Objecion> objecionPage = new PageImpl<>(objecions, PageRequest.of(0, 10), objecions.size());

        when(objecionRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(objecionPage);

        Pageable pageable = PageRequest.of(0, 10);
		
        Map<String, Object> filtros = null;
		
        Page<ObjecionDTO> resultadoPage = objecionService.obtenerTodosObjecion(pageable, filtros);

        List<ObjecionDTO> expectedDTOs = objecions.stream().map(ObjecionDTO::toDTO).toList();		
		
		for (int i = 0; i < expectedDTOs.size(); i++) {

			assertEquals(expectedDTOs.get(i).getId(), resultadoPage.getContent().get(i).getId());
			assertEquals(expectedDTOs.get(i).getFechaCreacion(), resultadoPage.getContent().get(i).getFechaCreacion());
			assertEquals(expectedDTOs.get(i).getPeriodoConstitucional(), resultadoPage.getContent().get(i).getPeriodoConstitucional());
			assertEquals(expectedDTOs.get(i).getPeriodoLegislativo(), resultadoPage.getContent().get(i).getPeriodoLegislativo());
			assertEquals(expectedDTOs.get(i).getNumeroProyecto(), resultadoPage.getContent().get(i).getNumeroProyecto());
			assertEquals(expectedDTOs.get(i).getTitulo(), resultadoPage.getContent().get(i).getTitulo());
			assertEquals(expectedDTOs.get(i).getProhija(), resultadoPage.getContent().get(i).getProhija());
			assertEquals(expectedDTOs.get(i).getFechaDeLaNota(), resultadoPage.getContent().get(i).getFechaDeLaNota());
			assertEquals(expectedDTOs.get(i).getAlcance(), resultadoPage.getContent().get(i).getAlcance());
			assertEquals(expectedDTOs.get(i).getMotivo(), resultadoPage.getContent().get(i).getMotivo());
			assertEquals(expectedDTOs.get(i).getInformeDeGobierno(), resultadoPage.getContent().get(i).getInformeDeGobierno());
			assertEquals(expectedDTOs.get(i).getInformeDeTrabajo(), resultadoPage.getContent().get(i).getInformeDeTrabajo());
			assertEquals(expectedDTOs.get(i).getSegundoDebate(), resultadoPage.getContent().get(i).getSegundoDebate());
			assertEquals(expectedDTOs.get(i).getTercerDebate(), resultadoPage.getContent().get(i).getTercerDebate());
			assertEquals(expectedDTOs.get(i).getInsistencia(), resultadoPage.getContent().get(i).getInsistencia());
			assertEquals(expectedDTOs.get(i).getPdfNroLey(), resultadoPage.getContent().get(i).getPdfNroLey());
			assertEquals(expectedDTOs.get(i).getPublicado(), resultadoPage.getContent().get(i).getPublicado());
			assertEquals(expectedDTOs.get(i).getGaceta(), resultadoPage.getContent().get(i).getGaceta());
			assertEquals(expectedDTOs.get(i).getComentario(), resultadoPage.getContent().get(i).getComentario());

        }
		
		ArgumentCaptor<Specification<Objecion>> specificationCaptor = ArgumentCaptor.forClass(Specification.class);
		
		verify(objecionRepository, times(1)).findAll(specificationCaptor.capture(), any(Pageable.class));
    }
	
	@Test
     void testObtenerTodosMinObjecion() {
         List<Objecion> objecions = List.of(
             Objecion.builder()
					.periodoConstitucional("test")
					.periodoLegislativo("test")
					.informeDeGobierno("test")
					.informeDeTrabajo("test")
                 .build(),
             Objecion.builder()
					.periodoConstitucional("test")
					.periodoLegislativo("test")
					.informeDeGobierno("test")
					.informeDeTrabajo("test")
                 .build()
         );
 
         when(objecionRepository.findAll()).thenReturn(objecions);
 
         List<ObjecionDTO> resultado = objecionService.obtenerTodosMinObjecion();
 
         List<ObjecionDTO> expected = objecions.stream().map(ObjecionDTO::toDTO).toList();
         assertEquals(expected, resultado);
         verify(objecionRepository, times(1)).findAll();
     }

    @Test
    void testActualizarObjecion() {
        ObjecionDTO objecionDTO = ObjecionDTO.builder()
				.fechaCreacion(null) // Valor por defecto para otros tipos
				.periodoConstitucional("test")
				.periodoLegislativo("test")
				.numeroProyecto(1)
				.titulo("test")
				.prohija("test")
				.fechaDeLaNota(null) // Valor por defecto para otros tipos
				.alcance("test")
				.motivo("test")
				.informeDeGobierno("test")
				.informeDeTrabajo("test")
				.segundoDebate("test")
				.tercerDebate("test")
				.insistencia("test")
				.pdfNroLey("test")
				.publicado(null) // Valor por defecto para otros tipos
				.gaceta("test")
				.comentario("test")
            .build();

        Objecion objecion = ObjecionDTO.toDomain(objecionDTO);
        when(objecionRepository.findById(1L)).thenReturn(Optional.of(objecion));
        when(objecionRepository.save(any(Objecion.class))).thenReturn(objecion);

        Optional<ObjecionDTO> resultado = objecionService.actualizarObjecion(1L, objecionDTO);

        assertEquals(Optional.of(ObjecionDTO.toDTO(objecion)), resultado);
        verify(objecionRepository, times(1)).findById(1L);
        verify(objecionRepository, times(1)).save(any(Objecion.class));
    }

    @Test
    void testEliminarObjecion() {
        objecionService.eliminarObjecion(1L);

        verify(objecionRepository, times(1)).deleteById(1L);
    }
}