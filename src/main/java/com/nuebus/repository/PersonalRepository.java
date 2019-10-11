package com.nuebus.repository;

import com.nuebus.model.Personal;
import com.nuebus.model.PersonalPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Valeria
 */
public interface PersonalRepository extends JpaRepository<Personal, PersonalPK>{
    
}
