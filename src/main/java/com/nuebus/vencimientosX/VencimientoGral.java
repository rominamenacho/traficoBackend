package com.nuebus.vencimientosX;

import com.nuebus.model.Vencimiento;

public abstract class VencimientoGral {
	
	public final static boolean VENCIMIENTO_ACTIVO = true;
	public final static String ENTITDAD_VEHICULO = "Vehiculo";
		
	Vencimiento vencimiento;

	public VencimientoGral(Vencimiento vencimiento) {
		super();
		this.vencimiento = vencimiento;
	}	
	
}
