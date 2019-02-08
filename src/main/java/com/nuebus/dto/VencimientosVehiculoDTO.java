package com.nuebus.dto;

import java.io.Serializable;
import java.util.List;

import com.nuebus.model.TipoVencimiento;
import com.nuebus.model.Vencimiento;

public class VencimientosVehiculoDTO implements Serializable {	
	
	Vencimiento vencimiento;
	List<VehiculoDTO> vehiculos;
	
	public VencimientosVehiculoDTO() {
		super();
	}	

	public VencimientosVehiculoDTO(Vencimiento vencimiento, List<VehiculoDTO> vehiculos) {
		super();
		this.vencimiento = vencimiento;
		this.vehiculos = vehiculos;
	}	

	public Vencimiento getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(Vencimiento vencimiento) {
		this.vencimiento = vencimiento;
	}

	public List<VehiculoDTO> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(List<VehiculoDTO> vehiculos) {
		this.vehiculos = vehiculos;
	}
		
	@Override
	public String toString() {
		return "VencimientosVehiculoDTO [vencimiento=" + vencimiento + ", vehiculos=" + vehiculos + "]";
	}

	private static final long serialVersionUID = 1L;

}
