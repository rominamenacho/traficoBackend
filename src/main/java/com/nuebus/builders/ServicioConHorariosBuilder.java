package com.nuebus.builders;

import static com.nuebus.model.Chofer.CHOFER;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.nuebus.dto.ChoferDTO;
import com.nuebus.dto.ChoferEtapasDTO;
import com.nuebus.dto.ServicioDTO;
import com.nuebus.dto.VehiculoEtapaDTO;
import com.nuebus.model.Chofer;
import com.nuebus.model.ChoferPK;
import com.nuebus.model.HorarioServicio;
import com.nuebus.model.Servicio;

import com.nuebus.model.VehiculoPK;

public class ServicioConHorariosBuilder {
	
	private List<Servicio> servicios = new ArrayList<>();	
	private  List<ChoferDTO> choferes =  new ArrayList<>();
	
	 public ServicioConHorariosBuilder(List<Servicio> servicios,  List<ChoferDTO> choferes ) {
		super();
		this.servicios = servicios;
		this.choferes = choferes;
	}

	public  List<ServicioDTO> build(){      
	        
        List<ServicioDTO> serviciosDTO = new ArrayList<>();       
        
        for( Servicio serv: this.servicios ){ 
        	
            ServicioDTO servDTO = new ServicioDTO();
            servDTO.setServicioPK( serv.getServicioPK() );
            servDTO.setEstado( serv.getSerEsrCodigo() );
            
            ordenarByCodigoEtapaYCompletarHorariosServicios( serv.getHorarios() );
            
            
            HorarioServicio desde = serv.getHorarios().stream()
            							.filter( h -> h.getAccionEtaCodigo() == HorarioServicio.HORARIO_SALIDA )
            							.findFirst().orElse(null);	
            
            if( desde != null ) {
            	 servDTO.setFechaHoraSalida( desde.getFechaHoraSalida() );
            	 if( desde.getEtapa() != null ) {
            		 servDTO.setEscSalida( desde.getEtapa().getEscalaCodigo() );
            	 }                 
                 servDTO.setEtaInicio( desde.getCodigoEtapa() );
            }
            
            HorarioServicio hasta = serv.getHorarios().stream()
										.filter( h -> h.getAccionEtaCodigo() == HorarioServicio.HORARIO_LLEGADA )
										.findFirst().orElse(null);
            if( hasta != null ) {
            	 servDTO.setFechaHoraLlegada( hasta.getFechaHoraLlegada() );
            	 if ( hasta.getEtapa() != null ) {
            		 servDTO.setEscLlegada( hasta.getEtapa().getEscalaCodigo() );
            	 }                     
                 servDTO.setEtaFin( hasta.getCodigoEtapa() );
            }            
            
            servDTO.setChoferes(  buildChoferes( serv.getHorarios() ) );                        
            servDTO.setVehiculos( buildVehiculos( serv.getHorarios() ) );        
            
            servDTO.setHorarios( serv.getHorarios()  );
            
            serviciosDTO.add( servDTO );  
            
        }        
        return serviciosDTO;
    }
	
	void ordenarByCodigoEtapaYCompletarHorariosServicios(  List<HorarioServicio> horarios ) {
		
		horarios.sort( Comparator.comparing( HorarioServicio :: getCodigoEtapa ) );
		
		HorarioServicio anterior = null;
		
		for( HorarioServicio h: horarios ) {
			if(  anterior != null && anterior.getFechaHoraLlegada() == null  ){
				anterior.setFechaHoraLlegada(  h.getFechaHoraSalida() );
			}			
			anterior = h;
		}
		
		HorarioServicio hasta = horarios.stream()
				.filter( h -> h.getAccionEtaCodigo() == HorarioServicio.HORARIO_LLEGADA )
				.findFirst().orElse(null);
		 
		if( hasta != null && hasta.getFechaHoraLlegada() == null && hasta.getFechaHoraSalida() != null ) {
			hasta.setFechaHoraLlegada( hasta.getFechaHoraSalida() );
		}
		
	}
	 
	 Set<ChoferEtapasDTO> buildChoferes( List<HorarioServicio> horarios ){
			
		 	Set<ChoferEtapasDTO> choferes = new HashSet<>();		
			
			choferes.addAll( generarChoferesByOrdenChofer( horarios,  Chofer.PRIMER_CHOFER ) );
			
			choferes.addAll( generarChoferesByOrdenChofer( horarios,  Chofer.SEGUNDO_CHOFER ) );
			
			choferes.addAll( generarChoferesByOrdenChofer( horarios,  Chofer.PRIMER_AUX ) );
			
			choferes.addAll( generarChoferesByOrdenChofer( horarios,  Chofer.SEGUNDO_AUX ) );		
							 
			return choferes;				 
	}
		
