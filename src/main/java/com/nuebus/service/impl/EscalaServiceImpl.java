package com.nuebus.service.impl;

import com.nuebus.repository.EscalaRepository;
import com.nuebus.service.EscalaService;
import com.nuebus.vistas.combos.ComboStr;
import java.util.ArrayList;
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
public class EscalaServiceImpl implements EscalaService{
    
    final static Logger LOG = LoggerFactory.getLogger(EscalaServiceImpl.class);

    @Autowired
    EscalaRepository escalaRepository;
    
    @Override
    public ArrayList<ComboStr> findEscalasByProvincia(int escProCodigo) {        
        return escalaRepository.findEscalasByProvincia(escProCodigo);
    }   
    
}
