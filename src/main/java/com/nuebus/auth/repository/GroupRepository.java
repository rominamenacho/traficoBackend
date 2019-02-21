
package com.nuebus.auth.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nuebus.model.Group;

/**
 *
 * @author Valeria
 */
public interface GroupRepository extends JpaRepository<Group, Long> {
    
     @Query(" Select g from Group g where  g.id != :idGroupAdmin and g.id != :idGroupUsuarioPrestamo ")
     Page<Group> findGruposComunes(Pageable pageable, 
            @Param("idGroupAdmin") Long idGroupAdmin,
            @Param("idGroupUsuarioPrestamo") Long idGroupUsuarioPrestamo);
    
}
