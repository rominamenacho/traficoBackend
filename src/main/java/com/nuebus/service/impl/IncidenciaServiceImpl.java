package com.nuebus.service.impl;

import com.nuebus.dto.ComboDTO;
import com.nuebus.dto.IncidenciaDTO;
import com.nuebus.excepciones.ResourceNotFoundException;
import com.nuebus.excepciones.ValidacionExcepcion;
import com.nuebus.mapper.IncidenciaMapper;
import com.nuebus.model.Incidencia;
import com.nuebus.repository.IncidenciaRepository;
import com.nuebus.service.IncidenciaService;
import com.nuebus.utilidades.Utilities;
import com.nuebus.vistas.MapperVistas;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class IncidenciaServiceImpl implements IncidenciaService{
    
    
     final static Logger LOG = LoggerFactory.getLogger(IncidenciaServiceImpl.class);

    @Autowired
    IncidenciaRepository incidenciaRepository;

    @Autowired
    IncidenciaMapper incidenciaMapper;

    @Override
    public Page<IncidenciaDTO> findIncidencias(Pageable pageable) {
        return incidenciaRepository.findAll(pageable).map(incidencia -> incidenciaMapper.toDTO(incidencia));
    }

    @Override
    public Incidencia getIncidencia(Long id) {
        Incidencia incidencia = incidenciaRepository.findById(id).orElse( null );
        if (incidencia == null) {
            throw new ResourceNotFoundException(id,"Incidencia no encontrada"); 
        } else {
            return incidencia;
        }
    }

    @Transactional(readOnly = false) 
    @Override
    public Incidencia updateIncidencia(long id, IncidenciaDTO incidenciaDTO)throws Exception {   	
        
        Incidencia incidencia = incidenciaRepository.findById(id).orElse( null );
        if (incidencia == null) {
            throw new ResourceNotFoundException(id,"Incidencia no encontrada"); 
        }     
        incidenciaMapper.mapToEntity(incidenciaDTO, incidencia);        
        Incidencia incidenciaUpdated = incidenciaRepository.save(incidencia);
        
        return incidenciaUpdated;        
    }
  
    @Transactional(readOnly = false) 
    @Override
    public Incidencia saveIncidencia(Incidencia incidencia) throws Exception{
    	
    	Incidencia incidenciaSaved =  null;
        
        //Map<String, Set<String>> errors = Utilities.validarEntity( incidencia );       
        Map<String, Set<String>> errors = new HashMap<>();
        
        if( existeCodigo( incidencia.getEmpresa(), incidencia.getTipo(), incidencia.getCodigo()) ){           
            errors.computeIfPresent( "codigo",( key, value ) -> { value.add( "el codigo especificado ya ha sido ocupado" ); return value; } );
            errors.computeIfAbsent( "codigo", key -> new HashSet<>()).add( "el codigo especificado ya ha sido ocupado" ); 
        } 
        
        if( errors.isEmpty() ){
        	incidenciaSaved = incidenciaRepository.save(incidencia);
        }else{            
            throw new ValidacionExcepcion( errors );            
        } 
        
        return incidenciaSaved;
    }

    @Transactional(readOnly = false) 
    @Override
    public void deleteIncidencia(Long id) {        
        Incidencia incidencia = incidenciaRepository.findById( id ).orElse( null );        
        if (incidencia == null) {
            throw new ResourceNotFoundException( id ,"Incidencia no encontrada"); 
        } else {
            incidenciaRepository.delete( incidencia );
        }                  
    }
    
    @Override
    public boolean existeCodigo(String empresa, int tipo, String codigo) {        
       return  incidenciaRepository.existeCodigoByEmpresayTipo(empresa, tipo, codigo);
    }
    
        
    @Override
    public Page<IncidenciaDTO> findIncidenciasByEmpresa(Pageable pageable, String empresa) {
        return incidenciaRepository.findIncidenciasByEmpresa(empresa, pageable ).map(incidencia -> incidenciaMapper.toDTO(incidencia));
    }    
    
    
    @Override
    public List<ComboDTO> findIncidenciasByEmpresayTipo(String empresa, int in_tipo) {     
        return incidenciaRepository.findIncidenciasByEmpresayTipo( empresa, in_tipo  ).stream().
                map( comboInc -> MapperVistas.toDTO(comboInc) ).collect(Collectors.toList());       
    }  
    
    
    
}
