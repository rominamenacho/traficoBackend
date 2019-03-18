package com.nuebus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.nuebus.annotations.PermisoEnVista;
import com.nuebus.auth.repository.GroupRepository;
import com.nuebus.auth.repository.UsuarioRepository;
import com.nuebus.auth.service.GroupService;
import com.nuebus.dto.GroupDTO;
import com.nuebus.dto.WraperModulo;
import com.nuebus.mapper.GroupMapper;
import com.nuebus.model.Chofer;
import com.nuebus.model.Group;
import com.nuebus.model.Role;
import com.nuebus.model.Usuario;
import com.nuebus.repository.ChoferRepository;
import com.nuebus.utilidades.HelloMessageService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.test.context.support.WithMockUser;



@RunWith(SpringRunner.class)
@SpringBootTest
public class TraficoNuebusApplicationTests {
	
	@Autowired 
	ChoferRepository choferRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	GroupRepository  groupRepository;
	
	
	@Autowired
    GroupMapper groupMapper;
	
	@Autowired
	GroupService groupService;
	
	@Autowired
	HelloMessageService helloMessageService;
	
	
	@Test
	public void contextLoads() {	
		
		// ROLE_USUARIOS_LISTAR   ROLE_CHOFERES_LISTAR
		
		List<WraperModulo> modulos =  armarModulos();
		
        System.out.println( modulos );        
        
        //////////////////////////////////////
        
        Usuario usr = usuarioRepository.findByUsername("GPERELLO");
        
        Set<Role> roles = usr.getGroup().getRoles();      
        
        
       
        /*for(  WraperModulo modulo: modulos ) {
        	
        	modulo.setPermisos( modulo.getPermisos().stream()
        						.filter( per -> estaEnLosPermisos ( per.getAuthority(), roles ) ) 
        						.collect( Collectors.toList()) );
        	if( modulo.getPermisos() == null || modulo.getPermisos().isEmpty()) {
        		modulos.remove(modulo);
        	} 
        }*/
        
        List<WraperModulo> modulosRespuesta = new ArrayList<>();
        
        
        modulos.forEach( modulo ->{
        	modulo.setPermisos( modulo.getPermisos().stream()
					.filter( per -> estaEnLosPermisos ( per.getAuthority(), roles ) ) 
					.collect( Collectors.toList()) );
        	if( modulo.getPermisos() != null && !modulo.getPermisos().isEmpty()) {
        		modulosRespuesta.add( modulo);
        	}
        } );
        
        System.out.println( modulosRespuesta );        
        
        
	}
	
	boolean estaEnLosPermisos( String authority,  Set<Role> roles ) {
		
		Optional<Role> rol = roles.stream().filter( r -> r.getAuthority().equalsIgnoreCase(authority)).findFirst();
		
		return rol.isPresent();
	}
	
	List<WraperModulo> armarModulos(){
		
		List<WraperModulo> modulos = new ArrayList<>();
		
		WraperModulo unModulo = new WraperModulo();
        unModulo.setModulo("Permisos");
        
        PermisoEnVista perm = new PermisoEnVista(); 
        perm.setAuthority("ROLE_USUARIOS_LISTAR");
        unModulo.getPermisos().add(perm );        
        
        perm = new PermisoEnVista(); 
        perm.setAuthority("ROLE_USUARIOS_TODOS_LISTAR");
        unModulo.getPermisos().add(perm );       
        
        modulos.add(unModulo);		  
        
        //////////////////////////////////
        unModulo = new WraperModulo();
        unModulo.setModulo("Incidencias");
        
        perm = new PermisoEnVista(); 
        perm.setAuthority("ROLE_INCIDENCIAS_LISTAR");
        unModulo.getPermisos().add(perm );        
        
        modulos.add(unModulo);		
        
        /////////////////////////////////////
        
        unModulo = new WraperModulo();
        unModulo.setModulo("Choferes");
        
        perm = new PermisoEnVista(); 
        perm.setAuthority("ROLE_CHOFERES_LISTAR");
        unModulo.getPermisos().add(perm );        
        
        modulos.add(unModulo);		
        
        return modulos;
	}

	/*@Test
	public void contextLoads() {	
		
		Pageable pageable = PageRequest.of(0, 500);
		
		Page<Usuario> pageUsuario = usuarioRepository.findAllByEmpresaAndBusqueda("IMQ", "1", pageable);
		
		
		System.out.println( pageUsuario.getContent().stream().count() );
		
		pageUsuario.getContent().stream().forEach( u -> System.out.println( u.getNombre()  ));
		
		
	}*/	
	
	
	/*@Test
	@WithMockUser(username = "CVP", authorities = { "TAD", "ASDADA" })
	public void getMessageWithMockUser() {
		String message = helloMessageService.getMessage();
		System.out.println( message );
		
		 Pageable pageable = PageRequest.of(0, 200);
				
		 groupService.findAll( pageable ).getContent().stream().forEach( System.out::println);;
		//groupService.findAll(pageable).getContent().stream().forEach( System.out::println);;
	}*/
	

}
