package com.nuebus.mvc;


import com.nuebus.dto.ComboDTO;
import com.nuebus.dto.IncidenciaDTO;
import com.nuebus.mapper.IncidenciaMapper;
import com.nuebus.model.Incidencia;
import com.nuebus.service.IncidenciaService;
import com.nuebus.utilidades.IAuthenticationFacade;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Usuario
 */

@RestController
@CrossOrigin
@RequestMapping(value = "api")
public class IncidenciaController {
    
    @Autowired
    private IAuthenticationFacade authenticationFacade;
    
    final static Logger LOG = LoggerFactory.getLogger(IncidenciaController.class);

    @Autowired
    IncidenciaService incidenciaService;
    @Autowired
    IncidenciaMapper incidenciaMapper;
    

    @RequestMapping(value = "/incidencias/empresa/{idEmpresa}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<IncidenciaDTO>> findAllIncidencias(@PathVariable String idEmpresa , Pageable pageable, HttpServletRequest req) {    
        
        /*Authentication authentication = authenticationFacade.getAuthentication();
        UserContext user = (UserContext)authentication.getPrincipal();*/      
        
        Page<IncidenciaDTO> page = incidenciaService.findIncidenciasByEmpresa( pageable,idEmpresa );
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @RequestMapping(value = "/incidencias/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getIncidencia(@PathVariable Long id, HttpServletRequest req) {
        Incidencia incidencia = incidenciaService.getIncidencia(id);            
        return new ResponseEntity<>(incidenciaMapper.toDTO(incidencia), HttpStatus.OK);
    }

    @RequestMapping(value = "/incidencias", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createIncidencia(@Valid @RequestBody IncidenciaDTO incidenciaDTO) throws Exception{                      
        Incidencia incidencia = incidenciaMapper.toEntity(incidenciaDTO);    
        incidenciaService.saveIncidencia( incidencia );
        return new ResponseEntity<>(HttpStatus.CREATED);              
    }

    @RequestMapping(value = "/checkCodigoIncidencia/empresa/{empresa}/tipo/{tipo}/codigo/{codigo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean  checkCodigoIncidencia( @PathVariable String empresa, @PathVariable int tipo, 
                                                         @PathVariable String codigo ) throws Exception{                         
        return incidenciaService.existeCodigo( empresa, tipo, codigo );                           
    }
    
    @RequestMapping(value = "/incidencias/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateIncidencia( @PathVariable Long id, @Valid @RequestBody IncidenciaDTO incidenciaDTO ) throws Exception{         
        incidenciaService.updateIncidencia(id, incidenciaDTO); 
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/incidencias/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteIncidencia(@PathVariable Long id) {
        incidenciaService.deleteIncidencia(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);        
    }
    
    
    @RequestMapping(value = "/empresa/{idEmpresa}/tipo/{idTipo}/incidencias", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComboDTO>> findIncidenciasByEmpresayTipo( @PathVariable String idEmpresa , @PathVariable int idTipo  ) {             
        List<ComboDTO> lista = incidenciaService.findIncidenciasByEmpresayTipo( idEmpresa, idTipo );
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }    
    
    
}
