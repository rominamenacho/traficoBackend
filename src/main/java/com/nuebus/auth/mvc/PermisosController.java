package com.nuebus.auth.mvc;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import com.nuebus.annotations.Descripcion;
import com.nuebus.annotations.DescripcionClase;
import com.nuebus.annotations.NombreClase;
import com.nuebus.annotations.PermisoEnVista;
import com.nuebus.auth.service.GroupService;
import com.nuebus.dto.GroupDTO;
import com.nuebus.dto.WraperModulo;
import com.nuebus.excepciones.ResourceNotFoundException;
import com.nuebus.mapper.GroupMapper;
import com.nuebus.mapper.UserMapper;
import com.nuebus.model.Group;
import com.nuebus.dto.WraperModulo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@DescripcionClase(value="Permisos")
@RestController
@RequestMapping("api")
public class PermisosController {
	
    final static org.slf4j.Logger log =LoggerFactory.getLogger(PermisosController.class);
 
    private RequestMappingHandlerMapping handlerMapping;
    
    /*@Autowired
    private PermisoService permisoService;*/
     
    @Autowired
    private GroupService groupService;   
   
    @Autowired
    GroupMapper groupMapper;
        
    @Autowired
    public void setRequestHandlerMapping(RequestMappingHandlerMapping handlerMapping){
        this.handlerMapping=handlerMapping;
    }       

    
    @Descripcion(value="Gestionar Permisos",permission="ROLE_PERMISOS_LISTAR")
    @RequestMapping(value="/permisos/listar",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or hasRole('ROLE_PERMISOS_LISTAR'))")
    public List<WraperModulo> listarPermisos(){  
      
        Map<String,List<PermisoEnVista>> controllers=new LinkedHashMap<String,List<PermisoEnVista>>();
        Map<RequestMappingInfo, HandlerMethod> p=this.handlerMapping.getHandlerMethods();
        for(Map.Entry<RequestMappingInfo, HandlerMethod> entry:p.entrySet()) {
            // Ahora para cada metodo averiguo el nombre del controller, del 
            // metodo y el tipo de request.
            NombreClase nombreClase=new NombreClase();
            nombreClase.setNombreClase( entry.getValue().getMethod().getDeclaringClass().getName() ) ;
            DescripcionClase descClase=entry.getValue().getMethod().getDeclaringClass().getAnnotation(DescripcionClase.class);
            if(descClase!=null){
                nombreClase.setDescripcionClase(descClase.value());
            } else {
                nombreClase.setDescripcionClase(nombreClase.getNombreClase());
            }           
           
            if(!controllers.containsKey(nombreClase.getDescripcionClase())){              
                List<PermisoEnVista> metodos=new LinkedList<PermisoEnVista>();               
                controllers.put( nombreClase.getDescripcionClase() , metodos);
            }          
            Descripcion descMetodo=entry.getValue().getMethod().getAnnotation(Descripcion.class);
            if(descMetodo!=null) {
                PermisoEnVista permiso=new PermisoEnVista();
                permiso.setDescripcionPermiso(descMetodo.value());
                permiso.setAuthority( descMetodo.permission() );
                controllers.get( nombreClase.getDescripcionClase()  ).add(permiso);
            }
        }
        
        
        List<WraperModulo> modulos = new ArrayList();
        WraperModulo unModulo;
        
        for( String clave: controllers.keySet() ){
            unModulo = new WraperModulo();
            unModulo.setModulo(clave);
            unModulo.setPermisos(controllers.get(clave));
            modulos.add(unModulo);
        }      
      
        return modulos;
    }  
    
    
    @Descripcion(value="Modificar permisos",permission="ROLE_PERMISOS_MODIFICAR")
    @RequestMapping(value="/permisos/grupo/{idGrupo}/permiso/{idPermiso}/grantrevoke",method=RequestMethod.PUT)
    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or hasRole('ROLE_PERMISOS_MODIFICAR'))")
    public ResponseEntity< Object > modificarPermiso( @PathVariable Long idGrupo,  @PathVariable String idPermiso){       
        boolean retorno = groupService.grantOrRevokePermission(idGrupo, idPermiso);
        return new ResponseEntity<>( retorno, HttpStatus.OK); 
    }
    
    

    @Descripcion(value="Gestionar Grupos",permission="ROLE_GRUPOS_LISTAR")
    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or hasRole('ROLE_GRUPOS_LISTAR'))")
    @RequestMapping(value="/permisos/grupos/{empresa}", method=RequestMethod.GET)
    public Page<GroupDTO> getGrupos( @PathVariable("empresa") String empresa, Pageable pageable ){       
        return groupService.findAllByEmpresa( empresa, pageable).map( group -> groupMapper.toDTO(group) );
    	//return groupService.findFetchWithRoles( pageable).map( group -> groupMapper.toDTO(group) ); 
    }
    
    @RequestMapping(value="/permisos/grupo", method=RequestMethod.POST)
    public ResponseEntity< Object > saveGrupo( @Valid @RequestBody GroupDTO groupDTO ){
        groupService.save(groupDTO);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }
    
     
    @RequestMapping(value="/permisos/grupo/{id}", method=RequestMethod.PUT)
    public ResponseEntity< Object > updateGrupo( @PathVariable Long id, @Valid @RequestBody GroupDTO groupDTO ){
        groupService.update(id, groupDTO);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }    
   
    
    @RequestMapping(value="/permisos/grupos/{id}", method=RequestMethod.DELETE)
    public ResponseEntity< Object > deleteGrupo( @PathVariable Long id ){
       groupService.delete(id);
       return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
   
    
}
