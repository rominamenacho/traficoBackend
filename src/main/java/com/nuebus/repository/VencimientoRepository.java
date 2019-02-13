package com.nuebus.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nuebus.model.Vencimiento;

public interface VencimientoRepository extends JpaRepository< Vencimiento, Long > {

	List<Vencimiento> findByEmpresa( String empresa );
	
	@Query( "Select v from Vencimiento v where v.empresa = :empresa "
			+ "  and v.tipoVencimiento.nombreEntidad = :nombreEntidad "
			+ "	 and v.activo = :estado	")
	List<Vencimiento> findByEmpresaAndNombreEntidad( @Param("empresa") String empresa,
			@Param("nombreEntidad") String nombreEntidad, @Param("estado") boolean estado );
}
