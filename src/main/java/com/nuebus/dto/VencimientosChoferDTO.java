package com.nuebus.dto;

import java.util.List;

import com.nuebus.model.Vencimiento;

public class VencimientosChoferDTO {
	
	Vencimiento vencimiento;
	List<ChoferDTO> choferes;
	
	
	public VencimientosChoferDTO() {		
	}	
	
	public VencimientosChoferDTO(Vencimiento vencimiento, List<ChoferDTO> choferes) {
		super();
		this.vencimiento = vencimiento;
		this.choferes = choferes;
	}

	public Vencimiento getVencimiento() {
		return vencimiento;
	}
	public void setVencimiento(Vencimiento vencimiento) {
		this.vencimiento = vencimiento;
	}

	public List<ChoferDTO> getChoferes() {
		return choferes;
	}

	public void setChoferes(List<ChoferDTO> choferes) {
		this.choferes = choferes;
	}


	@Override
	public String toString() {
		return "VencimientosChoferDTO [vencimiento=" + vencimiento + ", choferes=" + choferes + "]";
	}	
	
	
	
}
