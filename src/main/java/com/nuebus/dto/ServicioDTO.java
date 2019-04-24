
package com.nuebus.dto;

import com.nuebus.model.HorarioServicio;
import com.nuebus.model.ServicioPK;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Valeria
 */
public class ServicioDTO {
    
    ServicioPK servicioPK;
    int estado;
    Date fechaHoraSalida;
    String escSalida;
    Date fechaHoraLlegada;
    String escLlegada;
    
    Integer etaInicio;
    Integer etaFin;
    
    
    Set<ChoferEtapasDTO> choferes = new HashSet<>();
    
    Set<VehiculoEtapaDTO> vehiculos = new HashSet<>();
    
    List<HorarioServicio> horarios = new ArrayList<>();

    public ServicioDTO() {
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

    public Date getFechaHoraLlegada() {
        return fechaHoraLlegada;
    }

    public void setFechaHoraLlegada(Date fechaHoraLlegada) {
        this.fechaHoraLlegada = fechaHoraLlegada;
    }
    

    public Set<ChoferEtapasDTO> getChoferes() {
        return choferes;
    }

    public void setChoferes(Set<ChoferEtapasDTO> choferes) {
        this.choferes = choferes;
    }

    public Set<VehiculoEtapaDTO> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(Set<VehiculoEtapaDTO> vehiculos) {
        this.vehiculos = vehiculos;
    }  

    public String getEscSalida() {
        return escSalida;
    }

    public void setEscSalida(String escSalida) {
        this.escSalida = escSalida;
    }

    public String getEscLlegada() {
        return escLlegada;
    }

    public void setEscLlegada(String escLlegada) {
        this.escLlegada = escLlegada;
    }

    public Integer getEtaInicio() {
        return etaInicio;
    }

    public void setEtaInicio(Integer etaInicio) {
        this.etaInicio = etaInicio;
    }

    public Integer getEtaFin() {
        return etaFin;
    }

    public void setEtaFin(Integer etaFin) {
        this.etaFin = etaFin;
    }
    
    
    public List<HorarioServicio> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<HorarioServicio> horarios) {
		this.horarios = horarios;
	}

	@Override
    public String toString() {
        return "ServicioDTO{" + "servicioPK=" + servicioPK + ", estado=" + estado + ", fechaHoraSalida=" + fechaHoraSalida + ", escSalida=" + escSalida + ", fechaHoraLlegada=" + fechaHoraLlegada + ", escLlegada=" + escLlegada + ", etaInicio=" + etaInicio + ", etaFin=" + etaFin + ", choferes=" + choferes + ", vehiculos=" + vehiculos + '}';
    }

    
       
}
