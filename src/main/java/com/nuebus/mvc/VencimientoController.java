package com.nuebus.mvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nuebus.annotations.Descripcion;
import com.nuebus.annotations.DescripcionClase;
import com.nuebus.dto.VehiculoDTO;
import com.nuebus.model.Vencimiento;
import com.nuebus.service.VencimientoService;

@DescripcionClase(value="Vencimientos")
@RestController
@RequestMapping(value = "api")
public class VencimientoController {
	
	  final static Logger LOG = LoggerFactory.getLogger(VencimientoController.class);
	
	  @Autowired
	  VencimientoService vencimientoService;
	  
	  

	  @Descripcion(value="Gestionar Vencimientos",permission="ROLE_VENCIMIENTOS_LISTAR")
	  @PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or hasRole('ROLE_VENCIMIENTOS_LISTAR'))")
	  @RequestMapping(value = "/vencimientos/empresa/{empresa}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity< Map<String, List<?>>> getVencimientos( @PathVariable String empresa ) {
		  
		 Map<String, List<?>> vencimientos = new HashMap<String, List<?>>();		 
		 vencimientos.put("vencimientos", vencimientoService.getVencimientos(empresa) );
		 vencimientos.put("tiposVencimientos", vencimientoService.getTiposVencimientos() );		  
		  
	     return new ResponseEntity<>(vencimientos, HttpStatus.OK);
	  }	
	  
	  
	  @RequestMapping(value = "/vencimientos", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<Vencimiento> createVencimiento(@Valid @RequestBody Vencimiento vencimiento ) throws Exception {	        
	     return new ResponseEntity<>( vencimientoService.saveVencimiento( vencimiento ), HttpStatus.CREATED );        
	  }
	
	  @RequestMapping(value = "/vencimientos/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<Object> updateVehiculo(@PathVariable Long id, @Valid @RequestBody Vencimiento vencimiento  ) throws Exception {
		  	vencimientoService.updateVencimiento( id, vencimiento );
	        return new ResponseEntity<>( HttpStatus.NO_CONTENT );        
	  } 
	   
	  @RequestMapping(value = "/vencimientos/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<Object> deleteVehiculo(@PathVariable Long id) {
		  	vencimientoService.deleteVencimiento(id);
	        return new ResponseEntity<>( HttpStatus.NO_CONTENT );
	  }
	    

}
