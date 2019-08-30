package com.nuebus.vencimientos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.nuebus.dto.VehiculoDTO;
import com.nuebus.dto.VencimientoCalculadoDTO;
import com.nuebus.dto.VencimientosVehiculoDTO;
import com.nuebus.mapper.VehiculoMapper;
import com.nuebus.model.MapaAsiento;
import com.nuebus.model.MapaAsientoPK;
import com.nuebus.model.Vehiculo;
import com.nuebus.model.Vencimiento;
import com.nuebus.repository.VehiculoRepository;
import com.nuebus.service.VehiculoService;
import com.nuebus.service.VencimientoService;
import com.nuebus.vencimientosX.VencimientoGral;
import com.nuebus.vencimientosX.VencimientoVehiculo;
import com.nuebus.vistas.MapperVistas;


@Component
public class VencimientosVehiculoService {	
	
		
	@Autowired
	VehiculoRepository vehiculoRepository;
	 
	@Autowired
	VencimientoService vencimientoService;
	
	@Autowired
	VehiculoMapper vehiculoMapper;	
	
	@Autowired
	MapperVistas mapperVistas;
	
	public List<VencimientosVehiculoDTO> calcularAllVencimientosVehiculos( String empresa, int estadoVehiculo ){
		 		 		
		 List<VencimientosVehiculoDTO> vencimientosRespuestas = new ArrayList<VencimientosVehiculoDTO>();
		 List<Vencimiento> vencimientosVehiculos = getVencimientosByVehiculos( empresa,
				 													   estadoVehiculo == Vehiculo.HABILITADO );
		 
		
    	 for( Vencimiento vencimiento: vencimientosVehiculos ){
    		 
    		 final VencimientoVehiculo vencimientoVehiculo = VencimientoVehiculo.getInstance(vencimiento);
			  List<Vehiculo> vehiculosVencidos = vencimientoVehiculo.getVehiculosVencidos( vehiculoRepository, 
					  																		empresa, 
					  																		estadoVehiculo );			  
			  final List<VehiculoDTO> vehiculosDTO = vehiculosVencidos.stream().map( vehiculo -> {
				  final VehiculoDTO vehiculoDTO = mapperVistas.toDTO( vehiculoMapper.toDTO(vehiculo) );
				  vehiculoDTO.getVencimientos().add(getVencimientoCalculado( vencimiento,  vehiculo ));
				  return  vehiculoDTO;
	          }).collect( Collectors.toList());				  
	 
			  vencimientosRespuestas.add( new VencimientosVehiculoDTO( vencimiento, vehiculosDTO) );    		 
    	 }		   	
		
	     return vencimientosRespuestas;
	}	
	
	
	public List<VencimientoCalculadoDTO>  getVencimientoCalculado( List<Vencimiento> vencimientosVehiculo, Vehiculo vehiculo ){
		
		List< VencimientoCalculadoDTO > calculados = new ArrayList<>();		
		VencimientoCalculadoDTO vencCalculado = null;
		for( Vencimiento vencimiento: vencimientosVehiculo ){			 
			 vencCalculado = getVencimientoCalculado( vencimiento, vehiculo );
			 if( vencCalculado != null  ) {
				 calculados.add(vencCalculado);
			 }
		}		
		
		return calculados;
	}
	
	public VencimientoCalculadoDTO  getVencimientoCalculado( Vencimiento vencimientoVehiculo, Vehiculo vehiculo ){
		VencimientoVehiculo intanciaVencimiento = VencimientoVehiculo.getInstance( vencimientoVehiculo );		 
		VencimientoCalculadoDTO vencimientoCalculado = intanciaVencimiento.calcularVencimiento( vehiculo );	
		return vencimientoCalculado;
	}
	
	public List<Vencimiento> getVencimientosByVehiculos( String empresa, boolean activo ) {
		 List<Vencimiento> vencimientosVehiculos = vencimientoService
				   .getVencimientosByEmpresaAndNombreEntidad( empresa, 
						   									  VencimientoGral.ENTITDAD_VEHICULO,
						   									  activo );
		 return vencimientosVehiculos;		
	}
	
	public List<Vencimiento> getVencimientosActivosByVehiculos( String empresa) {
		return getVencimientosByVehiculos( empresa, VencimientoGral.VENCIMIENTO_ACTIVO ); 	
	}
	
}
