/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Valeria
 */
@Embeddable
public class ChoferPK implements Serializable{
    @NotBlank
    @NotNull
    @Size(max = 4)
    @Column
    private String cho_emp_codigo = new String();
    @Column
    @Digits(integer = 10,fraction = 0)
    private long cho_codigo; 
    
    public ChoferPK( ){
    }
    
    
    public ChoferPK( String cho_emp_codigo, long cho_codigo ){
       this.cho_emp_codigo = cho_emp_codigo;
       this.cho_codigo = cho_codigo;
    }
    
   /* public ChoferPK( String cho_emp_codigo, long cho_codigo ){
        this.cho_emp_codigo = cho_emp_codigo;
        this.cho_codigo = cho_codigo;
    }  */  
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ChoferPK){
            ChoferPK choferPK = (ChoferPK) obj;
 
            if(!choferPK.getCho_emp_codigo().equals(cho_emp_codigo)){
                return false;
            }
 
            if( choferPK.getCho_codigo() == cho_codigo ){
                return false;
            }
 
            return true;
        }
 
        return false;
    }
 
    @Override
    public int hashCode() {
        return cho_emp_codigo.hashCode() +  String.valueOf(cho_codigo).hashCode();
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
        return "ChoferPK{" + "cho_emp_codigo=" + cho_emp_codigo + ", cho_codigo=" + cho_codigo + '}';
    }
    
    
}
