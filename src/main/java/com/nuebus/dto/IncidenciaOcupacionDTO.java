
package com.nuebus.dto;

import java.util.Date;

/**
 *
 * @author Valeria
 */
public class IncidenciaOcupacionDTO {
    
    Long idIncidencia;
    java.util.Date inicio;
    java.util.Date fin;

    public IncidenciaOcupacionDTO() {
    }   

    public IncidenciaOcupacionDTO(Long idIncidencia, Date inicio, Date fin) {
        this.idIncidencia = idIncidencia;
        this.inicio = inicio;
        this.fin = fin;
    }  
    

    public Long getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(Long idIncidencia) {
        this.idIncidencia = idIncidencia;
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
        return "IncidenciaOcup{" + "idIncidencia=" + idIncidencia + ", inicio=" + inicio + ", fin=" + fin + '}';
    }  
    
    
}
