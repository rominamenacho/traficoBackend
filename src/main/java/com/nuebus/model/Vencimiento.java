package com.nuebus.model;

import java.io.Serializable;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author Valeria
 */
@Entity
@Table( name = "Vencimientos")
public class Vencimiento implements Serializable {	
	
	@GenericGenerator(
            name = "vencimientosSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "vencimiento_seq")
                ,
                    @Parameter(name = "initial_value", value = "1")
                ,
                    @Parameter(name = "increment_size", value = "1")
            }
    )	
    @Id
    @GeneratedValue(generator = "vencimientosSequenceGenerator")
    Long id;  
    @Size( max=4)
    String empresa;    
    Boolean activo;   
    Integer cantidadAnticipacion;/*En principio dias*/    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipoVencimiento_id", nullable = false)
    private TipoVencimiento tipoVencimiento;
    
    @Transient
    private Integer diasAMostrarDespuesVencido=60;
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}	

	public TipoVencimiento getTipoVencimiento() {
		return tipoVencimiento;
	}

	public void setTipoVencimiento(TipoVencimiento tipoVencimiento) {
		this.tipoVencimiento = tipoVencimiento;
	}

	public Integer getCantidadAnticipacion() {
		return cantidadAnticipacion;
	}

	public void setCantidadAnticipacion(Integer cantidadAnticipacion) {
		this.cantidadAnticipacion = cantidadAnticipacion;
	}	


	public java.util.Date getFechaControl(){
		 GregorianCalendar fechaControl = new GregorianCalendar();	
		 fechaControl.add( GregorianCalendar.DATE ,  getCantidadAnticipacion() );		 
    	 return fechaControl.getTime();
	}
	
	/*
	 public java.util.Date getFechaControl(){
		 GregorianCalendar fechaControl = new GregorianCalendar();	
		 fechaControl.add( GregorianCalendar.DATE ,  getCantidadAnticipacion() );    	 
		 fechaControl.add( GregorianCalendar.DATE ,  - getCantidadPlazoVigencia() );
    	 return fechaControl.getTime();
	 }
	  */

	
	public Integer getDiasAMostrarDespuesVencido() {
		return diasAMostrarDespuesVencido;
	}	
	
	
	@Override
	public String toString() {
		return "Vencimiento [id=" + id + ", empresa=" + empresa + ", activo=" + activo + ", cantidadAnticipacion="
				+ cantidadAnticipacion + ", tipoVencimiento=" + tipoVencimiento + ", diasAMostrarDespuesVendido="
				+ diasAMostrarDespuesVencido + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activo == null) ? 0 : activo.hashCode());
		result = prime * result + ((cantidadAnticipacion == null) ? 0 : cantidadAnticipacion.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tipoVencimiento == null) ? 0 : tipoVencimiento.hashCode());
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
		Vencimiento other = (Vencimiento) obj;
		if (activo == null) {
			if (other.activo != null)
				return false;
		} else if (!activo.equals(other.activo))
			return false;
		if (cantidadAnticipacion == null) {
			if (other.cantidadAnticipacion != null)
				return false;
		} else if (!cantidadAnticipacion.equals(other.cantidadAnticipacion))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tipoVencimiento == null) {
			if (other.tipoVencimiento != null)
				return false;
		} else if (!tipoVencimiento.equals(other.tipoVencimiento))
			return false;
		return true;
	}


	private static final long serialVersionUID = 1L;
	
	      
}
