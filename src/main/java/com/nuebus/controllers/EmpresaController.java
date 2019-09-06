package com.nuebus.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.nuebus.service.EmpresaService;


@RestController
@RequestMapping( value="api/")
public class EmpresaController {
	
	@Autowired 
	EmpresaService empresaService;	
	
	@RequestMapping( value="/empresas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Object > getEmpresa(){
		return new ResponseEntity<>( empresaService.getEmpresas() , HttpStatus.OK);
	}

}
