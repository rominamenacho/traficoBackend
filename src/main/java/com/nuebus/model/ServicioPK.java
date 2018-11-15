
package com.nuebus.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Valeria
 */
public class ServicioPK implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    String serEmpCodigo = "";
    String serLinCodigo = "";    
    java.util.Date serFechaHora;
    int serRefuerzo = 0;

    public ServicioPK() {
    }   
    
    

    public String getSerEmpCodigo() {
        return serEmpCodigo;
    }

    public void setSerEmpCodigo(String serEmpCodigo) {
        this.serEmpCodigo = serEmpCodigo;
    }   

    public Date getSerFechaHora() {
        return serFechaHora;
    }

    public void setSerFechaHora(Date serFechaHora) {
        this.serFechaHora = serFechaHora;
    }

    public int getSerRefuerzo() {
        return serRefuerzo;
    }

    public void setSerRefuerzo(int serRefuerzo) {
        this.serRefuerzo = serRefuerzo;
    }

    public String getSerLinCodigo() {
        return serLinCodigo;
    }

    public void setSerLinCodigo(String serLinCodigo) {
        this.serLinCodigo = serLinCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.serEmpCodigo);
        hash = 61 * hash + Objects.hashCode(this.serLinCodigo);
        hash = 61 * hash + Objects.hashCode(this.serFechaHora);
        hash = 61 * hash + this.serRefuerzo;
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
        final ServicioPK other = (ServicioPK) obj;
        if (this.serRefuerzo != other.serRefuerzo) {
            return false;
        }
        if (!Objects.equals(this.serEmpCodigo, other.serEmpCodigo)) {
            return false;
        }
        if (!Objects.equals(this.serLinCodigo, other.serLinCodigo)) {
            return false;
        }
        if (!Objects.equals(this.serFechaHora, other.serFechaHora)) {
            return false;
        }
        return true;
    }

   
    
    @Override
    public String toString(){        
        return  "serEmpCodigo=" + serEmpCodigo + ";serLinCodigo= " +  serLinCodigo 
	+ "; serFechaHora="  + serFechaHora  + ";serRefuerzo=" + serRefuerzo;
    } 
  
    
}
