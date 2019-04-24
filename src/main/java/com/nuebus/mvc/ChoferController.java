package com.nuebus.mvc;

import com.nuebus.annotations.Descripcion;
import com.nuebus.annotations.DescripcionClase;
import com.nuebus.dto.CarnetDTO;
import com.nuebus.dto.ChoferDTO;
import com.nuebus.dto.ChoferIncidenciaDTO;
import com.nuebus.dto.ListaCarnetDTO;
import com.nuebus.dto.ListaChoferIncidencia;
import com.nuebus.dto.VencimientosChoferDTO;
import com.nuebus.erroresJson.WrapCarnetError;
import com.nuebus.erroresJson.WrapChoferIncidenciaError;
import com.nuebus.model.Chofer;
import com.nuebus.model.ChoferPK;
import com.nuebus.repository.ChoferRepository;
import com.nuebus.service.ChoferService;
import com.nuebus.service.VencimientoService;
import com.nuebus.utilidades.Utilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Valeria
 */

@DescripcionClase(value="Choferes")
@RestController
@CrossOrigin
@RequestMapping(value = "api")
public class ChoferController { 
	
	private final Logger log = LoggerFactory.getLogger(ChoferController.class);    
    
    @Autowired
    ChoferService choferService;
    
    @Autowired
    ChoferRepository choferRepository; 
    
    @Autowired
    VencimientoService vencimientoService;   
    

    @Descripcion(value="Gestionar Personal",permission="ROLE_CHOFERES_LISTAR")
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_CHOFERES_LISTAR'))")
    @RequestMapping(value = "/choferes/empresa/{cho_emp_codigo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ChoferDTO>> findAllPersonal(Pageable pageable, @PathVariable String cho_emp_codigo ) {       
        Page<ChoferDTO> page = choferService.findPersonalByEmpresa(pageable, cho_emp_codigo);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
      
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_CHOFERES_LISTAR'))")
    @RequestMapping(value = "/choferes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createChofer(@Valid @RequestBody ChoferDTO choferDTO) throws Exception {        
        choferService.saveChofer(choferDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);  
    }
    
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_CHOFERES_LISTAR'))")
    @RequestMapping(value = "/choferes/empresa/{cho_emp_codigo}/codigo/{cho_codigo}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateChofer(@PathVariable String cho_emp_codigo, @PathVariable Long cho_codigo, @Valid @RequestBody ChoferDTO choferDTO) throws Exception {        
        choferService.updateChofer( cho_emp_codigo,  cho_codigo, choferDTO );
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_CHOFERES_LISTAR'))")
    @RequestMapping(value = "/choferes/empresa/{cho_emp_codigo}/codigo/{cho_codigo}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>  deleteChofer(@PathVariable String cho_emp_codigo, @PathVariable Long cho_codigo ) {
        choferService.deleteChofer( cho_emp_codigo, cho_codigo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_CHOFERES_LISTAR'))")
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
     
    
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_CHOFERES_LISTAR'))")
    @RequestMapping(value = "/choferes/empresa/{cho_emp_codigo}/codigo/{cho_codigo}/incidencias", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ChoferIncidenciaDTO>> getIncidenciasByChofer( @PathVariable String cho_emp_codigo,  @PathVariable long cho_codigo ) {        
        List<ChoferIncidenciaDTO> incidencias = choferService.getIncidenciasByChofer( cho_emp_codigo,  cho_codigo );        
        return new ResponseEntity<>(incidencias, HttpStatus.OK);
    }
    
    
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_CHOFERES_LISTAR'))")
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
    
    
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_CHOFERES_LISTAR'))")
    @RequestMapping(value = "/choferes/empresa/{cho_emp_codigo}/codigo/{cho_codigo}/carnets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CarnetDTO>> getCarnetsByChofer( @PathVariable String cho_emp_codigo,  @PathVariable long cho_codigo ) {        
        List<CarnetDTO> carnets = choferService.getCarnetsByChofer( cho_emp_codigo,  cho_codigo );        
        return new ResponseEntity<>(carnets, HttpStatus.OK);
    } 
    
    
   
    
}
