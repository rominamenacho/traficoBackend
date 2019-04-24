package com.nuebus.mvc;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nuebus.model.Chofer;
import com.nuebus.model.ChoferPK;
import com.nuebus.repository.ChoferRepository;
import com.nuebus.service.ChoferService;
import com.nuebus.service.ImagenService;

@RestController
public class ImagenController {
	
	@Value(value = "${directorioUploadsChoferes}")
	String directorioUploadsChoferes;
	
	
    @Autowired
	ChoferService choferService;	    
	@Autowired
	ChoferRepository choferRepository;	
	@Autowired
	ImagenService imagenService;
	
		
	private final Logger log = LoggerFactory.getLogger( ImagenController.class );
	
	
	
	 @RequestMapping(value="upload/choferes/{cho_emp_codigo}/{cho_codigo}" , method=RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<?> uploadChofer( @RequestParam("archivo") MultipartFile archivo,
	    			@PathVariable String cho_emp_codigo, @PathVariable long cho_codigo ) {   	

	    	
	    	Map<String, Object> response = new HashMap<>();
	    	String nombreArchivo = archivo != null ? archivo.getOriginalFilename() : " arcivo nulo";
	    	Chofer chofer = choferService.getChoferById( new ChoferPK( cho_emp_codigo,  cho_codigo ) );
	    	String fotoAnterior = chofer.getFoto();
	    	
	    	if( !archivo.isEmpty() ){	    		
	    		
	    		try {   			
	    			 
	    			String nombreFoto = imagenService.crearImagen(archivo, directorioUploadsChoferes);					
					chofer.setFoto(nombreFoto);
					choferRepository.save(chofer);				
					
				} catch (IOException e) {				
					e.printStackTrace();				
					response.put("error", e.getMessage().concat(":").concat(e.getCause().getMessage()));
					response.put("mensaje","Error al subir el archivo " + nombreArchivo );
					return new ResponseEntity<Map<String,Object>>( response, HttpStatus.INTERNAL_SERVER_ERROR );
				}	    		
	    		
	    		imagenService.eliminarImagen(fotoAnterior, directorioUploadsChoferes);  		
	    		
	    		response.put("mensaje","ha subido correctamente el archivo: " + nombreArchivo);
	    	}
	    	
	    	return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);  
	    }
	    
	    
	    @GetMapping("img/choferes/{nombreFoto:.+}")
	    public ResponseEntity<Resource> verFoto( @PathVariable("nombreFoto")  String nombreFoto ){
	    	    	
	    	
	    	Resource recurso = imagenService.verImagen(nombreFoto, directorioUploadsChoferes);
	    	
	    	
	    	HttpHeaders cabeceras = new HttpHeaders();
	    	cabeceras.add( HttpHeaders.CONTENT_DISPOSITION , "attachment;filename=\"" + recurso.getFilename() + "\"");
	    	
	    	return new ResponseEntity<Resource>( recurso, cabeceras, HttpStatus.OK );    	
	    }

}
