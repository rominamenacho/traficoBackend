package com.nuebus.dto;

import java.util.List;

import com.nuebus.model.Vencimiento;

public class VencimientosChoferDTO {
	
	Vencimiento vencimiento;
	List<ChoferConCarnetsDTO> choferes;
	
	
	public VencimientosChoferDTO() {		
	}
	
	
	
	public VencimientosChoferDTO(Vencimiento vencimiento, List<ChoferConCarnetsDTO> choferes) {
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
	public List<ChoferConCarnetsDTO> getChoferes() {
		return choferes;
	}
	public void setChoferes(List<ChoferConCarnetsDTO> choferes) {
		this.choferes = choferes;
	}


	@Override
	public String toString() {
		return "VencimientosChoferDTO [vencimiento=" + vencimiento + ", choferes=" + choferes + "]";
	}		
	
}
