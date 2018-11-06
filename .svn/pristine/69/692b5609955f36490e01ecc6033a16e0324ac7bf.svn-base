/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.mvc;

import com.nuebus.dto.CbMapaAsientoDTO;
import com.nuebus.seguridad.security.model.UserContext;
import com.nuebus.service.MapaAsientoService;
import com.nuebus.utilidades.IAuthenticationFacade;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Valeria
 */

@RestController
@CrossOrigin
@RequestMapping(value = "/api/mapaAsiento")
public class MapaAsientoController {
    @Autowired
    private IAuthenticationFacade authenticationFacade;
    final static Logger LOG = LoggerFactory.getLogger(MapaAsientoController.class);
    @Inject
    MapaAsientoService mapaAsientoService;
    
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CbMapaAsientoDTO>> findAllMapaAsiento( HttpServletRequest req) {
        //Page<ChoferDTO> page = choferService.findChoferes(pageable);
        
        Authentication authentication = authenticationFacade.getAuthentication();
        
        UserContext user = (UserContext)authentication.getPrincipal();
        
        List<CbMapaAsientoDTO> lista = mapaAsientoService.findAllMapaAsiento( user.getEmpresa());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    
}
