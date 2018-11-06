/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.mvc;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import com.nuebus.dto.UsuarioDTO;
import com.nuebus.model.UsuarioPK;
import com.nuebus.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Valeria
 */

@RestController
@CrossOrigin
@RequestMapping(value = "/api/usuario")
public class UsuarioController {
    
    final static Logger LOG = LoggerFactory.getLogger(UsuarioController.class);
    @Inject
    UsuarioService usuarioService;
    
    @RequestMapping(value = "/{empresa}/{legajo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDTO> geU(@PathVariable String empresa, @PathVariable long legajo , HttpServletRequest req){
        
        UsuarioPK usuarioPK = new UsuarioPK( empresa, legajo );
        
        UsuarioDTO usuario = usuarioService.getUsuario(usuarioPK);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void loginUsuario( @RequestBody UsuarioDTO usuarioDTO ) {        
        usuarioService.findByLoginPass(usuarioDTO);
    }
    
}
