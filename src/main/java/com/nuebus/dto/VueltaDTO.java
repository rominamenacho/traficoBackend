
package com.nuebus.dto;


import java.util.Date;
import java.util.Set;

/**
 *
 * @author Usuario
 */
public class VueltaDTO {

    // String empCodigo;
    private Long id;
    private java.util.Date fechaHoraSalida;
    private java.util.Date fechaHoraLlegada;
    private java.util.Date fechaHoraSalidaTaller;
    private java.util.Date fechaHoraLlegadaTaller;
    private int kmTotales;
    private java.util.Date duracionTotal;
    private String observaciones;
    

    private Set<ChoferMinDTO> choferes;
    private Set<VehiculoMinDTO> vehiculos;

    public VueltaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getFechaHoraSalidaTaller() {
        return fechaHoraSalidaTaller;
    }

    public void setFechaHoraSalidaTaller(Date fechaHoraSalidaTaller) {
        this.fechaHoraSalidaTaller = fechaHoraSalidaTaller;
    }

    public Date getFechaHoraLlegadaTaller() {
        return fechaHoraLlegadaTaller;
    }

    public void setFechaHoraLlegadaTaller(Date fechaHoraLlegadaTaller) {
        this.fechaHoraLlegadaTaller = fechaHoraLlegadaTaller;
    }

    public int getKmTotales() {
        return kmTotales;
    }

    public void setKmTotales(int kmTotales) {
        this.kmTotales = kmTotales;
    }

    public Date getDuracionTotal() {
        return duracionTotal;
    }

    public void setDuracionTotal(Date duracionTotal) {
        this.duracionTotal = duracionTotal;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }   

    public Set<ChoferMinDTO> getChoferes() {
        return choferes;
    }

    public void setChoferes(Set<ChoferMinDTO> choferes) {
        this.choferes = choferes;
    }

    public Set<VehiculoMinDTO> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(Set<VehiculoMinDTO> vehiculos) {
        this.vehiculos = vehiculos;
    }

}
