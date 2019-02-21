package com.nuebus.auth.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nuebus.dto.UserDTO;
import com.nuebus.model.Usuario;

public interface UserService {			        
               
        public  Usuario findByUsername( String login );                
        public Page<UserDTO> findAllUsuarioConDatos( Pageable pageable );
    
}
