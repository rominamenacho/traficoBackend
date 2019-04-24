package com.nuebus.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nuebus.service.ImagenService;

@Service
public class ImagenServiceImpl implements ImagenService {
	
	@Value(value = "classpath:img/no-img.jpg")
	private Resource noImg;
	
	
	private final Logger log = LoggerFactory.getLogger( ImagenServiceImpl.class );

	@Override
	public String crearImagen(MultipartFile archivo, String directorioUploads) throws IOException {
		
		Path base =  Paths.get( directorioUploads ).toAbsolutePath();
		File fileBase = base.toFile();
		
		if( !fileBase.exists() ) {			
			fileBase.mkdirs();
	    }		
		String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
		Path rutaArchivo = base.resolve(nombreArchivo).toAbsolutePath();		   			
		
		Files.copy( archivo.getInputStream() , rutaArchivo);
		
		log.info("Subida " + rutaArchivo.toString() );
		
		return nombreArchivo;
	}

	@Override
	public boolean eliminarImagen( String nombreArchivo, String directorioUploads) {
		
		boolean retorno = false;
		
		if ( nombreArchivo != null && nombreArchivo.length() > 0 ) {
			Path ruta = Paths.get( directorioUploads ).resolve(nombreArchivo).toAbsolutePath();
			log.info("delete " + ruta.toString() );
			File file = ruta.toFile();
			if( file.exists() && file.canRead() ) {
				retorno = file.delete();
			}else {
				log.error("Error delete imagen " + ruta.toString() );
			}    			
		}		
		
		return retorno;
	}

	@Override
	public Resource verImagen( String nombreArchivo, String directorioUploads ) {
		Path rutaFoto = Paths.get( directorioUploads ).resolve(nombreArchivo).toAbsolutePath();
    	Resource recurso = null;
    	
    	log.info( "view " + rutaFoto.toString() );
    	
    	try {
			recurso = new UrlResource( rutaFoto.toUri() );
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	if( !recurso.exists()  && !recurso.isReadable() ) {	    		
    		if( !noImg.exists()  && !noImg.isReadable() ) {
    			throw new RuntimeException( "La imagen por defecto no se encuentra");
    		}else {	    			
    			recurso = noImg;	    			
    		}
    	}
    	
    	return recurso;
	}

}
