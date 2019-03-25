
package com.nuebus.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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
    
    @OneToMany  (cascade = CascadeType.ALL,
    			fetch = FetchType.EAGER,
    			mappedBy = "horarioServicioPK.servicio")
    List<HorarioServicio> horarios ;
    
    public Servicio() {    	
    	 horarios = new ArrayList<>();
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
    
    public List<HorarioServicio> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<HorarioServicio> horarios) {
		this.horarios = horarios;
	}
	    
    @Override
	public String toString() {
		return "Servicio [servicioPK=" + servicioPK + ", serEsrCodigo=" + serEsrCodigo + ", horarios=" + horarios + "]";
	}


	private static final long serialVersionUID = 1L;
    
}
