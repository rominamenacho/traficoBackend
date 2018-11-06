/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.repository;

import com.nuebus.model.Vuelta;
import java.util.ArrayList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Usuario
 */
public interface VueltaRepository extends JpaRepository<Vuelta, Long> {

    @Query(value = " Select v "
            + " from Vuelta v where "
            + "  v.vl_emp_codigo = ?1 "
            + "  and v.id_diagramacion = ?2 ", nativeQuery = true )
    public ArrayList<Vuelta> findVueltasByDiagramacion(String empresa,
            int diagramacion_id);

    @Query( " Select v "
            + " from Vuelta v where "
            + "  v.empCodigo = ?1 "
            + "  and v.fechaHoraSalida between ?2 and ?3 ")
    public Page<Vuelta> findVueltasByEmpresaAndFecha(String empresa, java.util.Date inicio, java.util.Date fin, Pageable pageable);

}
