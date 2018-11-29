
package com.nuebus.dto;

/**
 *
 * @author Valeria
 */
public class ChoferEtapasDTO {
    
    ChoferPKDTO choferPK;    
    Integer etaDesde;
    Integer etaHasta;  

    public ChoferEtapasDTO() {
    }   

    public ChoferPKDTO getChoferPK() {
        return choferPK;
    }

    public void setChoferPK(ChoferPKDTO choferPK) {
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

    @Override
    public String toString() {
        return "ChoferEtapasDTO{" + "choferPK=" + choferPK + ", etaDesde=" + etaDesde + ", etaHasta=" + etaHasta + '}';
    }
    
    
    
}
