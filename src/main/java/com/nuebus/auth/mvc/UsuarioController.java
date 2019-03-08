/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.auth.mvc;



import com.nuebus.annotations.Descripcion;
import com.nuebus.annotations.DescripcionClase;
import com.nuebus.auth.service.UserService;
import com.nuebus.dto.UsuarioDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Valeria
 */


@DescripcionClase(value="Usuarios")
@RestController
@CrossOrigin
@RequestMapping(value = "api")
public class UsuarioController {
	
	
	final static Logger LOG = LoggerFactory.getLogger(UsuarioController.class);
    @Autowired
    UserService userService;
    
    
    @Descripcion(value="Listar Usuarios",permission="ROLE_USUARIOS_LISTAR")
    @RequestMapping(value = "/usuarios/{empresa}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or hasRole('ROLE_USUARIOS_LISTAR'))")
    public ResponseEntity< Page<UsuarioDTO>> geUsuarios(@PathVariable String empresa, 
    								@RequestParam(required = false) String busqueda, Pageable pageable){	
    	
    	
    	Page<UsuarioDTO> usuariosDTO = null;
    	
    	if( busqueda != null ) {
    		usuariosDTO =  userService.findAllByEmpresaAndBusqueda( empresa, busqueda,  pageable);    		
    	}else {
    		usuariosDTO =  userService.findAllUsuarioConDatos( empresa, pageable);
    	}
    	
        return new ResponseEntity<>( usuariosDTO, HttpStatus.OK );
    }
    
    /*    
    @RequestMapping(value = "/{empresa}/{legajo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDTO> geU(@PathVariable String empresa, @PathVariable long legajo , HttpServletRequest req){
        
        UsuarioPK usuarioPK = new UsuarioPK( empresa, legajo );
        
        UsuarioDTO usuario = usuarioService.getUsuario(usuarioPK);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void loginUsuario( @RequestBody UsuarioDTO usuarioDTO ) {        
        usuarioService.findByLoginPass(usuarioDTO);
    }*/
    
}
