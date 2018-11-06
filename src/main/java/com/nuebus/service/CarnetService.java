/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.service;

import com.nuebus.dto.CarnetDTO;
import com.nuebus.model.Carnet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Valeria
 */
public interface CarnetService {
    
    public Page<CarnetDTO> findCarnets(Pageable pageable);
    public CarnetDTO getCarnet(Long id);    
    public void updateCarnet(CarnetDTO carnetDTO);    
    public void deleteCarnet(Long id);
    public void saveCarnet(Carnet carnet);
    
}
