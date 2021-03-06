
package com.nuebus.service.impl;

import com.nuebus.dto.ComboDTO;
import com.nuebus.dto.ComboStrDTO;
import com.nuebus.dto.EnlaceLineasDTO;
import com.nuebus.model.EnlaceLineas;
import com.nuebus.model.Linea;
import com.nuebus.repository.EnlaceLineasRepository;
import com.nuebus.repository.LineaRepository;
import com.nuebus.service.LineaService;
import com.nuebus.vistas.MapperVistas;
import java.util.List;
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
 * @author Valeria
 */

@Service
@Transactional( readOnly = true)
public class LineaServiceImpl implements LineaService{
    
    final static Logger LOG = LoggerFactory.getLogger(LineaServiceImpl.class);

    
    @Autowired
    LineaRepository lineaRepository;
    @Autowired
    EnlaceLineasRepository enlaceLineasRepository;

    @Override
    public List<ComboStrDTO> finAllLinea(String empresa) {       
        return lineaRepository.findLineasByEmpresa( empresa )
               .stream().map( lin -> MapperVistas.toDTO(lin) ).collect( Collectors.toList() );
        
    }    

    @Override
    public List<Linea> findAllLineas(String idEmpresa) {
        return lineaRepository.findAllLineasByEmpresa( idEmpresa );
    }
    

    @Override
    @Transactional( readOnly = false )
    public void saveEnlaceLineas( EnlaceLineasDTO enlaceDTO ) { 
        
        EnlaceLineas enlace = new EnlaceLineas();
        
        Linea ida = lineaRepository.findById( enlaceDTO.getIdaPK() ).orElse( null );
        Linea vta = lineaRepository.findById( enlaceDTO.getVueltaPK() ).orElse( null );
        
        enlace.setEmpCodigo(enlaceDTO.getEmpCodigo());
        enlace.setIda(ida);
        enlace.setVuelta(vta);
        
        enlaceLineasRepository.save( enlace);        
    }

    @Override
    public Page<EnlaceLineas> finAllEnlaceLineasByEmpresa(String empCodigo, Pageable pageable) {
        return enlaceLineasRepository.findAllByEmpresa(empCodigo, pageable);
    }

    @Override
    @Transactional( readOnly = false )
    public void deleteEnlaceLineas(Long idEnlace) {
    	EnlaceLineas enlaceLinea = enlaceLineasRepository.findById( idEnlace ).orElse( null ); 
        enlaceLineasRepository.delete( enlaceLinea );
    }
    
}
