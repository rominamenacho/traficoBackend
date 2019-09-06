package com.nuebus.controllers;

import com.nuebus.annotations.Descripcion;
import com.nuebus.annotations.DescripcionClase;
import com.nuebus.dto.CarnetDTO;
import com.nuebus.dto.ChoferDTO;
import com.nuebus.dto.ChoferIncidenciaDTO;
import com.nuebus.dto.ListaCarnetDTO;
import com.nuebus.dto.ListaChoferIncidencia;
import com.nuebus.erroresJson.WrapCarnetError;
import com.nuebus.erroresJson.WrapChoferIncidenciaError;
import com.nuebus.model.Chofer;
import com.nuebus.model.ChoferPK;
import com.nuebus.repository.ChoferRepository;
import com.nuebus.repository.ImagenChoferRepository;
import com.nuebus.service.ChoferService;
import com.nuebus.service.VencimientoService;
import com.nuebus.utilidades.Utilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    @GetMapping( "/choferes/empresa/{cho_emp_codigo}" )  
    @ResponseStatus( HttpStatus.OK )
    public Page<ChoferDTO> findAllPersonal( Pageable pageable, @PathVariable String cho_emp_codigo,
    		 												@RequestParam(required = false) String busqueda ) {       
        Page<ChoferDTO> page = null;
        
        if( busqueda != null ) {
        	page = choferService.findPersonalByBusquedaAndEmpresa( busqueda, cho_emp_codigo, pageable );        	     
        }else {
        	page = choferService.findPersonalByEmpresa(pageable, cho_emp_codigo);        	
        }       
        return page;
    }
      
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_CHOFERES_LISTAR'))")
    @PostMapping( "/choferes" ) 
    @ResponseStatus( HttpStatus.CREATED )
    public ChoferDTO createChofer(@Valid @RequestBody ChoferDTO choferDTO) throws Exception {        
        ChoferDTO choferCreated =  choferService.saveChofer(choferDTO);
        return choferCreated;
    }
    
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_CHOFERES_LISTAR'))")
    @PutMapping( "/choferes/empresa/{cho_emp_codigo}/codigo/{cho_codigo}" )
    @ResponseStatus( HttpStatus.CREATED )
    public ChoferDTO  updateChofer(@PathVariable String cho_emp_codigo, @PathVariable Long cho_codigo, @Valid @RequestBody ChoferDTO choferDTO) throws Exception {        
        ChoferDTO choferUpdated = choferService.updateChofer( cho_emp_codigo,  cho_codigo, choferDTO );
        return choferUpdated;
    }
    
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_CHOFERES_LISTAR'))")
    @DeleteMapping( "/choferes/empresa/{cho_emp_codigo}/codigo/{cho_codigo}" )
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void  deleteChofer(@PathVariable String cho_emp_codigo, @PathVariable Long cho_codigo ) {
        choferService.deleteChofer( cho_emp_codigo, cho_codigo);        
    }
    
    
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_CHOFERES_LISTAR'))")
    @PutMapping( "/choferes/empresa/{cho_emp_codigo}/codigo/{cho_codigo}/incidencias" )    
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
    @GetMapping( "/choferes/empresa/{cho_emp_codigo}/codigo/{cho_codigo}/incidencias" )
    @ResponseStatus( HttpStatus.OK )
    public List<ChoferIncidenciaDTO> getIncidenciasByChofer( @PathVariable String cho_emp_codigo,  @PathVariable long cho_codigo ) {        
        List<ChoferIncidenciaDTO> incidencias = choferService.getIncidenciasByChofer( cho_emp_codigo,  cho_codigo );        
        return incidencias;
    }
    
    
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_CHOFERES_LISTAR'))")
    @PutMapping( "/choferes/empresa/{cho_emp_codigo}/codigo/{cho_codigo}/carnets" )    
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
    @GetMapping("/choferes/empresa/{cho_emp_codigo}/codigo/{cho_codigo}/carnets")
    @ResponseStatus( HttpStatus.OK )
    public List<CarnetDTO> getCarnetsByChofer( @PathVariable String cho_emp_codigo,  @PathVariable long cho_codigo ) {        
        List<CarnetDTO> carnets = choferService.getCarnetsByChofer( cho_emp_codigo,  cho_codigo );        
        return carnets;
    } 
    
    ///// Tratamiento de imagenes
    
  
  
    /*
    @RequestMapping(value = "/Image/{id:.+}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    public ResponseEntity getImage(@PathVariable("id")String id, HttpServletResponse response) { 
    	byte[] image = imageService.getImage(id); 
 
    	response.setContentType(MediaType.IMAGE_JPEG_VALUE); 
    	return ResponseEntity.ok(image);  
    }*/

    
  
    
    /*@GetMapping("img/choferes/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto( @PathVariable("nombreFoto")  String nombreFoto ){
    	    	
    	
    	Resource recurso = imagenService.verImagen(nombreFoto, directorioUploadsChoferes);
    	
    	
    	HttpHeaders cabeceras = new HttpHeaders();
    	cabeceras.add( HttpHeaders.CONTENT_DISPOSITION , "attachment;filename=\"" + recurso.getFilename() + "\"");
    	
    	return new ResponseEntity<Resource>( recurso, cabeceras, HttpStatus.OK );    	
    }*/
   
    
}
