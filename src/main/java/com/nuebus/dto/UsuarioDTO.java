/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.dto;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.OneToOne;

import com.nuebus.model.Group;
import com.nuebus.model.UsuarioPK;

/**
 *
 * @author Valeria
 */
public class UsuarioDTO {  
    UsuarioPK usuarioPk;	  
    String username;    	       
    String password;            
    String nombre;   
    int agencia;    
    boolean estado;    
    String email;
    
    Group group;    
	
	public UsuarioDTO() {
		
	}
	public UsuarioDTO(UsuarioPK usuarioPk, String username, String password, String nombre, int agencia, boolean estado,
			String email, Group group) {		
		this.usuarioPk = usuarioPk;
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.agencia = agencia;
		this.estado = estado;
		this.email = email;
		this.group = group;
	}
	public UsuarioPK getUsuarioPk() {
		return usuarioPk;
	}
	public void setUsuarioPk(UsuarioPK usuarioPk) {
		this.usuarioPk = usuarioPk;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getAgencia() {
		return agencia;
	}
	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
	   
    
}
