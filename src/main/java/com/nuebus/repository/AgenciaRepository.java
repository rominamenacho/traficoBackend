
package com.nuebus.repository;

import com.nuebus.model.Agencia;
import com.nuebus.model.AgenciaPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Valeria
 */
public interface AgenciaRepository extends JpaRepository<Agencia, AgenciaPK>{
    
}
