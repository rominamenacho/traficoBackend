
package com.nuebus.dto;

import java.util.Date;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Valeria
 */
public class VehiculoIncidenciaDTO extends AbstractDTO{
    
    @NotNull
    @Digits(integer = 10, fraction = 0)
    private long idIncidencia;
    @NotNull
    private java.util.Date inicio;
    @NotNull
    private java.util.Date fin;
    
    
    public VehiculoIncidenciaDTO(){
    }

    public VehiculoIncidenciaDTO(  long idIncidencia, java.util.Date inicio, java.util.Date fin ){
        this.idIncidencia = idIncidencia ;
        this.inicio = inicio ;
        this.fin = fin;        
    }

    public long getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(long idIncidencia) {
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
    
}
