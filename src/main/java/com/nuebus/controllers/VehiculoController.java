package com.nuebus.controllers;

import com.nuebus.annotations.Descripcion;
import com.nuebus.annotations.DescripcionClase;
import com.nuebus.dto.ListaVehiculoIncidenciasDTO;
import com.nuebus.dto.VehiculoDTO;
import com.nuebus.dto.VehiculoIncidenciaDTO;
import com.nuebus.dto.VehiculoOpDTO;
import com.nuebus.erroresJson.WrapVehiculoIncidenciaError;
import com.nuebus.model.Vehiculo;
import com.nuebus.model.VehiculoPK;
import com.nuebus.service.MapaAsientoService;
import com.nuebus.service.VehiculoService;
import com.nuebus.utilidades.IAuthenticationFacade;
import com.nuebus.utilidades.Utilities;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Valeria
 */

@DescripcionClase(value="Unidades")
@RestController
@CrossOrigin
@RequestMapping(value = "api")
public class VehiculoController {
    
    @Autowired
    private IAuthenticationFacade authenticationFacade;
    
    final static Logger LOG = LoggerFactory.getLogger(VehiculoController.class);
    
    @Autowired 
    VehiculoService vehiculoService;
    @Autowired
    MapaAsientoService mapaAsientoService;
       
    

