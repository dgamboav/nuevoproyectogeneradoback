package com.dgamboav.nuevoproyectogeneradoback.specification;

import com.dgamboav.nuevoproyectogeneradoback.entidad.NoConformidad;
import com.dgamboav.nuevoproyectogeneradoback.utiles.FilterTypeInferer;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.metamodel.EntityType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NoConformidadSpecification implements Specification<NoConformidad> {

	private final transient FilterTypeInferer filterTypeInferer;
    private final transient  Map<String, Object> filtros;

    public NoConformidadSpecification(Map<String, Object> filtros, FilterTypeInferer filterTypeInferer) {
        this.filtros = filtros;
		this.filterTypeInferer = filterTypeInferer;
    }
	
	@Override
    public Predicate toPredicate(Root<NoConformidad> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        EntityType<NoConformidad> entityType = root.getModel();

        for (Map.Entry<String, Object> entry : filtros.entrySet()) {
            String atributoNombre = entry.getKey();
            Object valor = entry.getValue();

            Predicate predicate = filterTypeInferer.inferPredicate(criteriaBuilder, root, atributoNombre, valor, entityType);
            if (predicate != null) {
                predicates.add(predicate);
            }
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
	
}