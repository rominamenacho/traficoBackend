
package com.nuebus.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;

/**
 *
 * @author Valeria
 */

@Entity
@Table(name="lineas")
public class Linea {
   
    @Valid
    @EmbeddedId
    private LineaPK lineaPK;     
    private String linNombre = "";
    
    public Linea(){    
    }
    
    public String getLinNombre() {
        return linNombre;
    }

    public void setLinNombre(String linNombre) {
        this.linNombre = linNombre;
    }

    public LineaPK getLineaPK() {
        return lineaPK;
    }

    public void setLineaPK(LineaPK lineaPK) {
        this.lineaPK = lineaPK;
    }    

    @Override
    public String toString() {
        return "Linea{" + "lineaPK=" + lineaPK + ", linNombre=" + linNombre + '}';
    }   
    
    
}
