
package com.nuebus.mvc;

import com.nuebus.dto.EnlaceLineasDTO;
import com.nuebus.model.EnlaceLineas;
import com.nuebus.model.Linea;
import com.nuebus.service.LineaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(value="api")
public class LineaController {
    
    @Autowired
    LineaService lineaService;
    
    
    @RequestMapping( value="/empresa/{idEmpresa}/lineas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity< List<Linea>>  getLineas( @PathVariable String idEmpresa ){
        List<Linea>  lineas = lineaService.findAllLineas(idEmpresa);
        return new ResponseEntity<>(lineas, HttpStatus.OK);
    }
    
    @RequestMapping( value="/empresa/{idEmpresa}/enlaceLineas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<EnlaceLineas>> findAllEnlacesByEmpresa( @PathVariable String  idEmpresa, Pageable pageable){
        Page<EnlaceLineas> enlaces = lineaService.finAllEnlaceLineasByEmpresa(idEmpresa, pageable );
        return new ResponseEntity<>(  enlaces, HttpStatus.OK );
    }
    
    @RequestMapping( value="/enlaceLineas", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Object> saveEnlaceLineas( @RequestBody EnlaceLineasDTO enlaceDTO ){
       lineaService.saveEnlaceLineas(enlaceDTO);
       return new ResponseEntity<>( HttpStatus.CREATED );
    }
    
    @RequestMapping( value="/enlaceLineas/{idEnlace}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Object> deleteEnlaceLineas( @PathVariable Long idEnlace ){
        lineaService.deleteEnlaceLineas(idEnlace);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }
    
}
