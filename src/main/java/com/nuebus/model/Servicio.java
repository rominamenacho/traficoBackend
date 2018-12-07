
package com.nuebus.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;

/**
 *
 * @author Valeria
 */

@Entity
@Table(name="Servicios")
public class Servicio implements Serializable{
    @Valid
    @EmbeddedId
    ServicioPK servicioPK;        
    int serEsrCodigo = 0;
    
    public Servicio() {
    }
    
    public ServicioPK getServicioPK() {
        return servicioPK;
    }

    public void setServicioPK(ServicioPK servicioPK) {
        this.servicioPK = servicioPK;
    }

    public int getSerEsrCodigo() {
        return serEsrCodigo;
    }

    public void setSerEsrCodigo(int serEsrCodigo) {
        this.serEsrCodigo = serEsrCodigo;
    } 

    @Override
    public String toString() {
        return "Servicio{" + "servicioPK=" + servicioPK + ", serEsrCodigo=" + serEsrCodigo + '}';
    }
    
    
    
}