    @Descripcion(value="Gestionar Unidades",permission="ROLE_UNIDADES_LISTAR")
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_UNIDADES_LISTAR'))")
    @GetMapping( "/vehiculos/empresa/{vehEmpCodigo}" )
    @ResponseStatus( HttpStatus.OK)
    public Page<VehiculoDTO> findAllVehiculos( Pageable pageable, @PathVariable String vehEmpCodigo,
    		 								   @RequestParam(required = false) String busqueda) {
               
        /*Authentication authentication = authenticationFacade.getAuthentication();        
        UserContext user = (UserContext)authentication.getPrincipal();        
        Page<VehiculoDTO> page = vehiculoService.findVehiculosByEmpresa( pageable, user.getEmpresa() );
        return new ResponseEntity<>(page, HttpStatus.OK);*/    
    	
        Page<VehiculoDTO> page = null;
        if( busqueda != null ){
        	page = vehiculoService.findByEmpresaAndBusqueda( vehEmpCodigo, busqueda, pageable );
        }else {
        	page = vehiculoService.findAllVehiculosByEmpresa( pageable, vehEmpCodigo );
        }       
        return page;
    }

    
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_UNIDADES_LISTAR'))")
    @PostMapping( "/vehiculos" )
    @ResponseStatus( HttpStatus.CREATED )    
    public VehiculoDTO createVehiculo(@Valid @RequestBody VehiculoDTO vehiculoDTO) throws Exception {
        /*Vehiculo vehiculo = vehiculoService.saveVehiculo(vehiculoDTO);
        return vehiculo;*/        
    	Vehiculo vehiculo = vehiculoService.saveVehiculo(vehiculoDTO);
    	VehiculoDTO dto = vehiculoService.generarDTOYAddVencimientos(vehiculo);
        return dto;
    }

    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_UNIDADES_LISTAR'))")
    @PutMapping( "/vehiculos/empresa/{vehEmpCodigo}/interno/{vehInterno}" )
    @ResponseStatus( HttpStatus.CREATED )
    public VehiculoDTO  updateVehiculo( @PathVariable String vehEmpCodigo,  @PathVariable int vehInterno,
    												@Valid @RequestBody VehiculoDTO vehiculoDTO) throws Exception {
    	/*Vehiculo vehiculo = vehiculoService.updateVehiculo(vehiculoDTO);
        return vehiculo;*/        
    	Vehiculo vehiculo = vehiculoService.updateVehiculo(vehiculoDTO);
    	VehiculoDTO dto = vehiculoService.generarDTOYAddVencimientos(vehiculo);
        return dto;
    }  
    
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_UNIDADES_LISTAR'))")
    @DeleteMapping( "/vehiculos/empresa/{vehEmpCodigo}/interno/{vehInterno}" )
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void deleteVehiculo(@PathVariable String vehEmpCodigo,  @PathVariable int vehInterno) {
        vehiculoService.deleteVehiculo(vehEmpCodigo, vehInterno);        
    }
    
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_UNIDADES_LISTAR'))")
    @GetMapping( "/vehiculos/empresa/{vehEmpCodigo}/interno/{vehInterno}" )
    @ResponseStatus( HttpStatus.OK )
    public VehiculoDTO getVehiculo(@PathVariable String vehEmpCodigo,  @PathVariable int vehInterno) {    	
    	VehiculoPK vehiculoPK = new VehiculoPK( vehEmpCodigo, vehInterno );
    	VehiculoDTO vehiculoDTO = vehiculoService.getVehiculo( vehiculoPK );
    	return vehiculoDTO;
    }
    
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_UNIDADES_LISTAR'))")
    @GetMapping( "/vehiculos/empresa/{vehEmpCodigo}/interno/{vehInterno}/checkExiste" )
    @ResponseStatus( HttpStatus.OK)
    public boolean checkExisteVehiculo( @PathVariable String vehEmpCodigo,  @PathVariable int vehInterno ) throws Exception {
        return vehiculoService.existeInterno( vehEmpCodigo, vehInterno );                
    }  
    
    
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_UNIDADES_LISTAR'))")
    @GetMapping("/empresa/{vehEmpCodigo}/vehiculosCb")    
    @ResponseStatus( HttpStatus.OK)
    public VehiculoOpDTO getOpcionesVehiculos(Pageable pageable, @PathVariable String vehEmpCodigo) {   
        VehiculoOpDTO opcionesVeh = new VehiculoOpDTO();                
        opcionesVeh.setComboMapas(mapaAsientoService.findAllMapaAsiento( vehEmpCodigo ));                
        return opcionesVeh;       
    }
    
    
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_UNIDADES_LISTAR'))")
    @GetMapping( "/vehiculos/empresa/{vehEmpCodigo}/interno/{vehInterno}/incidencias" )   
    @ResponseStatus( HttpStatus.OK)
    public List<VehiculoIncidenciaDTO> getIncidenciasByVehiculo(@PathVariable String vehEmpCodigo, 
    																					@PathVariable int vehInterno) {        
        List<VehiculoIncidenciaDTO> incidencias = vehiculoService.getIncidenciasByVehiculo( vehEmpCodigo,  vehInterno );        
        return incidencias;
    }
    
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_UNIDADES_LISTAR'))")
    @PutMapping( "/vehiculos/empresa/{vehEmpCodigo}/interno/{vehInterno}/incidencias" )    
    public ResponseEntity<Object> salvarIncidenciasByVehiculo( @PathVariable String vehEmpCodigo,  @PathVariable int vehInterno, 
                            @Valid @RequestBody ListaVehiculoIncidenciasDTO  incidencias, BindingResult result ) {   
        
        
        if( result.hasErrors() ){
             return new ResponseEntity<>(validarIncidencias( incidencias.getIncidencias() ), HttpStatus.BAD_REQUEST);         
        }                   
        
        vehiculoService.salvarIncidenciasByVehiculo( vehEmpCodigo,  vehInterno, incidencias.getIncidencias() );  
        return new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }
    
    private WrapVehiculoIncidenciaError validarIncidencias( Set<VehiculoIncidenciaDTO> incidencias ){        
        
        WrapVehiculoIncidenciaError errores = new WrapVehiculoIncidenciaError();       
        for( VehiculoIncidenciaDTO incid: incidencias ){
            errores.getIncidencias().add( (WrapVehiculoIncidenciaError.VehiculoIncidenciaError)Utilities.validarEntityError( incid, (new WrapVehiculoIncidenciaError()).new VehiculoIncidenciaError() ) );
        }                    
        return errores;
    }  
 
}
