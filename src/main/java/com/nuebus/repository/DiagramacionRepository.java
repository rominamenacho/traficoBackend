/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.repository;

import com.nuebus.model.Diagramacion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Usuario
 */
public interface DiagramacionRepository extends JpaRepository<Diagramacion, Long> {

  /*  @Query("Select d from diagramacion d where d.emp_codigo = ?1 ")
    public List<Diagramacion> findDiagramacionesByEmpresa(String empresa);*/

    @Query("Select d from Diagramacion d where d.empCodigo = ?1 "
            + "and d.fechaDesde between ?2 and ?3 ")
    public Page<Diagramacion> findDiagramacionesByEmpresaAndFechas(String empresa, java.util.Date inicio, java.util.Date fin, Pageable pageable);

    @Query("Select d from Diagramacion d where d.empCodigo = ?1 "
            + "and d.fechaDesde between ?2 and ?3 ")
    public ArrayList<Diagramacion> findDiagramacionesByEmpresaAndFechas(String empresa, Date inicio, Date fin);

}
