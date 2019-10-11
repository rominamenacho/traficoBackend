
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
public class PersonalPK implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Column(name="prs_emp_codigo")
    private String empresa;    
    @Column(name="prs_legajo")
    private Long legajo;

    public PersonalPK() {
    }    
    
    public PersonalPK(String empresa, Long legajo) {
        this.empresa = empresa;
        this.legajo = legajo;
    }    

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Long getLegajo() {
        return legajo;
    }

    public void setLegajo(Long legajo) {
        this.legajo = legajo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.empresa);
        hash = 29 * hash + Objects.hashCode(this.legajo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PersonalPK other = (PersonalPK) obj;
        if (!Objects.equals(this.empresa, other.empresa)) {
            return false;
        }
        if (!Objects.equals(this.legajo, other.legajo)) {
            return false;
        }
        return true;
    }   
    
}
