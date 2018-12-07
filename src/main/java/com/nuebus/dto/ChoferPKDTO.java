/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.dto;

/**
 *
 * @author Valeria
 */
public class ChoferPKDTO {
    
    private String cho_emp_codigo; 
    private long cho_codigo;

    public ChoferPKDTO() {
    }   
    
    public ChoferPKDTO(String cho_emp_codigo, long cho_codigo) {
        this.cho_emp_codigo = cho_emp_codigo;
        this.cho_codigo = cho_codigo;
    }   
    

    public String getCho_emp_codigo() {
        return cho_emp_codigo;
    }

    public void setCho_emp_codigo(String cho_emp_codigo) {
        this.cho_emp_codigo = cho_emp_codigo;
    }

    public long getCho_codigo() {
        return cho_codigo;
    }

    public void setCho_codigo(long cho_codigo) {
        this.cho_codigo = cho_codigo;
    }    

    @Override
    public String toString() {
        return "ChoferPKDTO{" + "cho_emp_codigo=" + cho_emp_codigo + ", cho_codigo=" + cho_codigo + '}';
    }
    
    
}
