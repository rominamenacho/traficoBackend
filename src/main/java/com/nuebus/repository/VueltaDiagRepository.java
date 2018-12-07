
package com.nuebus.repository;

import com.nuebus.model.VueltaDiag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Valeria
 */
public interface VueltaDiagRepository extends JpaRepository< VueltaDiag, Long> {
    
    @Query( " Select v from VueltaDiag v "
        +   "   where v.servicio.servicioPK.serEmpCodigo = :empresa "
        +   "       and  v.servicio.servicioPK.serLinCodigo = :lineaIda "   
        +   "       and trunc( v.servicio.servicioPK.serFechaHora ) between :inicio and :fin  "   )
    public List<VueltaDiag> findByFechaServiciosIda( @Param("empresa") String empresa, @Param("lineaIda") String lineaIda,
            @Param("inicio")java.util.Date inicio, @Param("fin") java.util.Date fin );
    
    
}
