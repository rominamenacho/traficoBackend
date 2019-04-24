package com.nuebus.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name="horarios_servicios")
public class HorarioServicio implements Serializable {
	
	public static final int HORARIO_SALIDA = 2;
	public static final int HORARIO_LLEGADA = 4;

	@EmbeddedId
	HorarioServicioPK horarioServicioPK;

	@Column(name = "hrs_fecha_hora")
	java.util.Date fechaHoraSalida;
	@Column(name = " hrs_fecha_hora_llegada ")
	java.util.Date fechaHoraLlegada;
	@Column(name = "hrs_ace_codigo")
	Long accionEtaCodigo;
	@Column(name = "hrs_chofer1")
	Long chofer1;
	@Column(name = "hrs_chofer2")
	Long chofer2;
	@Column(name = "hrs_auxiliar1")
	Long auxiliar1;
	@Column(name = "hrs_auxiliar2")
	Long auxiliar2;
	@Column(name = "hrs_interno")
	Integer interno;

	
	@ManyToOne 
	@JoinColumns({
        @JoinColumn(
                name = "hrs_ser_emp_codigo",
                referencedColumnName = "eta_emp_codigo", insertable=false, updatable=false ),
            @JoinColumn(
                name = "hrs_ser_lin_codigo",
                referencedColumnName = "eta_lin_codigo", insertable=false, updatable=false ),
            @JoinColumn(
                    name = "hrs_eta_codigo",
                    referencedColumnName = "eta_codigo", insertable=false, updatable=false )            
        })
	Etapa etapa;	
	
	
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

	public Integer getInterno() {
		return interno;
	}

	public void setInterno(Integer interno) {
		this.interno = interno;
	}

	
	public Etapa getEtapa() {
		return etapa;
	}

	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}
	
	public Integer getCodigoEtapa(){
		return horarioServicioPK!=null? horarioServicioPK.getEtaCodigo():-1;
	}
	
	public Long getChoferByOrden( int orderChofer ) {
		
		Long chofer = (long)-1; 
		switch (orderChofer) {
		case Chofer.PRIMER_CHOFER:
			chofer = getChofer1();
			break;
		case Chofer.SEGUNDO_CHOFER:
			chofer = getChofer2();
			break;	
		case Chofer.PRIMER_AUX:
			chofer = getAuxiliar1();
			break;
		case Chofer.SEGUNDO_AUX:
			chofer = getAuxiliar2();
			break;	
		default:
			chofer = (long)-1; 
			break;
		}
		return chofer;
	}

	
	

	public java.util.Date getFechaHoraSalida() {
		return fechaHoraSalida;
	}

	public void setFechaHoraSalida(java.util.Date fechaHoraSalida) {
		this.fechaHoraSalida = fechaHoraSalida;
	}

	public java.util.Date getFechaHoraLlegada() {
		return fechaHoraLlegada;
	}

	public void setFechaHoraLlegada(java.util.Date fechaHoraLlegada) {
		this.fechaHoraLlegada = fechaHoraLlegada;
	}

	@Override
	public String toString() {
		return "HorarioServicio [horarioServicioPK=" + horarioServicioPK + ", fechaHoraSalida=" + fechaHoraSalida
				+ ", fechaHoraLlegada=" + fechaHoraLlegada + ", accionEtaCodigo=" + accionEtaCodigo + ", chofer1="
				+ chofer1 + ", chofer2=" + chofer2 + ", auxiliar1=" + auxiliar1 + ", auxiliar2=" + auxiliar2
				+ ", interno=" + interno + ", etapa=" + etapa + "]";
	}




	private static final long serialVersionUID = 1L;
}
