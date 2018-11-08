package com.nuebus.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Valeria
 */

@Embeddable
public class LineaPK implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @NotNull
    @NotBlank
    @Size( max = 4)
    String linEmpCodigo = "";
    @NotNull
    @Size( max = 8)
    String linCodigo = "";
    
    
    public LineaPK(){    
    }
    
    public LineaPK( String linEmpCodigo, String linCodigo ){
       this.linEmpCodigo = linEmpCodigo;
       this.linCodigo = linCodigo;       
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
        final LineaPK other = (LineaPK) obj;
        if (!Objects.equals(this.linEmpCodigo, other.linEmpCodigo)) {
            return false;
        }
        if (!Objects.equals(this.linCodigo, other.linCodigo)) {
            return false;
        }
        return true;
    }   
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.linEmpCodigo);
        hash = 83 * hash + Objects.hashCode(this.linCodigo);
        return hash;
    }
    
    
    
    public String getLinEmpCodigo( ) {
        return linEmpCodigo;
    }

    public void setLinEmpCodigo(String linEmpCodigo) {
        this.linEmpCodigo = linEmpCodigo;
    }

    public String getLinCodigo() {
        return linCodigo;
    }

    public void setLinCodigo(String linCodigo) {
        this.linCodigo = linCodigo;
    }  

    @Override
    public String toString() {
        return "LineaPK{" + "linEmpCodigo=" + linEmpCodigo + ", linCodigo=" + linCodigo + '}';
    }
    
    
    
}
