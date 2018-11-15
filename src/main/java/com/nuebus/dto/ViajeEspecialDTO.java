package com.nuebus.dto;

import com.nuebus.model.VehiculoPK;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Valeria
 */
public class ViajeEspecialDTO extends AbstractDTO{
    
    String empCodigo;
    String agenciaContratante;
    java.util.Date fechaHoraSalida;
    String origen;
    String destino;
    java.util.Date fechaHoraRegreso;    
    String observaciones;    
    
    Set<ChoferMinDTO> choferes;
    
    Set<ChoferMinDTO>auxiliares;
    
    VehiculoMinDTO vehiculo;
    
    public ViajeEspecialDTO(){
    
    }

    public String getAgenciaContratante() {
        return agenciaContratante;
    }

    public void setAgenciaContratante(String agenciaContratante) {
        this.agenciaContratante = agenciaContratante;
    }

    public Date getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(Date fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getFechaHoraRegreso() {
        return fechaHoraRegreso;
    }

    public void setFechaHoraRegreso(Date fechaHoraRegreso) {
        this.fechaHoraRegreso = fechaHoraRegreso;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEmpCodigo() {
        return empCodigo;
    }

    public void setEmpCodigo(String empCodigo) {
        this.empCodigo = empCodigo;
    }

    public Set<ChoferMinDTO> getChoferes() {
        return choferes;
    }

    public void setChoferes(Set<ChoferMinDTO> choferes) {
        this.choferes = choferes;
    }

    public VehiculoMinDTO getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoMinDTO vehiculo) {
        this.vehiculo = vehiculo;
    }   

    public Set<ChoferMinDTO> getAuxiliares() {
        return auxiliares;
    }

    public void setAuxiliares(Set<ChoferMinDTO> auxiliares) {
        this.auxiliares = auxiliares;
    }
   
   
    
}
