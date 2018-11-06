/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

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
    String login;
    
    @Column(name="USR_PASSWORD_TXT") 
    String pass; 
    
    @Column(name="USR_PRS_NOMBRE")            
    String nombre;       
   
    @Column(name="USR_PRS_AGN_CODIGO")
    int agencia;
    
    public UsuarioPK getUsuarioPk() {
        return usuarioPk;
    }

    public void setUsuarioPk(UsuarioPK usuarioPk) {
        this.usuarioPk = usuarioPk;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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
    
      
}
