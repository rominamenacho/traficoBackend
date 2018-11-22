package com.nuebus.dto;

import java.util.Date;

/**
 *
 * @author Valeria
 */
public class ViajeOcupacionDTO {
    
    Long idViaje;
    java.util.Date inicio;
    java.util.Date fin;

    public ViajeOcupacionDTO() {
    }    
    

    public ViajeOcupacionDTO(Long idViaje, Date inicio, Date fin) {
        this.idViaje = idViaje;
        this.inicio = inicio;
        this.fin = fin;
    }  
    

    public Long getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(Long idViaje) {
        this.idViaje = idViaje;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    @Override
    public String toString() {
        return "ViajeOcupacionDTO{" + "idViaje=" + idViaje + ", inicio=" + inicio + ", fin=" + fin + '}';
    }
    
    
    
}
