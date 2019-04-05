package com.nuebus.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "etapas")
public class Etapa implements Serializable {

	@EmbeddedId
	EtapaPK etapaPK;

	@Column(name = "eta_esc_codigo")
	String escalaCodigo;
	@Column(name = "eta_nombre")
	String nombre;
	@Column(name = "eta_km")
	Long kilometros;
	

	public EtapaPK getEtapaPK() {
		return etapaPK;
	}

	public void setEtapaPK(EtapaPK etapaPK) {
		this.etapaPK = etapaPK;
	}

	public String getEscalaCodigo() {
		return escalaCodigo;
	}

	public void setEscalaCodigo(String escalaCodigo) {
		this.escalaCodigo = escalaCodigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
		
	public Long getKilometros() {
		return kilometros;
	}

	public void setKilometros(Long kilometros) {
		this.kilometros = kilometros;
	}	

	@Override
	public String toString() {
		return "Etapa [etapaPK=" + etapaPK + ", escalaCodigo=" + escalaCodigo + ", nombre=" + nombre + ", kilometros="
				+ kilometros + "]";
	}


	private static final long serialVersionUID = 1L;
}
