package com.nuebus.vencimientosX;

import java.util.List;

import com.nuebus.dto.VencimientoCalculadoDTO;
import com.nuebus.model.Vehiculo;
import com.nuebus.model.Vencimiento;
import com.nuebus.repository.VehiculoRepository;

public abstract class VencimientoVehiculo extends VencimientoGral{
	
		
	final static String FECHA_VERIFICACION_CAMPO_VEHICULO = "vehVerificacionTecnica";	
	
	public static VencimientoVehiculo getInstance( Vencimiento venc ) {
		
		if (  venc.getTipoVencimiento().getNombreCampo().equals( FECHA_VERIFICACION_CAMPO_VEHICULO ) ) {
			return new VencimientoVehiculoFechaVerificacion( venc );
		} 
		
		return null;
	}	
	
	
	public VencimientoVehiculo(Vencimiento vencimiento) {
		super(vencimiento);
		// TODO Auto-generated constructor stub
	}
	
	public abstract VencimientoCalculadoDTO calcularVencimiento( Vehiculo vehiculo );
	
	public abstract  List<Vehiculo> getVehiculosVencidos( VehiculoRepository vehiculoRepository,  String empresa, int estado );

}
