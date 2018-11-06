
package com.nuebus.model;

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
public class Servicio {
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
    
}
