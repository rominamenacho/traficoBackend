
package com.nuebus.dto;

import com.nuebus.model.VehiculoPK;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Valeria
 */
public class VehiculoOcupacionDTO {
    
    VehiculoPK vehiculoPK;
    String patente;
    private int estado;
    
    private List<IncidenciaOcupacionDTO> incidencias = new ArrayList<>();
    private List<ServicioOcupacionDTO> servicios = new ArrayList<>();
    private List<ViajeOcupacionDTO> viajes = new ArrayList<>();

    public VehiculoOcupacionDTO() {
    }

    public VehiculoPK getVehiculoPK() {
        return vehiculoPK;
    }

    public void setVehiculoPK(VehiculoPK vehiculoPK) {
        this.vehiculoPK = vehiculoPK;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public List<IncidenciaOcupacionDTO> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(List<IncidenciaOcupacionDTO> incidencias) {
        this.incidencias = incidencias;
    }

   

    public List<ViajeOcupacionDTO> getViajes() {
        return viajes;
    }

    public void setViajes(List<ViajeOcupacionDTO> viajes) {
        this.viajes = viajes;
    }    

    public List<ServicioOcupacionDTO> getServicios() {
        return servicios;
    }

    public void setServicios(List<ServicioOcupacionDTO> servicios) {
        this.servicios = servicios;
    }

    @Override
    public String toString() {
        return "VehiculoOcupacionDTO{" + "vehiculoPK=" + vehiculoPK + ", patente=" + patente + ", estado=" + estado + ", incidencias=" + incidencias + ", servicios=" + servicios + ", viajes=" + viajes + '}';
    }   
    
    
}
