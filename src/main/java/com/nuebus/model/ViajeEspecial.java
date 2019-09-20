
package com.nuebus.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Valeria
 */


@Entity
@Table( name = "viajes_especiales" )
public class ViajeEspecial implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
        
    @GenericGenerator(
            name = "viajeEspSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "viaje_seq"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    ) 
    
    @Id
    @GeneratedValue( generator = "viajeEspSequenceGenerator" )
    private Long id;
    @NotNull
    @NotBlank
    @Size(max = 4)
    String empCodigo;
    @NotNull
    @NotBlank
    String agenciaContratante;
    @NotNull
    java.util.Date fechaHoraSalida;
    @NotNull
    @NotBlank
    String origen;
    @NotNull
    @NotBlank        
    String destino;
    @NotNull
    java.util.Date fechaHoraRegreso;    
    
    String observaciones;
    
    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)   
    @JoinColumns({        
        @JoinColumn(
            name = "veh_emp_codigo",
            referencedColumnName = "vehEmpCodigo"),
        @JoinColumn(
            name = "veh_interno",
            referencedColumnName = "vehInterno")
    })
    Vehiculo vehiculo;   
    
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "viajeEspecial", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<ChoferViaje> ChoferViaje = new HashSet<>(); 
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "viajeEspecial", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<AuxiliarViaje> AuxiliarViaje = new HashSet<>(); 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }  

    public Set<ChoferViaje> getChoferViaje() {
        return ChoferViaje;
    }

    public void setChoferViaje(Set<ChoferViaje> ChoferViaje) {
        this.ChoferViaje = ChoferViaje;
    }

        
    public Set<AuxiliarViaje> getAuxiliarViaje() {
		return AuxiliarViaje;
	}

	public void setAuxiliarViaje(Set<AuxiliarViaje> auxiliarViaje) {
		AuxiliarViaje = auxiliarViaje;
	}

	@Override
    public String toString() {
        return  "id= " + id + ";empCodigo= " + empCodigo + ";agenciaContratante= " + agenciaContratante
                + ";fechaHoraSalida= " + fechaHoraSalida + ";origen= " + origen + "; destino =  " + destino
                + ";fechaHoraRegreso= " + fechaHoraRegreso + "; observaciones= "     + observaciones
                +  ";vehiculo = " + ((vehiculo!= null)? vehiculo.toString():"Sin definir");
    }
    
    
    
    
    
    
}
