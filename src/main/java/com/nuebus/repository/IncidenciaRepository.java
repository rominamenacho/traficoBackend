/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.repository;

import com.nuebus.model.Incidencia;
import com.nuebus.vistas.combos.Combo;
import java.util.ArrayList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Usuario
 */
public interface IncidenciaRepository extends PagingAndSortingRepository<Incidencia, Long>{
    
    @Query( " Select CASE WHEN COUNT(i) > 0 THEN true ELSE false END from Incidencia i "
            + " where i.in_empresa  =?1 "
            + "   and i.in_tipo=?2 "
            + "   and i.codigo=?3  " )
    public boolean existeCodigoByEmpresayTipo( String empresa, int tipo , String codigo );

        
    @Query("Select i from Incidencia i where i.in_empresa = ?1 ")
    public Page<Incidencia> findIncidenciasByEmpresa(String in_empresa , Pageable pageable);   
    
  
    @Query(" Select new com.nuebus.vistas.combos.Combo(i.id, i.in_descripcion) "
           + " from Incidencia i where i.in_empresa = ?1 and i.in_tipo = ?2 ")
    public ArrayList<Combo> findIncidenciasByEmpresayTipo(String in_empresa , int in_tipo);       
    
}
