
package com.nuebus.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Digits;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

/**
 *
 * @author Valeria
 */
@Entity
@Table(name="VEHICULOS")
public class Vehiculo implements Serializable { 
    
    public static final int HABILITADO = 0;
    public static final int DESHABILITADO = 1;
    
    private static final long serialVersionUID = 1L;
    
    @Valid
    @EmbeddedId
    private VehiculoPK vehiculoPK; 
    
    @Size( max = 18)
    private String vehMotor = new String();
    @Size( max = 18)
    private String vehChasis = new String();
    @NotNull
    @Size(min = 6, max = 10)
    private String vehPatente = new String();    
    @Size( max = 20)
    private String vehCarroceria = new String();
    @Column (nullable = true )
    @Digits(integer=12, fraction=0)
    private long vehMovilGps = 0;
    
    //@Digits(integer=3, fraction=0)
    //private int vehMpaCodigo = 0;
    
    @Column( name= "veh_verificacion_tecnica")
    private java.util.Date vehVerificacionTecnicaVto = null;     
    
    private int vehEstado = 0;    
    
    @OneToMany(  fetch = FetchType.LAZY, mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true )
    private Set<VehiculoIncidencia> vehiculoIncidencias = new HashSet<>();
    
    
    /*@ManyToOne
    @JoinColumns({
        @JoinColumn( name = "veh_mpa_emp_codigo", referencedColumnName = "MPA_EMP_CODIGO"),
        @JoinColumn( name = "veh_mpa_codigo",  referencedColumnName = "MPA_CODIGO")         
    })  
    private MapaAsiento mapaAsiento; */
    
    
    @ManyToOne
    @JoinColumnsOrFormulas(value = {
        @JoinColumnOrFormula(formula = @JoinFormula(value = "veh_emp_codigo", referencedColumnName = "MPA_EMP_CODIGO")),
        @JoinColumnOrFormula(column = @JoinColumn(name = "veh_mpa_codigo", referencedColumnName = "MPA_CODIGO")),        
    })  
    private MapaAsiento mapaAsiento;
    
  
      
    public Vehiculo(){ super(); }
    
    public String getVehMotor() {
        return vehMotor;
    }

    public void setVehMotor(String vehMotor) {
        this.vehMotor = vehMotor;
    }

    public String getVehChasis() {
        return vehChasis;
    }

    public void setVehChasis(String vehChasis) {
        this.vehChasis = vehChasis;
    }

    public String getVehPatente() {
        return vehPatente;
    }

    public void setVehPatente(String vehPatente) {
        this.vehPatente = vehPatente;
    }
    
    public String getVehCarroceria() {
        return vehCarroceria;
    }

    public void setVehCarroceria(String vehCarroceria) {
        this.vehCarroceria = vehCarroceria;
    }

    public long getVehMovilGps() {
        return vehMovilGps;
    }

    public void setVehMovilGps(long vehMovilGps) {
        this.vehMovilGps = vehMovilGps;
    }
   

    public java.util.Date getVehVerificacionTecnicaVto() {
		return vehVerificacionTecnicaVto;
	}

	public void setVehVerificacionTecnicaVto(java.util.Date vehVerificacionTecnicaVto) {
		this.vehVerificacionTecnicaVto = vehVerificacionTecnicaVto;
	}

	public VehiculoPK getVehiculoPK() {
        return vehiculoPK;
    }

    public void setVehiculoPK(VehiculoPK vehiculoPK) {
        this.vehiculoPK = vehiculoPK;
    }  

    public Set<VehiculoIncidencia> getVehiculoIncidencias() {
        return vehiculoIncidencias;
    }

    public void setVehiculoIncidencias(Set<VehiculoIncidencia> vehiculoIncidencias) {
        this.vehiculoIncidencias = vehiculoIncidencias;
    }

    public int getVehEstado() {
        return vehEstado;
    }

    public void setVehEstado(int vehEstado) {
        this.vehEstado = vehEstado;
    }	
		

	public MapaAsiento getMapaAsiento() {
		return mapaAsiento;
	}

	public void setMapaAsiento(MapaAsiento mapaAsiento) {
		this.mapaAsiento = mapaAsiento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vehCarroceria == null) ? 0 : vehCarroceria.hashCode());
		result = prime * result + ((vehChasis == null) ? 0 : vehChasis.hashCode());
		result = prime * result + vehEstado;
		result = prime * result + ((vehMotor == null) ? 0 : vehMotor.hashCode());
		result = prime * result + (int) (vehMovilGps ^ (vehMovilGps >>> 32));		
		result = prime * result + ((vehPatente == null) ? 0 : vehPatente.hashCode());
		result = prime * result + ((vehVerificacionTecnicaVto == null) ? 0 : vehVerificacionTecnicaVto.hashCode());
		result = prime * result + ((vehiculoIncidencias == null) ? 0 : vehiculoIncidencias.hashCode());
		result = prime * result + ((vehiculoPK == null) ? 0 : vehiculoPK.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehiculo other = (Vehiculo) obj;
		if (vehCarroceria == null) {
			if (other.vehCarroceria != null)
				return false;
		} else if (!vehCarroceria.equals(other.vehCarroceria))
			return false;
		if (vehChasis == null) {
			if (other.vehChasis != null)
				return false;
		} else if (!vehChasis.equals(other.vehChasis))
			return false;
		if (vehEstado != other.vehEstado)
			return false;
		if (vehMotor == null) {
			if (other.vehMotor != null)
				return false;
		} else if (!vehMotor.equals(other.vehMotor))
			return false;
		if (vehMovilGps != other.vehMovilGps)
			return false;	
		if (vehPatente == null) {
			if (other.vehPatente != null)
				return false;
		} else if (!vehPatente.equals(other.vehPatente))
			return false;
		if (vehVerificacionTecnicaVto == null) {
			if (other.vehVerificacionTecnicaVto != null)
				return false;
		} else if (!vehVerificacionTecnicaVto.equals(other.vehVerificacionTecnicaVto))
			return false;
		if (vehiculoIncidencias == null) {
			if (other.vehiculoIncidencias != null)
				return false;
		} else if (!vehiculoIncidencias.equals(other.vehiculoIncidencias))
			return false;
		if (vehiculoPK == null) {
			if (other.vehiculoPK != null)
				return false;
		} else if (!vehiculoPK.equals(other.vehiculoPK))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vehiculo [vehiculoPK=" + vehiculoPK + ", vehMotor=" + vehMotor + ", vehChasis=" + vehChasis
				+ ", vehPatente=" + vehPatente + ", vehCarroceria=" + vehCarroceria + ", vehMovilGps=" + vehMovilGps
				+ ", vehVerificacionTecnicaVto=" + vehVerificacionTecnicaVto + ", vehEstado=" + vehEstado
				+ ", vehiculoIncidencias=" + vehiculoIncidencias + ", mapaAsiento=" + mapaAsiento + "]";
	}    
    
   
}
