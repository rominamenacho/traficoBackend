
package com.nuebus.dto;

import com.nuebus.model.ChoferPK;

/**
 *
 * @author Valeria
 */
public class ChoferEtapasDTO {
    
    ChoferPK choferPK;  
    Integer tipoChofer;
    Integer etaDesde;
    Integer etaHasta; 
    String nombre;
    String nombreConTipo;
    Integer ordenChofer;
    Integer idAux;

     public ChoferEtapasDTO() {
    }   

    public ChoferPK getChoferPK() {
        return choferPK;
    }

    public void setChoferPK(ChoferPK choferPK) {
        this.choferPK = choferPK;
    }    

    public Integer getEtaDesde() {
        return etaDesde;
    }

    public void setEtaDesde(Integer etaDesde) {
        this.etaDesde = etaDesde;
    }

    public Integer getEtaHasta() {
        return etaHasta;
    }

    public void setEtaHasta(Integer etaHasta) {
        this.etaHasta = etaHasta;
    }  

   public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTipoChofer() {
        return tipoChofer;
    }

    public void setTipoChofer(Integer tipoChofer) {
        this.tipoChofer = tipoChofer;
    }

    public String getNombreConTipo() {
        return nombreConTipo;
    }

    public void setNombreConTipo(String nombreConTipo) {
        this.nombreConTipo = nombreConTipo;
    }    

    public Integer getOrdenChofer() {
		return ordenChofer;
	}

	public void setOrdenChofer(Integer ordenChofer) {
		this.ordenChofer = ordenChofer;
	}

	public Integer getIdAux() {
		return idAux;
	}

	public void setIdAux(Integer idAux) {
		this.idAux = idAux;
	}

	@Override
	public String toString() {
		return "ChoferEtapasDTO [choferPK=" + choferPK + ", tipoChofer=" + tipoChofer + ", etaDesde=" + etaDesde
				+ ", etaHasta=" + etaHasta + ", nombre=" + nombre + ", nombreConTipo=" + nombreConTipo
				+ ", ordenChofer=" + ordenChofer + ", idAux=" + idAux + "]";
	}	
    
}
