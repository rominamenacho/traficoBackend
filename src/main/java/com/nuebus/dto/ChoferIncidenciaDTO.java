package com.nuebus.dto;

import java.util.Date;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Valeria
 */
public class ChoferIncidenciaDTO{
    
    //ChoferDTO chofer;
    @NotNull
    private Long id;
    
    @NotNull
    @Digits(integer = 10, fraction=0)
    private long idIncidencia;
    @NotNull
    java.util.Date inicio;
    @NotNull
    java.util.Date fin;
    
    public ChoferIncidenciaDTO(){}

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }   
    
}
