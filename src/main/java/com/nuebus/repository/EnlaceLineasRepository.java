
package com.nuebus.repository;

import com.nuebus.dto.ConfServiciosDTO;
import com.nuebus.model.EnlaceLineas;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Valeria
 */

public interface EnlaceLineasRepository extends JpaRepository< EnlaceLineas , Long >{
    
    
      @Query( "Select e from  EnlaceLineas e where  e.empCodigo = :empCodigo " )      
      Page<EnlaceLineas> findAllByEmpresa( @Param("empCodigo") String empCodigo, Pageable pageable );
      
      
      @Query( value = " Select  emp_codigo, lin_codigo, refuerzo, hora, cabecera, lunes, martes, miercoles, jueves, viernes, "
              + " sabado, domingo, feriado, descrip, mpa_lunes, mpa_martes, mpa_miercoles, "
              + " mpa_jueves, mpa_viernes, mpa_sabado, mpa_domingo, mpa_feriado, desc_dias  "
              + "  from  conf_servicios_view "
              + "    where emp_codigo=?1 and lin_codigo=?2 ",  nativeQuery = true  )
      List< Object > findConfServicios( String emp_codigo, String lin_codigo );
      
}
