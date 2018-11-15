
package com.nuebus.dto;

import com.nuebus.model.ServicioPK;
import java.util.Date;

/**
 *
 * @author Valeria
 */
public class ServicioDTO {
    
    ServicioPK servicioPK;
    int estado;
    Date fechaHoraSalida;
    String escalaSalida;
    Date fechaHoraLlegada;
    String escalaLlegada;

    public ServicioDTO() {
    }  
    

    public ServicioDTO(ServicioPK servicioPK, int estado, Date fechaHoraSalida, String escalaSalida, Date fechaHoraLlegada, String escalaLlegada) {
        this.servicioPK = servicioPK;
        this.estado = estado;
        this.fechaHoraSalida = fechaHoraSalida;
        this.escalaSalida = escalaSalida;
        this.fechaHoraLlegada = fechaHoraLlegada;
        this.escalaLlegada = escalaLlegada;
    }
    

    public ServicioPK getServicioPK() {
        return servicioPK;
    }

    public void setServicioPK(ServicioPK servicioPK) {
        this.servicioPK = servicioPK;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(Date fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public String getEscalaSalida() {
        return escalaSalida;
    }

    public void setEscalaSalida(String escalaSalida) {
        this.escalaSalida = escalaSalida;
    }

    public Date getFechaHoraLlegada() {
        return fechaHoraLlegada;
    }

    public void setFechaHoraLlegada(Date fechaHoraLlegada) {
        this.fechaHoraLlegada = fechaHoraLlegada;
    }

    public String getEscalaLlegada() {
        return escalaLlegada;
    }

    public void setEscalaLlegada(String escalaLlegada) {
        this.escalaLlegada = escalaLlegada;
    }

    @Override
    public String toString() {
        return "ServicioDTO{" + "servicioPK=" + servicioPK + ", estado=" + estado + ", fechaHoraSalida=" + fechaHoraSalida + ", escalaSalida=" + escalaSalida + ", fechaHoraLlegada=" + fechaHoraLlegada + ", escalaLlegada=" + escalaLlegada + '}';
    }   
    
}
