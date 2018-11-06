package com.nuebus.mvc;

import com.nuebus.dto.CarnetDTO;
import com.nuebus.dto.ChoferDTO;
import com.nuebus.dto.ChoferIncidenciaDTO;
import com.nuebus.dto.ListaCarnetDTO;
import com.nuebus.dto.ListaChoferIncidencia;
import com.nuebus.erroresJson.WrapCarnetError;
import com.nuebus.erroresJson.WrapChoferIncidenciaError;
import com.nuebus.service.ChoferService;
import com.nuebus.utilidades.Utilities;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ChoferController { 
    
    final static Logger LOG = LoggerFactory.getLogger(ChoferController.class);
    @Inject
    ChoferService choferService;
        
    /*@RequestMapping(value = "/chofer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ChoferDTO>> findAllChofer(Pageable pageable, HttpServletRequest req, JwtAuthenticationToken token) {
        //Page<ChoferDTO> page = choferService.findChoferes(pageable);
        UserContext user =  (UserContext) token.getPrincipal();
        Page<ChoferDTO> page = choferService.findChoferesByEmpresa(pageable, user.getEmpresa());
        return new ResponseEntity<>(page, HttpStatus.OK);
    }*/    
   
    @RequestMapping(value = "/choferes/empresa/{cho_emp_codigo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ChoferDTO>> findAllChofer(Pageable pageable, @PathVariable String cho_emp_codigo ) {       
        Page<ChoferDTO> page = choferService.findChoferesByEmpresa(pageable, cho_emp_codigo);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
      
    
    @RequestMapping(value = "/choferes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createChofer(@Valid @RequestBody ChoferDTO choferDTO) throws Exception {        
        choferService.saveChofer(choferDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);  
    }
    
    @RequestMapping(value = "/choferes/empresa/{cho_emp_codigo}/codigo/{cho_codigo}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateChofer(@PathVariable String cho_emp_codigo, @PathVariable Long cho_codigo, @Valid @RequestBody ChoferDTO choferDTO) throws Exception {        
        choferService.updateChofer( cho_emp_codigo,  cho_codigo, choferDTO );
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "/choferes/empresa/{cho_emp_codigo}/codigo/{cho_codigo}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>  deleteChofer(@PathVariable String cho_emp_codigo, @PathVariable Long cho_codigo ) {
        choferService.deleteChofer( cho_emp_codigo, cho_codigo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/choferes/empresa/{cho_emp_codigo}/codigo/{cho_codigo}/incidencias", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> salvarIncidenciasByChofer( @PathVariable String cho_emp_codigo, @PathVariable Long cho_codigo,
                                       @Valid @RequestBody ListaChoferIncidencia listaChoferIncidencia, BindingResult result )throws Exception { 
                        
        if( result.hasErrors() ){          
            return new ResponseEntity<>(validarChoferIncidencias(listaChoferIncidencia.getChoferIncidencias()), HttpStatus.BAD_REQUEST);                  
        }   
        
        choferService.salvarIncidenciasByChofer( cho_emp_codigo, cho_codigo, listaChoferIncidencia.getChoferIncidencias() );        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);         
    }    
    
     private WrapChoferIncidenciaError validarChoferIncidencias( Set<ChoferIncidenciaDTO> choferIncidencias ){        
        WrapChoferIncidenciaError errores = new WrapChoferIncidenciaError();       
        if( choferIncidencias != null){
            for( ChoferIncidenciaDTO choferInc: choferIncidencias ){
                errores.getChoferIncidencias().add( (WrapChoferIncidenciaError.ChoferIncidenciaError)Utilities.validarEntityError( choferInc, (new WrapChoferIncidenciaError()).new ChoferIncidenciaError() ) );
            }
        }else{
            errores.getErrores().add("La lista es nula");
        }                            
        
        return errores;
    }   
    
    
    @RequestMapping(value = "/choferes/empresa/{cho_emp_codigo}/codigo/{cho_codigo}/incidencias", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ChoferIncidenciaDTO>> getIncidenciasByChofer( @PathVariable String cho_emp_codigo,  @PathVariable long cho_codigo ) {        
        List<ChoferIncidenciaDTO> incidencias = choferService.getIncidenciasByChofer( cho_emp_codigo,  cho_codigo );        
        return new ResponseEntity<>(incidencias, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/choferes/empresa/{cho_emp_codigo}/codigo/{cho_codigo}/carnets", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> salvarCarnetsByChofer( @PathVariable String cho_emp_codigo, @PathVariable Long cho_codigo,
                                       @Valid @RequestBody ListaCarnetDTO listaCarnetsDTO, BindingResult result )throws Exception {        
        
        if( result.hasErrors() ){
            return new ResponseEntity<>(validarCarnets(listaCarnetsDTO.getCarnets() ), HttpStatus.BAD_REQUEST);         
        }        
        choferService.salvarCarnetsByChofer( cho_emp_codigo, cho_codigo, listaCarnetsDTO.getCarnets() );        
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 
    
     private WrapCarnetError validarCarnets( Set<CarnetDTO> CarnetDTO ){        
        
        WrapCarnetError errores = new WrapCarnetError();       
        for( CarnetDTO carnet: CarnetDTO ){
            errores.getCarnets().add( (WrapCarnetError.CarnetError)Utilities.validarEntityError( carnet, (new WrapCarnetError()).new CarnetError() ) );
        }                    
        return errores;
    }   
    
    
    @RequestMapping(value = "/choferes/empresa/{cho_emp_codigo}/codigo/{cho_codigo}/carnets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CarnetDTO>> getCarnetsByChofer( @PathVariable String cho_emp_codigo,  @PathVariable long cho_codigo ) {        
        List<CarnetDTO> carnets = choferService.getCarnetsByChofer( cho_emp_codigo,  cho_codigo );        
        return new ResponseEntity<>(carnets, HttpStatus.OK);
    }
    
}
