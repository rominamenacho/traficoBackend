/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;



/**
 *
 * @author Valeria
 */

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    UsuarioPK usuarioPk; 
    
    @Column(name="USR_LOGIN")
    String username;    
      
    @Column(name="USR_PASSWORD_TXT")     
    String password; 
    
    @Column(name="USR_PRS_NOMBRE")            
    String nombre;       
   
    @Column(name="USR_PRS_AGN_CODIGO")
    int agencia;
    
    @Column(name="USR_ESTADO")
    boolean estado;
    
    @Column( name=" USR_EMAIL")
    String email;
    
    @OneToOne()
    Group group;
    
    
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
	
	
	@Override
	public String toString() {
		return "Usuario [usuarioPk=" + usuarioPk + ", username=" + username + ", password=" + password + ", nombre="
				+ nombre + ", agencia=" + agencia + ", estado=" + estado + ", email=" + email + ", group=" + group
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + agencia;
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((usuarioPk == null) ? 0 : usuarioPk.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (agencia != other.agencia)
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (usuarioPk == null) {
			if (other.usuarioPk != null)
				return false;
		} else if (!usuarioPk.equals(other.usuarioPk))
			return false;
		return true;
	} 
            
      
}
