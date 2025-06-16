package com.dgamboav.nuevoproyectogeneradoback.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.metamodel.EntityType;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.dgamboav.nuevoproyectogeneradoback.utiles.FilterTypeInferer;
import com.dgamboav.nuevoproyectogeneradoback.entidad.Empleado;

@ExtendWith(MockitoExtension.class)
class EmpleadoSpecificationTest {

    @Mock
    private FilterTypeInferer filterTypeInferer;

    @Test
    void testToPredicateConFiltros() {
        // Datos de prueba para los filtros
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("nombre", "test");
        filtros.put("edad", "30");
        filtros.put("activo", "true");

        // Crear una instancia de la Specification
        EmpleadoSpecification specification = new EmpleadoSpecification(filtros, filterTypeInferer);

        // Mockear los objetos necesarios para el toPredicate
        Root<Empleado> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        EntityType<Empleado> entityType = mock(EntityType.class);

        when(root.getModel()).thenReturn(entityType);

        Predicate predicateNombre = mock(Predicate.class);
        Predicate predicateEdad = mock(Predicate.class);
        Predicate predicateActivo = mock(Predicate.class);

        when(filterTypeInferer.inferPredicate(criteriaBuilder, root, "nombre", "test", entityType)).thenReturn(predicateNombre);
        when(filterTypeInferer.inferPredicate(criteriaBuilder, root, "edad", "30", entityType)).thenReturn(predicateEdad);
        when(filterTypeInferer.inferPredicate(criteriaBuilder, root, "activo", "true", entityType)).thenReturn(predicateActivo);

        // Mockear el comportamiento de criteriaBuilder.and()
        Predicate andPredicate = mock(Predicate.class);
        when(criteriaBuilder.and(predicateNombre, predicateEdad, predicateActivo)).thenReturn(andPredicate);

        // Ejecutar el método toPredicate
        Predicate resultPredicate = specification.toPredicate(root, query, criteriaBuilder);

        // Assertions
        assertNotNull(resultPredicate);
        assertEquals(andPredicate, resultPredicate);

        // Verificar que FilterTypeInferer.inferPredicate fue llamado con los parámetros correctos
        verify(filterTypeInferer, times(1)).inferPredicate(criteriaBuilder, root, "nombre", "test", entityType);
        verify(filterTypeInferer, times(1)).inferPredicate(criteriaBuilder, root, "edad", "30", entityType);
        verify(filterTypeInferer, times(1)).inferPredicate(criteriaBuilder, root, "activo", "true", entityType);

        // Verificar que criteriaBuilder.and() fue llamado con los predicates correctos
        verify(criteriaBuilder, times(1)).and(predicateNombre, predicateEdad, predicateActivo);
    }

    @Test
    void testToPredicateSinFiltros() {
        Map<String, Object> filtros = new HashMap<>();
        EmpleadoSpecification specification = new EmpleadoSpecification(filtros, filterTypeInferer);

        // Mockear los objetos necesarios para el toPredicate
        Root<Empleado> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        EntityType<Empleado> entityType = mock(EntityType.class);
        when(root.getModel()).thenReturn(entityType);
        specification.toPredicate(root, query, criteriaBuilder);

        verify(criteriaBuilder, times(1)).and(new Predicate[0]);
        verify(filterTypeInferer, never()).inferPredicate(any(), any(), any(), any(), any());
    }
}