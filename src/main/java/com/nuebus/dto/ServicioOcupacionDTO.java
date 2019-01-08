package com.nuebus.dto;

import com.nuebus.model.ServicioPK;
import java.util.Date;


/**
 *
 * @author Valeria
 */

public class ServicioOcupacionDTO {
    
    ServicioPK servicioPK;
    Integer estado;
    Date fechaHoraSalida;  
    Date fechaHoraLlegada;

    public ServicioOcupacionDTO() {
    }    

    public ServicioPK getServicioPK() {
        return servicioPK;
    }

    public void setServicioPK(ServicioPK servicioPK) {
        this.servicioPK = servicioPK;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Date getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(Date fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public Date getFechaHoraLlegada() {
        return fechaHoraLlegada;
    }

    public void setFechaHoraLlegada(Date fechaHoraLlegada) {
        this.fechaHoraLlegada = fechaHoraLlegada;
    }    

    @Override
    public String toString() {
        return "ServicioOcupacionDTO{" + "servicioPK=" + servicioPK + ", estado=" + estado + ", fechaHoraSalida=" + fechaHoraSalida + ", fechaHoraLlegada=" + fechaHoraLlegada + '}';
    }
    
    
}
