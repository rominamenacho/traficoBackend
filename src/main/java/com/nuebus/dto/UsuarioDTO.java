package com.nuebus.dto;

import com.nuebus.model.Agencia;

import com.nuebus.model.Group;
import com.nuebus.model.Personal;
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
    boolean estado;    
    String email;    
    Group group;    
    Agencia agencia;
    Personal personal;

    public UsuarioDTO() {
    }   

    public UsuarioDTO(UsuarioPK usuarioPk, String username, String password, String nombre, boolean estado, String email, Group group, Agencia agencia, Personal personal) {
        this.usuarioPk = usuarioPk;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.estado = estado;
        this.email = email;
        this.group = group;
        this.agencia = agencia;
        this.personal = personal;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }	

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }    
    
}
