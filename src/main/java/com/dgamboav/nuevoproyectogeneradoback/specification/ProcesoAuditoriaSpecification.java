package com.dgamboav.nuevoproyectogeneradoback.specification;

import com.dgamboav.nuevoproyectogeneradoback.entidad.ProcesoAuditoria;
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

public class ProcesoAuditoriaSpecification implements Specification<ProcesoAuditoria> {

	private final transient FilterTypeInferer filterTypeInferer;
    private final transient  Map<String, Object> filtros;

    public ProcesoAuditoriaSpecification(Map<String, Object> filtros, FilterTypeInferer filterTypeInferer) {
        this.filtros = filtros;
		this.filterTypeInferer = filterTypeInferer;
    }
	
	@Override
    public Predicate toPredicate(Root<ProcesoAuditoria> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        EntityType<ProcesoAuditoria> entityType = root.getModel();

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