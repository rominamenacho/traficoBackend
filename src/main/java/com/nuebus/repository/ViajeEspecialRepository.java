
package com.nuebus.repository;


import com.nuebus.dto.ViajeEspecialDTO;
import com.nuebus.model.ViajeEspecial;
import java.util.ArrayList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Valeria
 */
public interface ViajeEspecialRepository extends JpaRepository<ViajeEspecial, Long>{
    
    @Query(" Select v "
           + " from ViajeEspecial v where "
           + "  v.empCodigo = ?1 "            
           + "  and v.fechaHoraSalida between ?2 and ?3 ")
    public ArrayList<ViajeEspecial> findViajesByFecha( String empresa, 
            java.util.Date inicio,  java.util.Date fin );
   
     
    @Query(" Select v "
           + " from ViajeEspecial v where "
           + "  v.empCodigo = ?1 "            
           + "  and v.fechaHoraSalida between ?2 and ?3 ")
    public Page<ViajeEspecial> findViajesByEmpresaAndFecha( String empresa, java.util.Date inicio,  java.util.Date fin, Pageable pageable );
    
    
    
    
    
}
