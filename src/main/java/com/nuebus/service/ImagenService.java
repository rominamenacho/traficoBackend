package com.nuebus.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import org.springframework.core.io.Resource;

public interface ImagenService {
	
	String crearImagen( MultipartFile archivo, String directorioUploads )throws IOException;	
	boolean eliminarImagen( String nombreArchivo, String directorioUploads ); 
	Resource verImagen( String nombreArchivo, String directorioUploads );

}
