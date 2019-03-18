package com.nuebus.auth.mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
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
import com.nuebus.mapper.GroupMapper;
import com.nuebus.model.Empresa;
import com.nuebus.model.Role;
import com.nuebus.model.Usuario;
import com.nuebus.service.EmpresaService;
import com.nuebus.utilidades.IAuthenticationFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@DescripcionClase(value="Permisos")
@RestController
@RequestMapping("api")
public class PermisosController {
	
    final static org.slf4j.Logger log =LoggerFactory.getLogger(PermisosController.class);
 
    private RequestMappingHandlerMapping handlerMapping;
    
    @Autowired
	private IAuthenticationFacade authenticationFacade;       
     
    @Autowired
    private GroupService groupService;
    
    @Autowired 
	EmpresaService empresaService;
   
    @Autowired
    GroupMapper groupMapper;
        
    @Autowired
    public void setRequestHandlerMapping(RequestMappingHandlerMapping handlerMapping){
        this.handlerMapping=handlerMapping;
    }

       
    @RequestMapping(value="/permisos/listar",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_GRUPOS_LISTAR'))")
    public List<WraperModulo> listarPermisos( HttpServletRequest request  ){  
      
    	List<WraperModulo> modulos = listarPermisosBases();
        
        if ( !request.isUserInRole("ROLE_ADMIN") ) {  
        	/////////////Filtrar permisos solo los que tenga el usuarios ///////////////
        	Usuario usr = authenticationFacade.getUsuario();      	
        	return groupService.filtrarModulosByGrupos( modulos, usr.getGroup().getRoles() );
        }
      
        return modulos;
    }  
    
    
    private List<WraperModulo> listarPermisosBases() {
    	
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
         	if ( clave.indexOf(".") < 0 ) {
         		unModulo = new WraperModulo();
                 unModulo.setModulo(clave);
                 unModulo.setPermisos(controllers.get(clave));
                 modulos.add(unModulo);
         	}            
         }   
    	
         return modulos;
    }
    
    
    @RequestMapping(value="/permisos/grupo/{idGrupo}/permiso/{idPermiso}/grantrevoke",method=RequestMethod.PUT)
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_GRUPOS_LISTAR'))")
    public ResponseEntity< Object > modificarPermiso( @PathVariable Long idGrupo,  @PathVariable String idPermiso){       
        boolean retorno = groupService.grantOrRevokePermission(idGrupo, idPermiso);
        return new ResponseEntity<>( retorno, HttpStatus.OK); 
    }
    
    //////////////////////////////////////////////////////////////
    
    @Descripcion(value="Gestionar Perfiles de todas las Empresas",permission="ROLE_GRUPOS_TODOS_LISTAR")
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_GRUPOS_TODOS_LISTAR') or hasRole('ROLE_GRUPOS_LISTAR') )")
    @RequestMapping(value="/permisos/grupos", method=RequestMethod.GET)
    public ResponseEntity< Object > getGrupos( Pageable pageable, HttpServletRequest request ){
    	
    	Map< String, Object > respuesta = new HashMap<>(); 
    	List<Empresa> empresas =  empresaService.getEmpresas();
    	
    	if ( request.isUserInRole("ROLE_GRUPOS_TODOS_LISTAR")
    			 || request.isUserInRole("ROLE_ADMIN") ) {    		
    		//Si tiene permisos entrega todos sino solo de su empresa
    		respuesta.put("grupos", groupService.findAll( pageable ).map( group -> groupMapper.toDTO(group) ) );
    		respuesta.put("empresas", empresas );                		
    	}else {
    		
    		String empresaUsuario = authenticationFacade.getEmpresa();
    		
    		respuesta.put("grupos", groupService.findAllByEmpresa( empresaUsuario,
    																pageable).map( group -> groupMapper.toDTO(group) ));
    		respuesta.put("empresas", empresas.stream().filter( emp -> emp.getEmpCodigo().equalsIgnoreCase( empresaUsuario ) ) );                				
    	}    	
    	return new ResponseEntity<>( respuesta, HttpStatus.OK);
    }   

    @Descripcion(value="Gestionar Perfiles Propios",permission="ROLE_GRUPOS_LISTAR")
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_GRUPOS_LISTAR'))")
    @RequestMapping(value="/permisos/grupos/{empresa}", method=RequestMethod.GET)
    public Page<GroupDTO> getGruposByEmpresa( @PathVariable("empresa") String empresa, Pageable pageable ){   
    	//Corroborar que sino es su empresa le debe dar un 403
        return groupService.findAllByEmpresa( empresa, pageable).map( group -> groupMapper.toDTO(group) );
    	//return groupService.findFetchWithRoles( pageable).map( group -> groupMapper.toDTO(group) ); 
    }
    
    /////////////////////////////////////////////////////////////
    
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_GRUPOS_LISTAR'))")
    @RequestMapping(value="/permisos/grupo", method=RequestMethod.POST)
    public ResponseEntity< Object > saveGrupo( @Valid @RequestBody GroupDTO groupDTO ){
        groupService.save(groupDTO);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }
    
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_GRUPOS_LISTAR'))") 
    @RequestMapping(value="/permisos/grupo/{id}", method=RequestMethod.PUT)
    public ResponseEntity< Object > updateGrupo( @PathVariable Long id, @Valid @RequestBody GroupDTO groupDTO ){
        groupService.update(id, groupDTO);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }    
   
    @PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_GRUPOS_LISTAR'))")
    @RequestMapping(value="/permisos/grupos/{id}", method=RequestMethod.DELETE)
    public ResponseEntity< Object > deleteGrupo( @PathVariable Long id ){
       groupService.delete(id);
       return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
   
    
}
