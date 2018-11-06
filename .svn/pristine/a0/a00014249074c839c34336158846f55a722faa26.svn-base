/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Valeria
 */

@Embeddable
public class UsuarioPK implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Column(name = "USR_PRS_EMP_CODIGO")
    String empresa;
    @Column(name = "USR_PRS_LEGAJO")
    long  legajo;    
    
    
    public UsuarioPK(){}

    public UsuarioPK(String empresa, long  legajo ){
        this.empresa = empresa;
        this.legajo = legajo;        
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.empresa);
        hash = 47 * hash + (int) (this.legajo ^ (this.legajo >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsuarioPK other = (UsuarioPK) obj;
        if (!Objects.equals(this.empresa, other.empresa)) {
            return false;
        }
        if (this.legajo != other.legajo) {
            return false;
        }
        return true;
    }
    
    

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public long getLegajo() {
        return legajo;
    }

    public void setLegajo(long legajo) {
        this.legajo = legajo;
    }
    
    
    
}
