package com.nuebus.mvc;

import com.nuebus.dto.ListaVehiculoIncidenciasDTO;
import com.nuebus.dto.VehiculoDTO;
import com.nuebus.dto.VehiculoIncidenciaDTO;
import com.nuebus.dto.VehiculoOpDTO;
import com.nuebus.dto.VencimientosVehiculoDTO;
import com.nuebus.erroresJson.WrapVehiculoIncidenciaError;
import com.nuebus.model.Vehiculo;
import com.nuebus.service.MapaAsientoService;
import com.nuebus.service.VehiculoService;
import com.nuebus.utilidades.IAuthenticationFacade;
import com.nuebus.utilidades.Utilities;
import com.nuebus.vencimientos.VencimientosVehiculo;
import java.util.List;
import java.util.Set;
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
    
    @Autowired 
    VehiculoService vehiculoService;
    @Autowired
    MapaAsientoService mapaAsientoService;
    @Autowired VencimientosVehiculo vencimientosVehiculo;
    
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
    
    
    @RequestMapping(value = "/vehiculos/empresa/{vehEmpCodigo}/interno/{vehInterno}/checkExiste", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean checkExisteVehiculo( @PathVariable String vehEmpCodigo,  @PathVariable int vehInterno ) throws Exception {
        return vehiculoService.existeInterno( vehEmpCodigo, vehInterno );                
    }  
    
    
    
    @RequestMapping(value = "/empresa/{vehEmpCodigo}/vehiculosCb", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehiculoOpDTO> getOpcionesVehiculos(Pageable pageable, @PathVariable String vehEmpCodigo) {              
        
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

    @RequestMapping(value = "/vehiculos/empresa/{vehEmpCodigo}/estado/{vehEstado}/vencimientos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VencimientosVehiculoDTO>> getVehiculosConVencimientos(@PathVariable String vehEmpCodigo,  @PathVariable int vehEstado ) {        
        //List<VehiculoDTO> vehiculos = vehiculoService.getVehiculosConVencimientos( vehEmpCodigo, vehEstado );
    	List<VencimientosVehiculoDTO> vencVehiculo = vencimientosVehiculo.calcularAllVencimientosVehiculos( vehEmpCodigo, 
    																										vehEstado);
        return new ResponseEntity<>( vencVehiculo, HttpStatus.OK );
    }
    
}
