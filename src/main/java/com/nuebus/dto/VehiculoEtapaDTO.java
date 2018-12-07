package com.nuebus.dto;

import com.nuebus.model.VehiculoPK;

/**
 *
 * @author Valeria
 */
public class VehiculoEtapaDTO {
    
    VehiculoPK  vehiculoPK;    
    Integer etaDesde;
    Integer etaHasta; 
    String nombre;

    public VehiculoEtapaDTO() {
    }   
    
    public VehiculoPK getVehiculoPK() {
        return vehiculoPK;
    }

    public void setVehiculoPK(VehiculoPK vehiculoPK) {
        this.vehiculoPK = vehiculoPK;
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

    @Override
    public String toString() {
        return "VehiculoEtapaDTO{" + "vehiculoPK=" + vehiculoPK + ", etaDesde=" + etaDesde + ", etaHasta=" + etaHasta + '}';
    }
    
    
}
