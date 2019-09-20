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
@Table( name="escalas")
public class Escala implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id  
	String  escCodigo;
	String  escNombre;
	int escProCodigo;
	  
	public Escala(){
	  
	}

    public String getEscCodigo() {
        return escCodigo;
    }

    public void setEscCodigo(String escCodigo) {
        this.escCodigo = escCodigo;
    }

    public String getEscNombre() {
        return escNombre;
    }

    public void setEscNombre(String escNombre) {
        this.escNombre = escNombre;
    }   

    public int getEscProCodigo() {
        return escProCodigo;
    }

    public void setEscProCodigo(int escProCodigo) {
        this.escProCodigo = escProCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.escCodigo);
        hash = 61 * hash + Objects.hashCode(this.escNombre);
        hash = 61 * hash + this.escProCodigo;
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
        final Escala other = (Escala) obj;
        if (this.escProCodigo != other.escProCodigo) {
            return false;
        }
        if (!Objects.equals(this.escCodigo, other.escCodigo)) {
            return false;
        }
        if (!Objects.equals(this.escNombre, other.escNombre)) {
            return false;
        }
        return true;
    }

       
    @Override
    public String toString() {
        return  "escCodigo= " + escCodigo +  "; escCodpostal=  "+  escNombre;
    } 
    
}
