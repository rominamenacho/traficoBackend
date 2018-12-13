
package com.nuebus.dto;

/**
 *
 * @author Valeria
 */
public class ChoferEtapasDTO {
    
    ChoferPKDTO choferPK;  
    Integer tipoChofer;
    Integer etaDesde;
    Integer etaHasta; 
    String nombre;
    String nombreConTipo;

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

    @Override
    public String toString() {
        return "ChoferEtapasDTO{" + "choferPK=" + choferPK + ", tipoChofer=" + tipoChofer + ", etaDesde=" + etaDesde + ", etaHasta=" + etaHasta + ", nombre=" + nombre + ", nombreConTipo=" + nombreConTipo + '}';
    }

   
    
}