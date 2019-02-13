package com.nuebus.vencimientos;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.nuebus.dto.VehiculoDTO;
import com.nuebus.dto.VencimientosVehiculoDTO;
import com.nuebus.model.Vencimiento;
import com.nuebus.repository.VehiculoRepository;
import com.nuebus.service.VehiculoService;
import com.nuebus.service.VencimientoService;


@Component
public class VencimientosVehiculo {
	
	final String ENTITDAD_VEHICULO = "Vehiculo";
	final String FECHA_VERIFICACION_CAMPO_VEHICULO = "vehVerificacionTecnica";
	final boolean VENCIMIENTO_ACTIVO = true;
		
	@Autowired
	VehiculoRepository vehiculoRepository;
	 
	@Autowired
	VencimientoService vencimientoService;
	 
	@Autowired 
	VehiculoService vehiculoService;
	 
	@Autowired
	VencimientosVehiculoFechaVerificacion vencimientosVehiculoFechaVerificacion;
	

	List<VehiculoDTO> vehiculos = new ArrayList<VehiculoDTO>();
	
	public void calcularAllVencimientosVehiculos( String empresa, List<VehiculoDTO> vehiculos ){	     
		 
		 List<Vencimiento> vencVehiculos = getVencimientosByVehiculos( empresa, VENCIMIENTO_ACTIVO );
	     
	     for(  Vencimiento v:vencVehiculos ){
	    	 if( v.getTipoVencimiento().getNombreCampo().equals( FECHA_VERIFICACION_CAMPO_VEHICULO ) ){	    		
	    		 vencimientosVehiculoFechaVerificacion.calcularVtoFechaVerificacion( v, vehiculos);	    		 	    		 
	    	 }
	     }     	     
	}
	
	public List<VencimientosVehiculoDTO> calcularAllVencimientosVehiculos( String empresa, int estadoVehiculo ){
		
		 List<VencimientosVehiculoDTO> vencimientosRespuestas = new ArrayList<VencimientosVehiculoDTO>();	     
		 List<Vencimiento> vencVehiculos = getVencimientosByVehiculos( empresa, VENCIMIENTO_ACTIVO );
	     
	     for(  Vencimiento v:vencVehiculos ){
	    	 if( v.getTipoVencimiento().getNombreCampo().equals( FECHA_VERIFICACION_CAMPO_VEHICULO ) ){
	    		 vencimientosVehiculoFechaVerificacion.calcularVtoFechaVerificacion( v, estadoVehiculo );	        		 
	    		 vencimientosRespuestas.add( new VencimientosVehiculoDTO( v,
	    				 												  vencimientosVehiculoFechaVerificacion.vehiculos ) );	    		 
	    	 }
	     } 
	     
	     return vencimientosRespuestas;
	}
	
	public List<Vencimiento> getVencimientosByVehiculos( String empresa, boolean activo ) {
		 List<Vencimiento> vencimientosVehiculos = vencimientoService
				   .getVencimientosByEmpresaAndNombreEntidad( empresa, ENTITDAD_VEHICULO, activo );
		 return vencimientosVehiculos;		
	}

	public List<VehiculoDTO> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(List<VehiculoDTO> vehiculos) {
		this.vehiculos = vehiculos;
	}	
	
	
	
}
