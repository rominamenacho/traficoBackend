
package com.nuebus.mvc;

import com.nuebus.annotations.Descripcion;
import com.nuebus.annotations.DescripcionClase;
import com.nuebus.dto.ListaChoferPK;
import com.nuebus.dto.ViajeEspecialDTO;
import com.nuebus.erroresJson.WrapChoferPKError;
import com.nuebus.erroresJson.WrapChoferPKError.ChoferPKError;
import com.nuebus.model.ChoferPK;
import com.nuebus.model.VehiculoPK;
import com.nuebus.service.ChoferService;
import com.nuebus.service.EscalaService;
import com.nuebus.service.ProvinciaService;
import com.nuebus.service.VehiculoService;
import com.nuebus.service.ViajeEspecialService;
import com.nuebus.utilidades.Utilities;
import com.nuebus.vistas.combos.ChoferesPKDet;
import com.nuebus.vistas.combos.ComboChoferes;
import com.nuebus.vistas.combos.ComboStr;
import com.nuebus.vistas.combos.ComboVehiculo;
import com.nuebus.vistas.combos.VehiculoPKDet;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

@DescripcionClase(value="Viajes Especiales")
@RestController
@CrossOrigin
@RequestMapping(value = "api")
public class ViajeEspecialController {

	@Autowired
    private EscalaService escalaService;
    @Autowired
    ProvinciaService provinciaService;
    @Autowired
    ViajeEspecialService viajeEspecialService;     
    @Autowired
    VehiculoService vehiculoService;
    @Autowired
    ChoferService choferService;

               
    @RequestMapping(value = "/ViajesEspeciales/provincia/{escProCodigo}/escalas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComboStr>> findEscalasByProvincia( @PathVariable int escProCodigo ) {       
        List<ComboStr> lista = escalaService.findEscalasByProvincia(escProCodigo );
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

         
    @RequestMapping(value = "/ViajesEspeciales/provincias", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComboStr>> findEscalasByProvincia( ) {       
        List<ComboStr> lista = provinciaService.findAllProvincias();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @Descripcion(value="Gestionar Viajes Especiales",permission="ROLE_VIAJES_ESPECIALES_LISTAR")
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_VIAJES_ESPECIALES_LISTAR'))")
    @RequestMapping(value = "/ViajesEspeciales", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void saveViajeEspecial(  @RequestBody ViajeEspecialDTO  viajeEspecial ) throws Exception {       
        viajeEspecialService.saveViajeEspecial(viajeEspecial);        
    }

    @RequestMapping(value = "/ViajesEspeciales/empresa/{empCodigo}/vehiculos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ComboStr> findVehiculoByEmpresa( @PathVariable String empCodigo ) {       
        return vehiculoService.vehiculosByEmpresa( empCodigo );
    }

    
    @RequestMapping(value = "/ViajesEspeciales/empresa/{empCodigo}/fechaInicio/{inicio}/fechaFin/{fin}/viajes",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ViajeEspecialDTO>> findViajesByFecha( @PathVariable String empCodigo,
            @PathVariable("inicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date inicio,
            @PathVariable("fin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin,
            Pageable pageable ) {

        Page<ViajeEspecialDTO> page  = viajeEspecialService.findViajesByEmpresaByFechas( pageable, empCodigo, inicio, fin );

        return new ResponseEntity<>(page, HttpStatus.OK);

    }

    
    @RequestMapping(value = "/ViajesEspeciales/{idViaje}/vehiculo",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void setVehiculo( @PathVariable long idViaje, @RequestBody VehiculoPK   vehiculoPK )  throws Exception{

        viajeEspecialService.setVehiculo( idViaje, vehiculoPK );    

    
    }

    @RequestMapping(value = "/ViajesEspeciales/empresa/{empCodigo}/choferes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComboChoferes>> findChoferesByEmpresa( @PathVariable String empCodigo ) {        
        //this.comboEstados.push({codigo:0, descripcion:'HABILITADO'});
        //this.comboEstados.push({codigo:1, descripcion:'DESHABILITADO'});        
        final int HABILITADO = 0;
        final int DESHABILITADO = 1;

        return new ResponseEntity<>( choferService.getPersonal(empCodigo , HABILITADO, 0 ), HttpStatus.OK ); 
    }
    
    @RequestMapping(value = "/ViajesEspeciales/empresa/{empCodigo}/auxiliares", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComboChoferes>> findAuxiliaresByEmpresa( @PathVariable String empCodigo ) {        
        //this.comboEstados.push({codigo:0, descripcion:'HABILITADO'});
        //this.comboEstados.push({codigo:1, descripcion:'DESHABILITADO'});        
        final int HABILITADO = 0;
        final int DESHABILITADO = 1;

        return new ResponseEntity<>( choferService.getPersonal(empCodigo , HABILITADO,1 ), HttpStatus.OK ); 
    }

    /*este*/
    @RequestMapping(value = "/ViajesEspeciales/{idViaje}/choferes",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> setChoferes( @PathVariable long idViaje, @Valid @RequestBody ListaChoferPK listaChoferesPK, BindingResult result ) throws Exception {

        if( result.hasErrors() ){
            return new ResponseEntity<>(validarChoferes(listaChoferesPK.getChoferesPK()), HttpStatus.CONFLICT);
        }

        viajeEspecialService.setChoferes(idViaje, listaChoferesPK.getChoferesPK());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    /**este*/
    @RequestMapping(value = "/ViajesEspeciales/{idViaje}/auxiliares",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> setAuxiliares( @PathVariable long idViaje, @Valid @RequestBody ListaChoferPK listaChoferesPK, BindingResult result ) throws Exception {

        if( result.hasErrors() ){
            return new ResponseEntity<>(validarChoferes(listaChoferesPK.getChoferesPK()), HttpStatus.CONFLICT);
        }

        viajeEspecialService.setAuxiliares(idViaje, listaChoferesPK.getChoferesPK());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private WrapChoferPKError validarChoferes( List<ChoferPK> choferesPK ){        

        WrapChoferPKError errores = new WrapChoferPKError();
        for( ChoferPK choPK: choferesPK ){
            errores.getChoferes().add( (WrapChoferPKError.ChoferPKError)Utilities.validarEntityError( choPK, (new WrapChoferPKError()).new ChoferPKError() ) );
        }
        return errores;
    }

    @RequestMapping(value = "/ViajesEspeciales/{idViaje}/comentarios",
            method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> setComentarios( @PathVariable long idViaje, @RequestBody ViajeEspecialDTO  viajeEspecial ) throws Exception {   

        viajeEspecialService.updateChofer(  idViaje, viajeEspecial );        
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    
    @RequestMapping(value = "/ViajesEspeciales/{idViaje}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void saveViajeEspecial(  @PathVariable long idViaje ) {       
        viajeEspecialService.deleteViajeEspecial(idViaje);        
    }

    @RequestMapping(value = "/ViajesEspeciales/{idViaje}/choferes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ChoferesPKDet>> findChoferesByViaje( @PathVariable long idViaje) {       
        return new ResponseEntity<>( viajeEspecialService.finChoferesByViaje( idViaje ), HttpStatus.OK ); 
    }
    
    @RequestMapping(value = "/ViajesEspeciales/{idViaje}/auxiliares", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ChoferesPKDet>> findAuxiliaresByViaje( @PathVariable long idViaje) {       
        return new ResponseEntity<>( viajeEspecialService.findAuxiliaresByViaje( idViaje ), HttpStatus.OK ); 
    }

    
    @RequestMapping(value = "/ViajesEspeciales/{idViaje}/choferesDisp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComboChoferes>> findChoferesByEmpresa( @PathVariable long idViaje ) {        

        return new ResponseEntity<>( viajeEspecialService.findChoferesLibreByViaje(idViaje), HttpStatus.OK ); 

    }
    
    @RequestMapping(value = "/ViajesEspeciales/{idViaje}/auxiliaresDisp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComboChoferes>> findAuxiliaresByEmpresa( @PathVariable long idViaje ) {        

        return new ResponseEntity<>( viajeEspecialService.findAuxiliaresLibreByViaje(idViaje), HttpStatus.OK ); 

    }

    @RequestMapping(value = "/ViajesEspeciales/{idViaje}/vehiculosDisp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<ComboVehiculo>> findVehiculosLibresByViaje( @PathVariable long idViaje ){

        return new ResponseEntity<>( viajeEspecialService.findVehiculosLibresByViaje(idViaje), HttpStatus.OK ); 

    }

    @RequestMapping(value = "/ViajesEspeciales/{idViaje}/vehiculos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<ComboVehiculo>> findVehiculosByEmpresas( @PathVariable long idViaje ){

        return new ResponseEntity<>( viajeEspecialService.findVehiculosByEmpresa(idViaje), HttpStatus.OK ); 

    }

    @RequestMapping(value = "/ViajesEspeciales/{idViaje}/vehiculo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<VehiculoPKDet> findVehiculoByViaje( @PathVariable long idViaje ){

        return new ResponseEntity<>( viajeEspecialService.findVehiculoByViaje(idViaje), HttpStatus.OK ); 

    }

}



/*

if( result.hasErrors() ){
            for ( FieldError fieldError: result.getFieldErrors() ) {
                System.out.println( "fieldError.getArguments() " + fieldError.getArguments() );
                System.out.println( "fieldError.getCode() " + fieldError.getCode() );
                System.out.println( "fieldError.getCodes() " + fieldError.getCodes() );
                System.out.println( "fieldError.getDefaultMessage() " + fieldError.getDefaultMessage() );
                System.out.println( "fieldError.getField() " + fieldError.getField() );
                System.out.println( "fieldError.getObjectName() " + fieldError.getObjectName() );
                System.out.println( "fieldError.getRejectedValue() " + fieldError.getRejectedValue()  );
                System.out.println( "fieldError.toString() " + fieldError.toString()  );
                System.out.println( "fieldError.hashCode()  " + fieldError.hashCode()  );
            }

            System.out.println( "result.getNestedPath() " + result.getNestedPath() );
            
            //result.getRawFieldValue(string)
        }        
        //viajeEspecialService.setChoferes( idViaje,  choferesPK );     

*/
