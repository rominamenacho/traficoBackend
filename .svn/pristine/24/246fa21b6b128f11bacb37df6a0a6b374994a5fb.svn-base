package com.nuebus.repository;

import com.nuebus.model.Escala;
import com.nuebus.vistas.combos.ComboStr;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Valeria
 */
public interface EscalaRepository extends JpaRepository<Escala, String>{
    
    @Query( " Select new com.nuebus.vistas.combos.ComboStr( e.escCodigo, e.escNombre ) "
            + " from Escala e where e.escProCodigo = ?1 " )    
    public ArrayList<ComboStr> findEscalasByProvincia( int escProCodigo);
            
    
}
