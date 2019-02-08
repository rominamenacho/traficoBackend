package com.nuebus.vencimientos;

import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.stereotype.Component;
import com.nuebus.dto.VehiculoDTO;
import com.nuebus.model.Vencimiento;

@Component
public class VencimientosVehiculoFechaVerificacion extends VencimientosVehiculo {
	
	
	public void calcularVtoFechaVerificacion( Vencimiento vencVerificacion, List<VehiculoDTO> vehiculos ){  
		
	  this.vehiculos = vehiculos;	
   	 
   	  vehiculos.stream().forEach( v ->{
   		 
   		 GregorianCalendar vtoFechaVerificacion = new GregorianCalendar();
   		 vtoFechaVerificacion.setTime( v.getVehVerificacionTecnicaVto() );   		    
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
   		 
   		 
   		 v.setMostrarDias( diasAVencer > 0 && diasAVencer <= vencVerificacion.getCantidadAnticipacion()  );    		 
   		 v.setVehVerificacionTecnicaVto ( vtoFechaVerificacion.getTime() );
   		 v.setDiasVencerFechaVerificacion(diasAVencer); 		 
   		 
   	 });   
   	 
	}
	
	public void calcularVtoFechaVerificacion( Vencimiento vencVerificacion, int estadoVehiculo ){	 
	   	 
		 calcularVtoFechaVerificacion( vencVerificacion, 
				 					   vehiculoService.getVehiculosConVencimientosByFechaVerificacion(
				 							   		  vencVerificacion.getEmpresa(), 
				 							   		  estadoVehiculo, 
				 							   		  vencVerificacion.getFechaControl()) );
	}
	

}
