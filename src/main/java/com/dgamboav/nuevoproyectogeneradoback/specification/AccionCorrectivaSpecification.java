package com.dgamboav.nuevoproyectogeneradoback.specification;

import com.dgamboav.nuevoproyectogeneradoback.entidad.AccionCorrectiva;
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

public class AccionCorrectivaSpecification implements Specification<AccionCorrectiva> {

	private final transient FilterTypeInferer filterTypeInferer;
    private final transient  Map<String, Object> filtros;

    public AccionCorrectivaSpecification(Map<String, Object> filtros, FilterTypeInferer filterTypeInferer) {
        this.filtros = filtros;
		this.filterTypeInferer = filterTypeInferer;
    }
	
	@Override
    public Predicate toPredicate(Root<AccionCorrectiva> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        EntityType<AccionCorrectiva> entityType = root.getModel();

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