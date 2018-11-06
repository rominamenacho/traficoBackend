/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.service;

import com.nuebus.dto.CarnetDTO;
import com.nuebus.model.Carnet;
import com.nuebus.repository.CarnetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Valeria
 */
public class CarnetServiceImpl implements CarnetService{
    
    final static Logger LOG = LoggerFactory.getLogger(CarnetServiceImpl.class);

    @Autowired
    CarnetRepository carnetRepository;

    
    @Override
    public Page<CarnetDTO> findCarnets(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CarnetDTO getCarnet(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateCarnet(CarnetDTO carnetDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteCarnet(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveCarnet(Carnet carnet) {
        carnetRepository.save(carnet);
    }
    
}
