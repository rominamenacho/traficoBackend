/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.repository;

import com.nuebus.model.MapaAsiento;
import com.nuebus.model.MapaAsientoPK;
import com.nuebus.vistas.combos.CbMapaAsiento;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Valeria
 */
public interface  MapaAsientoRepository  extends JpaRepository<MapaAsiento, MapaAsientoPK>{
    
    @Query( " Select new com.nuebus.vistas.combos.CbMapaAsiento(a.mapaAsientoPK.codigo, a.descripcion) from MapaAsiento a "
            + " where a.mapaAsientoPK.empresa  =?1 "    )
    public ArrayList<CbMapaAsiento> findMapaAsientoByEmpresa( String empresa );
    
}
