package com.nuebus.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonBackReference;



public class HorarioServicioPK implements Serializable {	
	
	
	@Column( name = "hrs_eta_codigo" )
	Integer etaCodigo;	
	
	
	@JsonBackReference
	@ManyToOne
    @NotFound(
        action = NotFoundAction.IGNORE)
    @JoinColumns({
        @JoinColumn(
            name = "hrs_ser_emp_codigo",
            referencedColumnName = "serEmpCodigo"),
        @JoinColumn(
            name = "hrs_ser_lin_codigo",
            referencedColumnName = "serLinCodigo"),
        @JoinColumn(
                name = "hrs_ser_fecha_hora",
                referencedColumnName = "serFechaHora"),
        @JoinColumn(
                name = "hrs_ser_refuerzo",
                referencedColumnName = "serRefuerzo")
    })
	Servicio servicio;
	
	
		
	public HorarioServicioPK() {
		super();		
	}

	public HorarioServicioPK(Integer etaCodigo, Servicio servicio) {
		super();
		this.etaCodigo = etaCodigo;
		this.servicio = servicio;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public Integer getEtaCodigo() {
		return etaCodigo;
	}

	public void setEtaCodigo(Integer etaCodigo) {
		this.etaCodigo = etaCodigo;
	}

	@Override
	public String toString() {
		return "HorarioServicioPK [etaCodigo=" + etaCodigo + "]";
	}


	private static final long serialVersionUID = 1L;
	
}
