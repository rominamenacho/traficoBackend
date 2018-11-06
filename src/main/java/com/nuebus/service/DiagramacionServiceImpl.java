/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.service;

import com.nuebus.dto.DiagramacionDTO;
import com.nuebus.excepciones.ValidacionExcepcion;
import com.nuebus.mapper.DiagramacionMapper;
import com.nuebus.model.Diagramacion;
import com.nuebus.model.Vuelta;
import com.nuebus.repository.DiagramacionRepository;
import com.nuebus.repository.VueltaRepository;
import com.nuebus.utilidades.Utilities;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Usuario
 */
@Service
@Transactional(readOnly = true) 
public class DiagramacionServiceImpl implements DiagramacionService {

    @Autowired
    DiagramacionRepository diagramacionRepository;

    @Autowired
     DiagramacionMapper diagramacionMapper;
 
    @Autowired
     VueltaRepository vueltaRepository;
  
    
    
    @Override
    @Transactional
    public void saveDiagramacion(Diagramacion diagramacion) throws Exception {
        //Diagramacion d = getDiagramacionMapper().toEntity(diagramacionDTO);
        Map<String, Set<String>> errors = Utilities.validarEntity(diagramacion);
        if (errors.isEmpty()) {
            diagramacionRepository.save(diagramacion);
        } else {
            throw new ValidacionExcepcion(errors);
        }
    }
    /*public void saveDiagramacion(DiagramacionDTO diagramacionDTO) throws Exception {
        Diagramacion d = getDiagramacionMapper().toEntity(diagramacionDTO);
        Map<String, Set<String>> errors = Utilities.validarEntity(d);
        if (errors.isEmpty()) {
            getDiagramacionRepository().save(d);
        } else {
            throw new ValidacionExcepcion(errors);
        }
    }*/

    
    
    
    @Override
    @SuppressWarnings("empty-statement")
    public List<DiagramacionDTO> findDiagramacionByFecha(String empresa, Date inicio, Date fin) {
        return diagramacionRepository.findDiagramacionesByEmpresaAndFechas(empresa, inicio, fin).stream()
                .map(diagramacion -> diagramacionMapper.toDTO(diagramacion)).collect(Collectors.toList());
    }

   /* @Override
    public List<DiagramacionDTO> findDiagramacionesByEmpresa(String empresa) {
        return diagramacionRepository.findDiagramacionesByEmpresa(empresa).stream()
                .map(diag -> diagramacionMapper.toDTO(diag)).collect(Collectors.toList());
    }*/

    
    
    
    
    @Override
    public void setVueltas(Long diagramacion, List<Vuelta> vueltas) throws Exception {
        Diagramacion d = diagramacionRepository.findOne(diagramacion);
        Stack<Vuelta> vuletasStack = new Stack<>();
        Vuelta v = null;
        for (Vuelta vu : vueltas) {
            v = new Vuelta();
            v.setDiagramacion(diagramacionRepository.findOne(diagramacion));
            vuletasStack.add(v);
        }

       diagramacionRepository.save(d);
    }

    @Override
    public void deleteDiagramacion(Long idDiagramacion) {
        Diagramacion d = diagramacionRepository.getOne(idDiagramacion);
        diagramacionRepository.delete(idDiagramacion);
    }

    

    @Override
    public Page<DiagramacionDTO> findDiagramacion(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DiagramacionDTO getDiagramacion(Long idDiagramacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateDiagramacion(Long id, DiagramacionDTO diagramacionDTO) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
