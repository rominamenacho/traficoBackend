
package com.nuebus.repository;

import com.nuebus.model.Linea;
import com.nuebus.model.LineaPK;
import com.nuebus.vistas.combos.Combo;
import com.nuebus.vistas.combos.ComboStr;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Valeria
 */
public interface LineaRepository extends JpaRepository< Linea, LineaPK>{
    
    
    @Query(" Select new com.nuebus.vistas.combos.ComboStr(l.lineaPK.linCodigo, l.linNombre) "
           + " from Linea l where l.lineaPK.linEmpCodigo = ?1  ")
    public ArrayList<ComboStr> findLineasByEmpresa( String in_empresa );
    
    
    @Query(" Select l from Linea l where l.lineaPK.linEmpCodigo = :idEmpresa ")
    public ArrayList<Linea> findAllLineasByEmpresa( @Param("idEmpresa") String idEmpresa );
    
}
