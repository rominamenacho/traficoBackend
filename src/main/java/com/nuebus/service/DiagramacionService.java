/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.service;

import com.nuebus.dto.DiagramacionDTO;
import com.nuebus.model.Diagramacion;
import com.nuebus.model.Vuelta;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 
 * @author Usuario
 */

public interface DiagramacionService {

    
    public Page<DiagramacionDTO> findDiagramacion(Pageable pageable);
    
    public DiagramacionDTO getDiagramacion(Long idDiagramacion);    
    
    public void updateDiagramacion(Long id, DiagramacionDTO diagramacionDTO)throws Exception;    
     
   // public void saveDiagramacion(DiagramacionDTO diagramacionDTO) throws Exception;
    public void saveDiagramacion(Diagramacion diagramacion) throws Exception;
  
    public void deleteDiagramacion(Long idDiagramacion);

    public List<DiagramacionDTO> findDiagramacionByFecha(String empresa,
            java.util.Date inicio, java.util.Date fin);

  //  public List<DiagramacionDTO> findDiagramacionesByEmpresa(String empresa);

    public void setVueltas(Long diagramacion, List<Vuelta> vueltas) throws Exception;
    
    
    //findVehiculosByEmpresa

}