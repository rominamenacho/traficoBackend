package com.nuebus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nuebus.model.HorarioServicio;
import com.nuebus.model.HorarioServicioPK;
import com.nuebus.model.ServicioPK;

public interface HorarioServicioRepository extends JpaRepository<HorarioServicio, HorarioServicioPK>{
	
	
	@Query( "Select h from HorarioServicio h where h.horarioServicioPK.servicio.servicioPK = :servicioPK ")
	List<HorarioServicio> findByServicioPK( @Param("servicioPK") ServicioPK  servicioPK);

}
