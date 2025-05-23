package com.dgamboav.nuevoproyectogeneradoback.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.dgamboav.nuevoproyectogeneradoback.entidad.Auditoria;
@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Long>, JpaSpecificationExecutor<Auditoria> {
}