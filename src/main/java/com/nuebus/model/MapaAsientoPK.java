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
public class MapaAsientoPK implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Column(name="MPA_EMP_CODIGO")
    String empresa;
    @Column(name="MPA_CODIGO")
    int codigo; 

    public MapaAsientoPK() { }

    public MapaAsientoPK(String empresa, int codigo) {
        this.empresa = empresa;
        this.codigo = codigo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.empresa);
        hash = 41 * hash + this.codigo;
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
        final MapaAsientoPK other = (MapaAsientoPK) obj;
        if (!Objects.equals(this.empresa, other.empresa)) {
            return false;
        }
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }   
    
    
}
