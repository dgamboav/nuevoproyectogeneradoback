package com.dgamboav.nuevoproyectogeneradoback.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.dgamboav.nuevoproyectogeneradoback.entidad.AccionCorrectiva;
@Repository
public interface AccionCorrectivaRepository extends JpaRepository<AccionCorrectiva, Long>, JpaSpecificationExecutor<AccionCorrectiva> {
}