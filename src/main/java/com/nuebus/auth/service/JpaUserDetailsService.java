package com.nuebus.auth.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nuebus.auth.UserPrincipal;
import com.nuebus.auth.repository.UsuarioRepository;
import com.nuebus.model.Role;
import com.nuebus.model.Usuario;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService{

	/*@Autowired
	private IUsuarioDao usuarioDao;*/
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired 
	UsuarioRepository usuarioRepository;
	
	private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);

	@Override
	public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.findByUsername( username );
		
		if ( usuario != null ) {			
			
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			
			if ( usuario.getGroup() == null ) {
				
				logger.error("Error en el Login: Usuario '" + username + "' no tiene grupo Asignado!");
	        	throw new UsernameNotFoundException("Error en el Login: usuario '" + username + "' no tiene roles asignados!");
			}			
			
			for( Role role: usuario.getGroup().getRoles() ) {
				logger.info("Role: ".concat(role.getAuthority()));
	        	authorities.add(new SimpleGrantedAuthority(role.getAuthority()));				
			}
	        
	        if(authorities.isEmpty()) {
	        	logger.error("Error en el Login: Usuario '" + username + "' no tiene roles asignados!");
	        	throw new UsernameNotFoundException("Error en el Login: usuario '" + username + "' no tiene roles asignados!");
	        }	        		 
	        
	        //Como spring 5 no permite claves planas aqui lo burlamos
	        
	        usuario.setPassword( passwordEncoder.encode( usuario.getPassword() ));
	        
			return UserPrincipal.create( usuario, authorities);
			
		}else {
			logger.error("Error en el Login: no existe el usuario '" + username + "' en el sistema!");
        	throw new UsernameNotFoundException("Username: " + username + " no existe en el sistema!");
		}
		
	}

}
