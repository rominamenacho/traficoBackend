/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.service;

import com.nuebus.dto.ComboDTO;
import com.nuebus.dto.IncidenciaDTO;
import com.nuebus.model.Incidencia;
import com.nuebus.vistas.combos.Combo;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Usuario
 */
public interface  IncidenciaService {
    
   public Page<IncidenciaDTO> findIncidencias(Pageable pageable);

   public Incidencia getIncidencia(Long id);

   public Incidencia updateIncidencia(long id, IncidenciaDTO incidenciaDTO)throws Exception;

   public Incidencia saveIncidencia(Incidencia incidencia)throws Exception;

   public void deleteIncidencia(Long id);
   
   public boolean existeCodigo( String empresa, int tipo, String codigo);
   
   public Page<IncidenciaDTO> findIncidenciasByEmpresa(Pageable pageable, String empresa);
   
   public List<Incidencia> findIncidenciasByEmpresayTipo( String empresa, int in_tipo);
    
   
}
