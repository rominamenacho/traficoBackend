package com.nuebus.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Valeria
 */

@Entity 
@Table(name = "Provincias") 
public class Provincia implements Serializable{	

	private static final long serialVersionUID = 1L;
	
	@Id
    int proCodigo;       
    String proNombre;

    public Provincia(){

    }
    
    public int getProCodigo() {
        return proCodigo;
    }

    public void setProCodigo(int proCodigo) {
        this.proCodigo = proCodigo;
    }

    public String getProNombre() {
        return proNombre;
    }

    public void setProNombre(String proNombre) {
        this.proNombre = proNombre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.proCodigo;
        hash = 41 * hash + Objects.hashCode(this.proNombre);
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
        final Provincia other = (Provincia) obj;
        if (this.proCodigo != other.proCodigo) {
            return false;
        }
        if (!Objects.equals(this.proNombre, other.proNombre)) {
            return false;
        }
        return true;
    }
    
    
    
}
