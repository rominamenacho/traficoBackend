package com.nuebus.dto;

import java.io.Serializable;
import java.util.Date;

public class VencimientoCalculadoDTO implements Serializable{	

	Long id;
	Integer cantidadAnticipacion;
	String nombreCampo;
	String descNombreCampo;
	Integer diasAntesVencer;
	Date fechaVencimiento;

	public VencimientoCalculadoDTO() {
	}

	public Long getId() {
		return id;
	}

	public Integer getCantidadAnticipacion() {
		return cantidadAnticipacion;
	}

	public String getNombreCampo() {
		return nombreCampo;
	}

	public String getDescNombreCampo() {
		return descNombreCampo;
	}

	public Integer getDiasAntesVencer() {
		return diasAntesVencer;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setCantidadAnticipacion(Integer cantidadAnticipacion) {
		this.cantidadAnticipacion = cantidadAnticipacion;
	}

	public void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}

	public void setDescNombreCampo(String descNombreCampo) {
		this.descNombreCampo = descNombreCampo;
	}

	public void setDiasAntesVencer(Integer diasAntesVencer) {
		this.diasAntesVencer = diasAntesVencer;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	private static final long serialVersionUID = 4827458077889546718L;
}
