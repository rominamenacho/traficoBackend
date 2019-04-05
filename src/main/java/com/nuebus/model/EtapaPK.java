package com.nuebus.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EtapaPK implements Serializable {	
	
	@Column( name = "eta_emp_codigo" )
	String empresa;
	@Column( name = "eta_lin_codigo")
	String linea;
	@Column( name = "eta_codigo" )
	Integer etaCodigo;

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public Integer getEtaCodigo() {
		return etaCodigo;
	}

	public void setEtaCodigo(Integer etaCodigo) {
		this.etaCodigo = etaCodigo;
	}
	
	

	@Override
	public String toString() {
		return "EtapaPK [empresa=" + empresa + ", linea=" + linea + ", etaCodigo=" + etaCodigo + "]";
	}



	private static final long serialVersionUID = 1L;

}
