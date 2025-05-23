package com.dgamboav.nuevoproyectogeneradoback.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.dgamboav.nuevoproyectogeneradoback.entidad.NoConformidad;
@Repository
public interface NoConformidadRepository extends JpaRepository<NoConformidad, Long>, JpaSpecificationExecutor<NoConformidad> {
}