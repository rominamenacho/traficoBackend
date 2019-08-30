package com.nuebus.vencimientosX;

import java.util.GregorianCalendar;
import java.util.List;
import com.nuebus.dto.VencimientoCalculadoDTO;
import com.nuebus.model.Vehiculo;
import com.nuebus.model.Vencimiento;
import com.nuebus.repository.VehiculoRepository;

public class VencimientoVehiculoFechaVerificacion extends VencimientoVehiculo{

	public VencimientoVehiculoFechaVerificacion( Vencimiento vencimiento) {
		super(vencimiento); 
	}

	@Override
	public VencimientoCalculadoDTO calcularVencimiento( Vehiculo vehiculo ) {		
		
		 GregorianCalendar vtoFechaVerificacion = new GregorianCalendar();
		 vtoFechaVerificacion.setTime( vehiculo.getVehVerificacionTecnicaVto() );   		    
		 vtoFechaVerificacion.set(GregorianCalendar.HOUR_OF_DAY, 0);
		 vtoFechaVerificacion.set(GregorianCalendar.MINUTE, 0);
		 vtoFechaVerificacion.set(GregorianCalendar.SECOND, 0);
		 vtoFechaVerificacion.set(GregorianCalendar.MILLISECOND, 0);  		 
		 
		 
		 GregorianCalendar hoy = new GregorianCalendar();
		 hoy.set(GregorianCalendar.HOUR_OF_DAY, 0);
		 hoy.set(GregorianCalendar.MINUTE, 0);
		 hoy.set(GregorianCalendar.SECOND, 0);
		 hoy.set(GregorianCalendar.MILLISECOND, 0);  		 
		 
		 
		 int diasAVencer= (int) ( ( vtoFechaVerificacion.getTime().getTime() -  hoy.getTime().getTime()   )/86400000);
		 
		 
		 VencimientoCalculadoDTO calculado = new VencimientoCalculadoDTO();
		 
		 calculado.setId( vencimiento.getId() ); 
		 calculado.setCantidadAnticipacion( vencimiento.getCantidadAnticipacion() ); 
		 calculado.setNombreCampo( vencimiento.getTipoVencimiento().getNombreCampo() ); 	
		 calculado.setDescNombreCampo( vencimiento.getTipoVencimiento().getDescNombreCampo() );		 		 
		 calculado.setDiasAntesVencer(diasAVencer);
		 calculado.setFechaVencimiento( vehiculo.getVehVerificacionTecnicaVto() );
		
		return calculado; 
	}

	@Override
	public  List<Vehiculo> getVehiculosVencidos( VehiculoRepository vehiculoRepository, String empresa, int estado  ) {
		                 
	        GregorianCalendar fechaControl = new GregorianCalendar();
	        fechaControl.add( GregorianCalendar.DATE , vencimiento.getCantidadAnticipacion() );   
	        
	        List<Vehiculo> vehiculos =  vehiculoRepository.getVencimientosByVerificacionTecnica(  
                    										empresa, 
                    										Integer.valueOf( estado ),
                    										fechaControl.getTime() );	
	        return vehiculos;
	}

}
