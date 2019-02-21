
package com.nuebus.auth.repository;


import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nuebus.dto.UserDTO;
import com.nuebus.model.Usuario;

/**
 *
 * @author Valeria
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {    
     //Optional<Usuario> findByUsername( String login );
	
	Usuario findByUsername( String login );
    
}
