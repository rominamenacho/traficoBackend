/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.repository;

import com.nuebus.model.Carnet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Valeria
 */
public interface CarnetRepository extends JpaRepository<Carnet, Long>, CarnetRepositoryCustom {
    
}
