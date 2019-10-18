package com.nuebus.controllers;


import com.nuebus.annotations.Descripcion;
import com.nuebus.annotations.DescripcionClase;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Usuario
 */

@DescripcionClase(value="Incidencias")
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
    


    @Descripcion(value="Gestionar Incidencias",permission="ROLE_INCIDENCIAS_LISTAR")
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_INCIDENCIAS_LISTAR'))")
    @GetMapping(  "/incidencias/empresa/{idEmpresa}" ) 
    @ResponseStatus( HttpStatus.OK )
    public Page<IncidenciaDTO> findAllIncidencias(@PathVariable String idEmpresa , Pageable pageable, HttpServletRequest req) {    
             
        Page<IncidenciaDTO> page = incidenciaService.findIncidenciasByEmpresa( pageable,idEmpresa );
        return page;
    }
    
    
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_INCIDENCIAS_LISTAR'))")
    @GetMapping( "/incidencias/{id}" )   
    @ResponseStatus( HttpStatus.OK )
    public IncidenciaDTO getIncidencia(@PathVariable Long id, HttpServletRequest req) {
        Incidencia incidencia = incidenciaService.getIncidencia(id);            
        return  incidenciaMapper.toDTO(incidencia);
    }

    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_INCIDENCIAS_LISTAR'))")
    @PostMapping(  "/incidencias" )  
    @ResponseStatus( HttpStatus.CREATED )
    public IncidenciaDTO createIncidencia(@Valid @RequestBody IncidenciaDTO incidenciaDTO) throws Exception{           
        Incidencia incidencia = incidenciaService.saveIncidencia( incidenciaMapper.toEntity(incidenciaDTO) );
        return incidenciaMapper.toDTO( incidencia );                   
    }

    @GetMapping(  "/checkCodigoIncidencia/empresa/{empresa}/tipo/{tipo}/codigo/{codigo}" )   
    @ResponseStatus( HttpStatus.OK  )
    public boolean  checkCodigoIncidencia( @PathVariable String empresa, @PathVariable int tipo, 
                                                         @PathVariable String codigo ) throws Exception{                         
        return incidenciaService.existeCodigo( empresa, tipo, codigo );                           
    }
    
    
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_INCIDENCIAS_LISTAR'))")
    @PutMapping( "/incidencias/{id}" )    
    @ResponseStatus( HttpStatus.CREATED )
    public IncidenciaDTO updateIncidencia( @PathVariable Long id, @Valid @RequestBody IncidenciaDTO incidenciaDTO ) throws Exception{         
        Incidencia incidencia = incidenciaService.updateIncidencia(id, incidenciaDTO); 
        return incidenciaMapper.toDTO( incidencia );
    }
    
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_INCIDENCIAS_LISTAR'))")
    @DeleteMapping( "/incidencias/{id}" )   
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void deleteIncidencia(@PathVariable Long id) {
        incidenciaService.deleteIncidencia(id);          
    }
    
    /*@PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_INCIDENCIAS_LISTAR'))")
    @GetMapping(  "/empresa/{idEmpresa}/tipo/{idTipo}/incidencias" )
    @ResponseStatus( HttpStatus.OK )
    public List<ComboDTO> findIncidenciasByEmpresayTipo( @PathVariable String idEmpresa , @PathVariable int idTipo  ) {             
        List<ComboDTO> lista = incidenciaService.findIncidenciasByEmpresayTipo( idEmpresa, idTipo );
        return lista;
    }*/    
    
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_INCIDENCIAS_LISTAR'))")
    @GetMapping(  "/empresa/{idEmpresa}/tipo/{idTipo}/incidencias" )
    @ResponseStatus( HttpStatus.OK )
    public List<Incidencia> findIncidenciasByEmpresayTipo( @PathVariable String idEmpresa , @PathVariable int idTipo  ) {             
        List<Incidencia> lista = incidenciaService.findIncidenciasByEmpresayTipo( idEmpresa, idTipo );
        return lista;
    }    
    
}
