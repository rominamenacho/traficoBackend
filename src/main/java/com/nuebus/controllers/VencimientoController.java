package com.nuebus.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.nuebus.annotations.Descripcion;
import com.nuebus.annotations.DescripcionClase;
import com.nuebus.dto.VencimientosChoferDTO;
import com.nuebus.dto.VencimientosVehiculoDTO;
import com.nuebus.model.Chofer;
import com.nuebus.model.Vehiculo;
import com.nuebus.model.Vencimiento;
import com.nuebus.service.VencimientoService;

import com.nuebus.vencimientos.choferes.VencimientoChoferService;
import com.nuebus.vencimientos.vehiculos.VencimientoVehiculoService;


@DescripcionClase(value="Vencimientos")
@RestController
@RequestMapping(value = "api")
public class VencimientoController {
	
    final static Logger LOG = LoggerFactory.getLogger(VencimientoController.class);

    @Autowired
    VencimientoService vencimientoService; 
    
    @Descripcion(value="Gestionar Conf Vencimientos",permission="ROLE_CONF_VENCIMIENTOS_LISTAR")
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_CONF_VENCIMIENTOS_LISTAR'))")
    @RequestMapping(value = "/vencimientos/empresa/{empresa}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity< Map<String, List<?>>> getVencimientos( @PathVariable String empresa ) {

           Map<String, List<?>> vencimientos = new HashMap<String, List<?>>();		 
           vencimientos.put("vencimientos", vencimientoService.getVencimientos(empresa) );
           vencimientos.put("tiposVencimientos", vencimientoService.getTiposVencimientos() );		  

       return new ResponseEntity<>(vencimientos, HttpStatus.OK);
    }	

    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_CONF_VENCIMIENTOS_LISTAR'))")
    @RequestMapping(value = "/vencimientos", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vencimiento> createVencimiento(@Valid @RequestBody Vencimiento vencimiento ) throws Exception {	        
       return new ResponseEntity<>( vencimientoService.saveVencimiento( vencimiento ), HttpStatus.CREATED );        
    }

    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_CONF_VENCIMIENTOS_LISTAR'))")
    @RequestMapping(value = "/vencimientos/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateVehiculo(@PathVariable Long id, @Valid @RequestBody Vencimiento vencimiento  ) throws Exception {
                  vencimientoService.updateVencimiento( id, vencimiento );
          return new ResponseEntity<>( HttpStatus.NO_CONTENT );        
    } 

    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_CONF_VENCIMIENTOS_LISTAR'))")
    @RequestMapping(value = "/vencimientos/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteVehiculo(@PathVariable Long id) {
                  vencimientoService.deleteVencimiento(id);
          return new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }


    @Descripcion(value="Listar Vencimientos",permission="ROLE_VENCIMIENTOS_LISTAR")
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_VENCIMIENTOS_LISTAR') or hasRole('ROLE_CHOFERES_LISTAR'))")
    @GetMapping(value = "/vencimientos/empresa/{cho_emp_codigo}/estado/{cho_estado}/choferes" )
    @ResponseStatus( code = HttpStatus.OK)
    public List<VencimientosChoferDTO> getChoferesConVencimientos( @PathVariable String cho_emp_codigo, @PathVariable int cho_estado ) {       

         List<VencimientosChoferDTO> vencimientosRespuesta = vencimientoService
                                                               .getChoferesConVencimientos( cho_emp_codigo, cho_estado );                
          return vencimientosRespuesta;
    }


    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_VENCIMIENTOS_LISTAR') or hasRole('ROLE_UNIDADES_LISTAR'))")
    @GetMapping("/vencimientos/empresa/{vehEmpCodigo}/estado/{vehEstado}/vehiculos" )
    @ResponseStatus( code = HttpStatus.OK)
    public List<VencimientosVehiculoDTO> getVehiculosConVencimientos(@PathVariable String vehEmpCodigo,  @PathVariable int vehEstado ) {       

          List<VencimientosVehiculoDTO> vencVehiculo
          = vencimientoService.getVehiculosConVencimientos( vehEmpCodigo, vehEstado);
          return  vencVehiculo;
    }
	    
    
    @GetMapping("/vencimientos/empresa/{empresa}/existenVencimientos" )
    @ResponseStatus( code = HttpStatus.OK)
    public boolean existenVencimientos(@PathVariable String empresa ) {
    	
          return  existenVencimientosHabilitados( empresa )
        		  && ( existenVencimientosVehiculos( empresa )
        				  ||  existenVencimientosChoferes( empresa ) );
    }
    
    private boolean existenVencimientosHabilitados( String empresa ) {
    	
    	List<Vencimiento> vencimientos = vencimientoService.getVencimientos(empresa)
				   .stream().filter( v -> v.getActivo() == true ).collect( Collectors.toList() );

    	return  vencimientos != null && !vencimientos.isEmpty();
    	
    }
    
    private boolean existenVencimientosVehiculos( String empresa ) {
    	
    	List<VencimientosVehiculoDTO> vencimientos = vencimientoService.getVehiculosConVencimientos(empresa ,
    																		Vehiculo.HABILITADO);				   
    	return  vencimientos != null && !vencimientos.isEmpty();    	
    }
    
    
    private boolean existenVencimientosChoferes( String empresa ) {
    	
    	   List<VencimientosChoferDTO> vencimientos = vencimientoService
                   .getChoferesConVencimientos( empresa, Chofer.HABILITADO );  		   
    	return  vencimientos != null && !vencimientos.isEmpty();    	
    }
    
    
   

}
