
package com.nuebus.dto;

import com.nuebus.model.ChoferPK;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Valeria
 */
public class ChoferOcupacionDTO implements Serializable {
    
    private ChoferPK choferPK;
    private String nombre;
    
    private List<IncidenciaOcupacionDTO> incidencias = new ArrayList<>();
    private List<ServicioDTO> servicios = new ArrayList<>();
    private List<ViajeOcupacionDTO> viajes = new ArrayList<>();

    public ChoferOcupacionDTO() {
        
    } 
    

    public ChoferPK getChoferPK() {
        return choferPK;
    }

    public void setChoferPK(ChoferPK choferPK) {
        this.choferPK = choferPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    } 

    public List<IncidenciaOcupacionDTO> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(List<IncidenciaOcupacionDTO> incidencias) {
        this.incidencias = incidencias;
    }

    public List<ServicioDTO> getServicios() {
        return servicios;
    }

    public void setServicios(List<ServicioDTO> servicios) {
        this.servicios = servicios;
    }

    public List<ViajeOcupacionDTO> getViajes() {
        return viajes;
    }

    public void setViajes(List<ViajeOcupacionDTO> viajes) {
        this.viajes = viajes;
    }    

    @Override
    public String toString() {
        return "ChoferOcupacionDTO{" + "choferPK=" + choferPK + ", nombre=" + nombre + ", incidencias=" + incidencias + ", servicios=" + servicios + ", viajes=" + viajes + '}';
    }
    
    
}
