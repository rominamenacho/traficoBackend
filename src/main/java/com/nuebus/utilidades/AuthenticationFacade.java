/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.utilidades;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.nuebus.auth.service.UserService;
import com.nuebus.auth.service.impl.GroupServiceImpl;
import com.nuebus.model.Usuario;

/**
 *
 * @author Valeria
 */


@Component
public class AuthenticationFacade implements IAuthenticationFacade {
	
	final static org.slf4j.Logger log =LoggerFactory.getLogger(AuthenticationFacade.class);
	
	Usuario usuario;
	
	@Autowired
	UserService userService;

	
    public AuthenticationFacade() {
    	super();
    	this.usuario = null;       
    }

    // API

    @Override
    public final Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
    
    
    @Override
    public final String getUserName(){            

    	if(  getAuthentication() != null  ) {
    		
    		if( getAuthentication().getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
    			log.info("Autentication User");
    			return ( (org.springframework.security.core.userdetails.User)getAuthentication().getPrincipal() ).getUsername();
    		}else {
    			log.info("Autentication String");
    			return (String)getAuthentication().getPrincipal();
    		}    		
    	}
        return "**NN**";
    }
    
    @Override
    public final String getEmpresa() {    	    	
    	return ( getUsuario() != null 
    			 && getUsuario().getUsuarioPk() != null)? getUsuario().getUsuarioPk().getEmpresa() : "**emp**";    	
    }

	@Override
	public final Usuario getUsuario() {		
		if( this.usuario == null ) {
			this.usuario = userService.findByUsername( getUserName()); 
		}		    
		return this.usuario;
	}

}