
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
    private int tipoChofer;
    private int estado;
    private String nombreConTipo;
    private Integer cho_id_aux;
    private List<IncidenciaOcupacionDTO> incidencias = new ArrayList<>();
    private List<ServicioOcupacionDTO> servicios = new ArrayList<>();
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

    public List<ServicioOcupacionDTO> getServicios() {
        return servicios;
    }

    public void setServicios(List<ServicioOcupacionDTO> servicios) {
        this.servicios = servicios;
    }  

    public List<ViajeOcupacionDTO> getViajes() {
        return viajes;
    }

    public void setViajes(List<ViajeOcupacionDTO> viajes) {
        this.viajes = viajes;
    }    

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getTipoChofer() {
        return tipoChofer;
    }

    public void setTipoChofer(int tipoChofer) {
        this.tipoChofer = tipoChofer;
    }

    public String getNombreConTipo() {
        return nombreConTipo;
    }

    public void setNombreConTipo(String nombreConTipo) {
        this.nombreConTipo = nombreConTipo;
    }


	public Integer getCho_id_aux() {
		return cho_id_aux;
	}


	public void setCho_id_aux(Integer cho_id_aux) {
		this.cho_id_aux = cho_id_aux;
	}


	@Override
	public String toString() {
		return "ChoferOcupacionDTO [choferPK=" + choferPK + ", nombre=" + nombre + ", tipoChofer=" + tipoChofer
				+ ", estado=" + estado + ", nombreConTipo=" + nombreConTipo + ", cho_id_aux=" + cho_id_aux
				+ ", incidencias=" + incidencias + ", servicios=" + servicios + ", viajes=" + viajes + "]";
	}       
    
}
