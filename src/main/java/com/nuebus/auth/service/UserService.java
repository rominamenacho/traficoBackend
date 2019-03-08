package com.nuebus.auth.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.nuebus.dto.UsuarioDTO;
import com.nuebus.model.Usuario;

public interface UserService {			        
               
        public  Usuario findByUsername( String login );                
        public Page<UsuarioDTO> findAllUsuarioConDatos( String empresa, Pageable pageable );
        public Page<UsuarioDTO> findAllByEmpresaAndBusqueda( String empresa, String busqueda, Pageable pageable );
    
}
