/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.service;

import com.nuebus.dto.CbMapaAsientoDTO;
import com.nuebus.repository.MapaAsientoRepository;
import com.nuebus.vistas.MapperVistas;
import java.util.ArrayList;
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
public class MapaAsientoServiceImpl implements MapaAsientoService{
    
    final static Logger LOG = LoggerFactory.getLogger(MapaAsientoServiceImpl.class);

    @Autowired
    MapaAsientoRepository mapaAsientoRepository;      

    @Override
    public List<CbMapaAsientoDTO> findAllMapaAsiento( String empresa) {           
        //return mapaAsientoRepository.findMapaAsientoByEmpresa().stream().map(cbMapaAsiento -> cbMapaAsientoMapper.toDTO(cbMapaAsiento));
        
        return mapaAsientoRepository.findMapaAsientoByEmpresa( empresa ).stream().
                map(cbMapaAsiento -> MapperVistas.toDTO(cbMapaAsiento)).collect(Collectors.toList());
    }
    
}
