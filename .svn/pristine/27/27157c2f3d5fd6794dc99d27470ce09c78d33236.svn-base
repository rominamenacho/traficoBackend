
package com.nuebus.service;

import com.nuebus.dto.ComboDTO;
import com.nuebus.dto.ComboStrDTO;
import com.nuebus.repository.LineaRepository;
import com.nuebus.vistas.MapperVistas;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Valeria
 */

@Service
@Transactional
public class LineaServiceImpl implements LineaService{
    
    final static Logger LOG = LoggerFactory.getLogger(LineaServiceImpl.class);

    
    @Autowired
    LineaRepository lineaRepository;

    @Override
    public List<ComboStrDTO> finAllLinea(String empresa) {       
        return lineaRepository.findLineasByEmpresa( empresa )
               .stream().map( lin -> MapperVistas.toDTO(lin) ).collect( Collectors.toList() );
        
    }    
    
}
