
package com.nuebus.mvc;

import com.nuebus.model.Linea;
import com.nuebus.service.LineaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
    
}
