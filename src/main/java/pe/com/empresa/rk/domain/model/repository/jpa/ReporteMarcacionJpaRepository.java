package pe.com.empresa.rk.domain.model.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.com.empresa.rk.domain.model.entities.ReporteMarcacion;

@Repository
public interface ReporteMarcacionJpaRepository extends CrudRepository<ReporteMarcacion, Long>, JpaRepository<ReporteMarcacion, Long>{

	
}
