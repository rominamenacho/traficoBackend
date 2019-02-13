package com.nuebus.vencimientos;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nuebus.dto.CarnetDTO;
import com.nuebus.dto.ChoferConCarnetsDTO;
import com.nuebus.dto.VencimientosChoferDTO;
import com.nuebus.model.Vencimiento;

@Component
public class VencimientosChoferCarnet extends VencimientosChofer {

	
	
	private List<ChoferConCarnetsDTO> getChoferesConCarnetsVencidos( String empresa, int estadoChofer, Vencimiento vencimiento ) {
		
		 List<ChoferConCarnetsDTO> choferesDTO = choferService.getChoferesConCarnetsVencidos( empresa, 
													 estadoChofer,
													 vencimiento.getFechaControl())
													.stream()
													.map( cho -> choferMapper.choferToChoferConCarnet( cho) )
													.collect(Collectors.toList());		
		
		 return choferesDTO;
	}	
	
	@Override
	public void calcularVencimietosChofer(   Vencimiento vencimiento, List<ChoferConCarnetsDTO> choferesDTO ) {	
		 
		
		for(  ChoferConCarnetsDTO ch : choferesDTO  ) {
			
			for( CarnetDTO carnet : ch.getCarnets() ) {
				
				 GregorianCalendar vencimientoCarnet = new GregorianCalendar();
				 vencimientoCarnet.setTime( carnet.getFechaVenc() );   		    
				 vencimientoCarnet.set(GregorianCalendar.HOUR_OF_DAY, 0);
				 vencimientoCarnet.set(GregorianCalendar.MINUTE, 0);
				 vencimientoCarnet.set(GregorianCalendar.SECOND, 0);
				 vencimientoCarnet.set(GregorianCalendar.MILLISECOND, 0);	  		 
		   		 
		   		 GregorianCalendar hoy = new GregorianCalendar();
		   		 hoy.set(GregorianCalendar.HOUR_OF_DAY, 0);
		   		 hoy.set(GregorianCalendar.MINUTE, 0);
		   		 hoy.set(GregorianCalendar.SECOND, 0);
		   		 hoy.set(GregorianCalendar.MILLISECOND, 0);  		 		   		 
		   		 
		   		 int diasAVencer= (int) ( ( vencimientoCarnet.getTime().getTime() -  hoy.getTime().getTime()   )/86400000);		   		 
		   		 
		   		 carnet.setMostrarDias( diasAVencer > 0 && diasAVencer <= vencimiento.getCantidadAnticipacion() );		   		 
		   		 carnet.setDiasVencerFechaVto(diasAVencer );				
			}			
		}		
	}

	@Override
	public VencimientosChoferDTO calcularVencimietosChofer(String empresa, int estadoChofer, Vencimiento vencimiento) {
		
		 List<ChoferConCarnetsDTO> choferesDTO =  getChoferesConCarnetsVencidos( empresa, estadoChofer, vencimiento );		 
		 calcularVencimietosChofer(  vencimiento,  choferesDTO );
		 return new VencimientosChoferDTO( vencimiento, choferesDTO);
	}
	
	
}