	Set<VehiculoEtapaDTO>  buildVehiculos( List<HorarioServicio> horarios ) {
		Set<VehiculoEtapaDTO> vehiculos = new HashSet<>();
		
		horarios.stream().filter( h -> h.getInterno() != null )
						 .map( h -> h.getInterno() )
						 .distinct()
						 .forEach( codigoInterno ->{
							 vehiculos.add( generarUnidad(  codigoInterno,  horarios ) );
						 	}
						  );
		return vehiculos;
	}
		
	VehiculoEtapaDTO generarUnidad( Integer codigoInterno, List<HorarioServicio> horarios ){
	
		VehiculoEtapaDTO vehiculo = new VehiculoEtapaDTO();
		
		Optional<HorarioServicio> hDesde = horarios.stream().filter( h -> h.getInterno() == codigoInterno )
				   .min( Comparator.comparingInt( HorarioServicio::getCodigoEtapa ) );
		Integer desde = hDesde.isPresent()? hDesde.get().getCodigoEtapa():-1;
		vehiculo.setEtaDesde( desde  );
		
		Optional<HorarioServicio> hHasta = horarios.stream().filter( h -> h.getInterno() == codigoInterno )
		.max( Comparator.comparingInt( HorarioServicio::getCodigoEtapa ) );
		Integer hasta = hHasta.isPresent()? hHasta.get().getCodigoEtapa():-1; 
		vehiculo.setEtaHasta( hasta ); 
		
		String empresa = hHasta.isPresent()? hHasta.get().getHorarioServicioPK().getServicio().getServicioPK().getSerEmpCodigo():"****";
		vehiculo.setVehiculoPK(  new VehiculoPK( empresa ,	 codigoInterno ));	
		
		return vehiculo;		
	}
	
	List<ChoferEtapasDTO> generarChoferesByOrdenChofer( List<HorarioServicio> horarios, Integer ordenChofer ){
		
		List<ChoferEtapasDTO> choferes = new ArrayList<>();
		horarios.stream().filter( h ->  h.getChoferByOrden( ordenChofer ) != null  )
		 .map( h -> h.getChoferByOrden( ordenChofer ) ).distinct()
		 .forEach( codigoCho ->{			 
			 choferes.add( generarUnChofer( codigoCho, 
					 						horarios, 
					 						ordenChofer ) );					 									 
		 });
		
		return choferes;		
	}
	
	ChoferEtapasDTO generarUnChofer( Long codigoCho, List<HorarioServicio> horarios, Integer ordenChofer ){		 
		
		 ChoferEtapasDTO choferEtapasDTO = new ChoferEtapasDTO();
		 choferEtapasDTO.setOrdenChofer( ordenChofer );			
		 
		 Optional<HorarioServicio> hDesde = horarios.stream().filter( h -> h.getChoferByOrden( ordenChofer ) == codigoCho )
				 					   .min( Comparator.comparingInt( HorarioServicio::getCodigoEtapa ) );
		 Integer desde = hDesde.isPresent()? hDesde.get().getCodigoEtapa():-1;
		 choferEtapasDTO.setEtaDesde( desde  );
		 
		 Optional<HorarioServicio> hHasta = horarios.stream().filter( h -> h.getChoferByOrden( ordenChofer ) == codigoCho )
				   .max( Comparator.comparingInt( HorarioServicio::getCodigoEtapa ) );
		 Integer hasta = hHasta.isPresent()? hHasta.get().getCodigoEtapa():-1; 
		 choferEtapasDTO.setEtaHasta( hasta ); 
		 
		 String empresa = hHasta.isPresent()? hHasta.get().getHorarioServicioPK().getServicio().getServicioPK().getSerEmpCodigo():"****";
		 choferEtapasDTO.setChoferPK(  new ChoferPK( empresa ,	 codigoCho ));	
		 
		 ChoferDTO choferDTO = getChofer( choferEtapasDTO.getChoferPK() );
		 
		 if ( choferDTO != null ) {
			 choferEtapasDTO.setNombre( choferDTO.getCho_nombre() );			 
			 choferEtapasDTO.setTipoChofer(choferDTO.getCho_chofer());         
			 choferEtapasDTO.setIdAux( choferDTO.getCho_id_aux() );
		     String descTipo = ( choferEtapasDTO.getTipoChofer() == CHOFER )? "(CHO)":"(AUX)";
		     choferEtapasDTO.setNombreConTipo( choferEtapasDTO.getIdAux() + descTipo + choferEtapasDTO.getNombre() );		         
		 }		 
	
		 return choferEtapasDTO;
	}	
	
	ChoferDTO getChofer( ChoferPK choferPK ){
		
		ChoferDTO choferDTO = this.choferes.stream()
				.filter( ch -> ch.getChoferPK().equals(choferPK) ).findAny().orElse(null);
		return choferDTO;		
	}
	
	void ordenarYCompletarHorariosServicios(  ArrayList<HorarioServicio> horarios ) {
		
		horarios.sort( Comparator.comparing( HorarioServicio :: getCodigoEtapa ) );	
		
	}

}
		
	