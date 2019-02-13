package com.nuebus.dto;

import java.util.Date;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Valeria
 */
public class CarnetDTO extends AbstractDTO{
	
    @NotNull    
    @Digits(integer=1, fraction=0)
    int tipo;
    @NotNull   
    Date fechaEmision;
    @NotNull   
    Date fechaVenc;    
    String numeroCarnet;   
    String observaciones;
    
    Boolean mostrarDias;
    Integer diasVencerFechaVto;

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaVenc() {
        return fechaVenc;
    }

    public void setFechaVenc(Date fechaVenc) {
        this.fechaVenc = fechaVenc;
    }    

    public String getNumeroCarnet() {
        return numeroCarnet;
    }

    public void setNumeroCarnet(String numeroCarnet) {
        this.numeroCarnet = numeroCarnet;
    }  

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

	public Boolean getMostrarDias() {
		return mostrarDias;
	}

	public void setMostrarDias(Boolean mostrarDias) {
		this.mostrarDias = mostrarDias;
	}

	public Integer getDiasVencerFechaVto() {
		return diasVencerFechaVto;
	}

	public void setDiasVencerFechaVto(Integer diasVencerFechaVto) {
		this.diasVencerFechaVto = diasVencerFechaVto;
	}

	@Override
	public String toString() {
		return "CarnetDTO [tipo=" + tipo + ", fechaEmision=" + fechaEmision + ", fechaVenc=" + fechaVenc
				+ ", numeroCarnet=" + numeroCarnet + ", observaciones=" + observaciones + ", mostrarDias=" + mostrarDias
				+ ", diasVencerFechaVto=" + diasVencerFechaVto + "]";
	}      
	
    
}
