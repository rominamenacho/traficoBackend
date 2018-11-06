
package com.nuebus.service;

import com.nuebus.repository.ProvinciaRepository;
import com.nuebus.vistas.combos.ComboStr;
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
public class ProvinciaServiceImpl implements ProvinciaService{
    
    final static Logger LOG = LoggerFactory.getLogger(ProvinciaServiceImpl.class);
    
    @Autowired
    ProvinciaRepository provinciaRepository;

    @Override
    public List<ComboStr> findAllProvincias() {
        
        return provinciaRepository.findAll().stream().map( prov 
           -> new ComboStr( String.valueOf( prov.getProCodigo() ), prov.getProNombre() ) ).collect(Collectors.toList());
    }
    
    
    
}
