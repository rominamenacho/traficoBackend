/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.auth.controllers;



import com.nuebus.annotations.Descripcion;
import com.nuebus.annotations.DescripcionClase;
import com.nuebus.auth.service.UserService;
import com.nuebus.dto.GroupDTO;
import com.nuebus.dto.UsuarioDTO;
import com.nuebus.utilidades.IAuthenticationFacade;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestBody;
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
    @Autowired
  	private IAuthenticationFacade authenticationFacade;     
    
    @Descripcion(value="Listar Usuarios de todas las empresas",permission="ROLE_USUARIOS_TODOS_LISTAR")
    @RequestMapping(value = "/usuarios", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_USUARIOS_LISTAR') or hasRole('ROLE_USUARIOS_TODOS_LISTAR'))")
    public ResponseEntity< Page<UsuarioDTO>> getUsuarios( @RequestParam(required = false) String busqueda, 
    		Pageable pageable, HttpServletRequest request){	   
    	
    	Page<UsuarioDTO> usuariosDTO = null;
    	
    	if ( request.isUserInRole("ROLE_USUARIOS_TODOS_LISTAR")
	   			 || request.isUserInRole("ROLE_ADMIN") ) {    		    	
        	if( busqueda != null ) {
        		usuariosDTO =  userService.findAllByBusqueda( busqueda,  pageable);    		
        	}else {
        		usuariosDTO =  userService.findAllUsuarioConDatos( pageable);
        	}                		
	   	} else {
	   		usuariosDTO = getUsrByEmpresa(  authenticationFacade.getEmpresa() ,  busqueda, pageable );
	   	}    	
    	
        return new ResponseEntity<>( usuariosDTO, HttpStatus.OK );
    }
    
    @Descripcion(value="Listar Usuarios Propios",permission="ROLE_USUARIOS_LISTAR")
    @RequestMapping(value = "/usuarios/{empresa}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_USUARIOS_LISTAR'))")
    public ResponseEntity< Page<UsuarioDTO>> getUsuariosByEmpresa(@PathVariable String empresa, 
    								@RequestParam(required = false) String busqueda, Pageable pageable){   	
    	
        return new ResponseEntity<>( getUsrByEmpresa(  empresa,  busqueda, pageable ), HttpStatus.OK );
    }
    
    private Page<UsuarioDTO> getUsrByEmpresa( String empresa, String busqueda, Pageable pageable ) {
    	
    	Page<UsuarioDTO> usuariosDTO = null;
    	
    	if( busqueda != null ) {
    		usuariosDTO =  userService.findAllByEmpresaAndBusqueda( empresa, busqueda,  pageable);    		
    	}else {
    		usuariosDTO =  userService.findAllUsuarioByEmpresaConDatos( empresa, pageable);
    	}
    	
    	return usuariosDTO;
    }
    
    
    @Descripcion(value="Modificar Usuarios",permission="ROLE_USUARIOS_MODIFICAR")
    @RequestMapping(value = "/usuarios/{username}/changeGrupo", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_USUARIOS_MODIFICAR'))")
    public ResponseEntity< Page<UsuarioDTO>> modificarUsuario( @PathVariable String username,
    		@RequestBody GroupDTO groupDTO ){	    	
    	userService.updateGrupoUsuario( username, groupDTO );    	
    	return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }    
       
}
