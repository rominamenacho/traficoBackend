package com.nuebus.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name="horarios_servicios")
public class HorarioServicio implements Serializable {

	@EmbeddedId
	HorarioServicioPK horarioServicioPK;	
	
	@Column( name = "hrs_ace_codigo")
	Long accionEtaCodigo;	
	@Column( name = "hrs_chofer1")
	Long chofer1;
	@Column( name = "hrs_chofer2")
	Long chofer2;
	@Column( name = "hrs_auxiliar1")
	Long auxiliar1;
	@Column( name = "hrs_auxiliar2")
	Long auxiliar2;
	@Column( name = "hrs_interno")
	Long interno;
	 
	public HorarioServicioPK getHorarioServicioPK() {
		return horarioServicioPK;
	}

	public void setHorarioServicioPK(HorarioServicioPK horarioServicioPK) {
		this.horarioServicioPK = horarioServicioPK;
	}

	public Long getAccionEtaCodigo() {
		return accionEtaCodigo;
	}

	public void setAccionEtaCodigo(Long accionEtaCodigo) {
		this.accionEtaCodigo = accionEtaCodigo;
	}

	public Long getChofer1() {
		return chofer1;
	}

	public void setChofer1(Long chofer1) {
		this.chofer1 = chofer1;
	}

	public Long getChofer2() {
		return chofer2;
	}

	public void setChofer2(Long chofer2) {
		this.chofer2 = chofer2;
	}

	public Long getAuxiliar1() {
		return auxiliar1;
	}

	public void setAuxiliar1(Long auxiliar1) {
		this.auxiliar1 = auxiliar1;
	}

	public Long getAuxiliar2() {
		return auxiliar2;
	}

	public void setAuxiliar2(Long auxiliar2) {
		this.auxiliar2 = auxiliar2;
	}

	public Long getInterno() {
		return interno;
	}

	public void setInterno(Long interno) {
		this.interno = interno;
	}
	
	

	@Override
	public String toString() {
		return "HorarioServicio [horarioServicioPK=" + horarioServicioPK + ", accionEtaCodigo=" + accionEtaCodigo
				+ ", chofer1=" + chofer1 + ", chofer2=" + chofer2 + ", auxiliar1=" + auxiliar1 + ", auxiliar2="
				+ auxiliar2 + ", interno=" + interno + "]";
	}



	private static final long serialVersionUID = 1L;
}
