
package com.nuebus.dto;

import com.nuebus.model.ChoferPK;

/**
 *
 * @author Valeria
 */
public class ChoferMinDTO {
    
     ChoferPK choferPK;
     String cho_nombre;
     int cho_estado = 0;
     
     public ChoferMinDTO(){
     }

    public ChoferPK getChoferPK() {
        return choferPK;
    }

    public void setChoferPK(ChoferPK choferPK) {
        this.choferPK = choferPK;
    }

    public String getCho_nombre() {
        return cho_nombre;
    }

    public void setCho_nombre(String cho_nombre) {
        this.cho_nombre = cho_nombre;
    }

    public int getCho_estado() {
        return cho_estado;
    }

    public void setCho_estado(int cho_estado) {
        this.cho_estado = cho_estado;
    }     
    
}
