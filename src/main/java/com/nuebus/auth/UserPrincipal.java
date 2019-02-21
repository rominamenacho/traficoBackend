package com.nuebus.auth;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.nuebus.model.Usuario;

public class UserPrincipal implements UserDetails  {	
	
	String empresa;	
	Integer agencia;
	String nombre;
	
	
	@JsonIgnore
	String password;
	String username;
	
	private Collection<? extends GrantedAuthority> authorities;	


	
	

	public UserPrincipal(String empresa, Integer agencia, String nombre, String password, String username,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.empresa = empresa;
		this.agencia = agencia;
		this.nombre = nombre;
		this.password = password;
		this.username = username;
		this.authorities = authorities;
	}


	public static UserPrincipal create( Usuario usuario, List<GrantedAuthority>  authorities ) {				
		return new  UserPrincipal( usuario.getUsuarioPk().getEmpresa(),
								   usuario.getAgencia(),
								   usuario.getNombre(),
								   usuario.getPassword(),	
								   usuario.getUsername(),
								   authorities);
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	
	@Override
	public String getPassword() { 
		return password;
	}

	@Override
	public String getUsername() {		
		return username;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() { 
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {		
		return true;
	}
	
	
	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	

	public Integer getAgencia() {
		return agencia;
	}


	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
