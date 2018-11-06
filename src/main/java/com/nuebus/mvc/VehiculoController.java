package com.nuebus.mvc;

import com.nuebus.dto.ListaVehiculoIncidenciasDTO;
import com.nuebus.dto.VehiculoDTO;
import com.nuebus.dto.VehiculoIncidenciaDTO;
import com.nuebus.dto.VehiculoOpDTO;
import com.nuebus.erroresJson.WrapVehiculoIncidenciaError;
import com.nuebus.service.MapaAsientoService;
import com.nuebus.service.VehiculoService;
import com.nuebus.utilidades.IAuthenticationFacade;
import com.nuebus.utilidades.Utilities;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
@RequestMapping(value = "api")
public class VehiculoController {
    
    @Autowired
    private IAuthenticationFacade authenticationFacade;
    
    final static Logger LOG = LoggerFactory.getLogger(VehiculoController.class);
    
    @Inject 
    VehiculoService vehiculoService;
    @Inject
    MapaAsientoService mapaAsientoService;
    
    @RequestMapping(value = "/vehiculos/empresa/{vehEmpCodigo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<VehiculoDTO>> findAllVehiculos( Pageable pageable, @PathVariable String vehEmpCodigo ) {
               
        /*Authentication authentication = authenticationFacade.getAuthentication();        
        UserContext user = (UserContext)authentication.getPrincipal();        
        Page<VehiculoDTO> page = vehiculoService.findVehiculosByEmpresa( pageable, user.getEmpresa() );
        return new ResponseEntity<>(page, HttpStatus.OK);*/
        
        
        Page<VehiculoDTO> page = vehiculoService.findVehiculosByEmpresa( pageable, vehEmpCodigo );
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    

    @RequestMapping(value = "/vehiculos", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createVehiculo(@Valid @RequestBody VehiculoDTO vehiculoDTO) throws Exception {
        vehiculoService.saveVehiculo(vehiculoDTO);
        return new ResponseEntity<>( HttpStatus.CREATED );        
    }

    @RequestMapping(value = "/vehiculos/empresa/{vehEmpCodigo}/interno/{vehInterno}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateVehiculo( @PathVariable String vehEmpCodigo,  @PathVariable int vehInterno, @Valid @RequestBody VehiculoDTO vehiculoDTO) throws Exception {
        vehiculoService.updateVehiculo(vehiculoDTO);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT );        
    }  
    
    @RequestMapping(value = "/vehiculos/empresa/{vehEmpCodigo}/interno/{vehInterno}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteVehiculo(@PathVariable String vehEmpCodigo,  @PathVariable int vehInterno) {
        vehiculoService.deleteVehiculo(vehEmpCodigo, vehInterno);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }
    
    
    @RequestMapping(value = "/empresa/{vehEmpCodigo}/vehiculosCb", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehiculoOpDTO> getOpcionesVehiculos(Pageable pageable, @PathVariable String vehEmpCodigo) {
                
        /*Authentication authentication = authenticationFacade.getAuthentication();        
        UserContext user = (UserContext)authentication.getPrincipal();        
        VehiculoOpDTO opcionesVeh = new VehiculoOpDTO();                
        opcionesVeh.setComboMapas(mapaAsientoService.findAllMapaAsiento( user.getEmpresa()));                
        return new ResponseEntity<>(opcionesVeh, HttpStatus.OK);*/        
        
        VehiculoOpDTO opcionesVeh = new VehiculoOpDTO();                
        opcionesVeh.setComboMapas(mapaAsientoService.findAllMapaAsiento( vehEmpCodigo ));                
        return new ResponseEntity<>(opcionesVeh, HttpStatus.OK);
        
        
        
    }
    
    @RequestMapping(value = "/vehiculos/empresa/{vehEmpCodigo}/interno/{vehInterno}/incidencias", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehiculoIncidenciaDTO>> getIncidenciasByVehiculo(@PathVariable String vehEmpCodigo,  @PathVariable int vehInterno) {        
        List<VehiculoIncidenciaDTO> incidencias = vehiculoService.getIncidenciasByVehiculo( vehEmpCodigo,  vehInterno );        
        return new ResponseEntity<>(incidencias, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/vehiculos/empresa/{vehEmpCodigo}/interno/{vehInterno}/incidencias", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
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
