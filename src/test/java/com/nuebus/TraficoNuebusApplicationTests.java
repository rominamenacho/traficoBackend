package com.nuebus;



import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nuebus.builders.ServicioConHorariosBuilder;
import com.nuebus.dto.ChoferDTO;
import com.nuebus.dto.ChoferEtapasDTO;
import com.nuebus.dto.ServicioDTO;
import com.nuebus.dto.VehiculoEtapaDTO;
import com.nuebus.dto.VueltaDiagDTO;
import com.nuebus.mapper.VehiculoMapper;
import com.nuebus.model.Chofer;
import com.nuebus.model.ChoferPK;
import com.nuebus.model.HorarioServicio;
import com.nuebus.model.ImagenChofer;
import com.nuebus.model.Servicio;
import com.nuebus.model.ServicioPK;
import com.nuebus.model.Vehiculo;
import com.nuebus.model.VehiculoPK;
import com.nuebus.model.VueltaDiag;
import com.nuebus.repository.ImagenChoferRepository;
import com.nuebus.repository.ServicioRepository;
import com.nuebus.repository.VehiculoRepository;
import com.nuebus.service.ChoferService;
import com.nuebus.service.ServicioService;
import com.nuebus.service.VueltaDiagService;
import com.nuebus.utilidades.Utilities;

import oracle.net.aso.h;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.test.context.support.WithMockUser;



@RunWith(SpringRunner.class)
@SpringBootTest
public class TraficoNuebusApplicationTests {
	
	@Autowired 
	ServicioService servicioService;
	
	@Autowired
	ServicioRepository servicioRepository;
	
	@Autowired
	ChoferService choferService;
	
	@Autowired
	VueltaDiagService vueltaDiagService;
	
	
	@Value(value = "classpath:img/no-img.jpg")
	Resource noImg;
	
	@Value(value = "${directorioUploadsChoferes}")
	String directorioUploadsChoferes;
	
	@Autowired
	ImagenChoferRepository imagenChoferRepository;
	
	@Autowired
	VehiculoRepository vehiculoRepository;
	
	@Autowired
	VehiculoMapper vehiculoMapper;
	
	@Test
	public void contextLoads() {
		
		/*ServicioPK servicioPK = getServicioPK();		
		Servicio servicio = servicioService.findServicioById(servicioPK);
		
		ordenarYCompletarHorariosServicios( servicio.getHorarios() );
		
		mostrarHorarios( servicio );*/
		
		Vehiculo vehiculo = vehiculoRepository.findById( new VehiculoPK("IMQ", 1) ).orElse(null);
		
		System.out.println( "*********__________****" +  vehiculo.getVehPatente() );
		
		//System.out.println( "vehiculo.getMapaAsiento()*****" +  vehiculo.getMapaAsiento() );
		
		
		
	}
	
	void ordenarYCompletarHorariosServicios(  List<HorarioServicio> horarios ) {
		
		horarios.sort( Comparator.comparing( HorarioServicio :: getCodigoEtapa ) );
		
		HorarioServicio anterior = null;
		
		for( HorarioServicio h: horarios ) {
			if(  anterior != null && anterior.getFechaHoraLlegada() == null  ){
				anterior.setFechaHoraLlegada(  h.getFechaHoraSalida() );
			}			
			anterior = h;
		}
		
	}
	
	
	
	/*@Test(expected = JsonMappingException.class)
	public void givenBidirectionRelation_whenSerializing_thenException()
	  throws JsonProcessingException {
	  
		java.util.Date inicio = Utilities.stringToDate("30/11/2018 10:00", Utilities.FORMAT_DATE);
		java.util.Date fin = Utilities.stringToDate("01/12/2018 10:00", Utilities.FORMAT_DATE);
		
		 List<VueltaDiag> vueltas = vueltaDiagService.getVueltas("IMQ", "100", inicio, fin);
	 
	    new ObjectMapper().writeValueAsString(vueltas);
	}*/
	
	
	
	List<ChoferEtapasDTO> generarChoferes( List<HorarioServicio> horarios ){
		
		List<ChoferEtapasDTO> choferes = new ArrayList<>();		
		
		choferes.addAll( generarChoferesByOrdenChofer( horarios,  Chofer.PRIMER_CHOFER ) );
		
		choferes.addAll( generarChoferesByOrdenChofer( horarios,  Chofer.SEGUNDO_CHOFER ) );
		
		choferes.addAll( generarChoferesByOrdenChofer( horarios,  Chofer.PRIMER_AUX ) );
		
		choferes.addAll( generarChoferesByOrdenChofer( horarios,  Chofer.SEGUNDO_AUX ) );		
						 
		return choferes;				 
	}
	
	Set<VehiculoEtapaDTO>  generarUnidades( List<HorarioServicio> horarios ) {
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
		
		 ChoferEtapasDTO chofer = new ChoferEtapasDTO();
		 chofer.setOrdenChofer( ordenChofer );			
		 
		 Optional<HorarioServicio> hDesde = horarios.stream().filter( h -> h.getChoferByOrden( ordenChofer ) == codigoCho )
				 					   .min( Comparator.comparingInt( HorarioServicio::getCodigoEtapa ) );
		 Integer desde = hDesde.isPresent()? hDesde.get().getCodigoEtapa():-1;
		 chofer.setEtaDesde( desde  );
		 
		 Optional<HorarioServicio> hHasta = horarios.stream().filter( h -> h.getChoferByOrden( ordenChofer ) == codigoCho )
				   .max( Comparator.comparingInt( HorarioServicio::getCodigoEtapa ) );
		 Integer hasta = hHasta.isPresent()? hHasta.get().getCodigoEtapa():-1; 
		 chofer.setEtaHasta( hasta ); 
		 
		 String empresa = hHasta.isPresent()? hHasta.get().getHorarioServicioPK().getServicio().getServicioPK().getSerEmpCodigo():"****";
		 chofer.setChoferPK(  new ChoferPK( empresa ,	 codigoCho ));						 		
	
		 return chofer;
	}	
	
	
	void setPersonalYUnidad( Servicio servicio,  ServicioDTO servicioDTO ){
		limpiarPersonalYUnidadDeHorarios( servicio.getHorarios() );
		//setChoferes( servicio,  servicioDTO.getChoferes() );
		//setAuxiliares( servicio,  servicioDTO.getChoferes() );
		//setUnidad( servicio, servicioDTO.getVehiculos() );
		
	}			
		
	
	/*private void setUnidad( Servicio serv, Set<VehiculoEtapaDTO> vehiculos ) {
		
		vehiculos.forEach( v ->{
			serv.getHorarios().stream().filter( h -> v.getEtaDesde() <= h.getHorarioServicioPK().getEtaCodigo()
													&& h.getHorarioServicioPK().getEtaCodigo() <= v.getEtaHasta() )
									  .forEach( h -> h.setInterno((long) v.getVehiculoPK().getVehInterno() ) );
		} );	
		
	}*/	
	
	/*private void setChoferes( Servicio serv,  Set<ChoferEtapasDTO> choferes  ){
        
        ServicioPK servPk = serv.getServicioPK(); 
        List< ChoferEtapasDTO > choferesTipo = choferes.stream()
                                                  .filter( c -> c.getTipoChofer() == Chofer.CHOFER  )
                                                  .collect( Collectors.toList() );

       ///Actualiza Chofer   Uno  y dos                
       //Lo hago individualmente porque pueden tener cho entre desde y hasta distintos       
       
       if( choferesTipo != null && choferesTipo.size() >= 1 && choferesTipo.get(0) != null  ){ 
           ChoferEtapasDTO choUno = choferesTipo.get(0);
           serv.getHorarios().stream().filter( h -> choUno.getEtaDesde() <= h.getHorarioServicioPK().getEtaCodigo()
				   &&  h.getHorarioServicioPK().getEtaCodigo() <= choUno.getEtaHasta() )
					 .forEach( h -> h.setChofer1( choUno.getChoferPK().getCho_codigo() ) );          
	   }        
       
       if( choferesTipo != null && choferesTipo.size() >= 2 && choferesTipo.get(1) != null  ){ 
           ChoferEtapasDTO choDos = choferesTipo.get(1);
           serv.getHorarios().stream().filter( h -> choDos.getEtaDesde() <= h.getHorarioServicioPK().getEtaCodigo()
				   &&  h.getHorarioServicioPK().getEtaCodigo() <= choDos.getEtaHasta() )
					 .forEach( h -> h.setChofer2( choDos.getChoferPK().getCho_codigo() ) );           
       }         
       
   }*/
	
	/*private void setAuxiliares( Servicio serv,  Set<ChoferEtapasDTO> choferes  ){
        
        ServicioPK servPk = serv.getServicioPK(); 
        List< ChoferEtapasDTO > choferesTipo = choferes.stream()
                                                  .filter( c -> c.getTipoChofer() == Chofer.AUXILIAR  )
                                                  .collect( Collectors.toList() );

       ///Actualiza  auxiliar  Uno  y dos                
       //Lo hago individualmente porque pueden tener cho entre desde y hasta distintos       
       
       if( choferesTipo != null && choferesTipo.size() >= 1 && choferesTipo.get(0) != null  ){ 
           ChoferEtapasDTO choUno = choferesTipo.get(0);
           serv.getHorarios().stream().filter( h -> choUno.getEtaDesde() <= h.getHorarioServicioPK().getEtaCodigo()
				   &&  h.getHorarioServicioPK().getEtaCodigo() <= choUno.getEtaHasta() )
					 .forEach( h -> h.setAuxiliar1( choUno.getChoferPK().getCho_codigo() ) );          
	   }        
       
       if( choferesTipo != null && choferesTipo.size() >= 2 && choferesTipo.get(1) != null  ){ 
           ChoferEtapasDTO choDos = choferesTipo.get(1);
           serv.getHorarios().stream().filter( h -> choDos.getEtaDesde() <= h.getHorarioServicioPK().getEtaCodigo()
				   &&  h.getHorarioServicioPK().getEtaCodigo() <= choDos.getEtaHasta() )
					 .forEach( h -> h.setAuxiliar2( choDos.getChoferPK().getCho_codigo() ) );           
       }         
       
   }*/
		
	
	void limpiarPersonalYUnidadDeHorarios( List<HorarioServicio> horarios ){
		
		horarios.forEach( h ->{
			h.setChofer1(null);
			h.setChofer2(null);
			h.setAuxiliar1(null);
			h.setAuxiliar2(null);
			h.setInterno(null);
		});
		 		
	}	
	
	
	void mostrarHorarios( Servicio servicio ) {
		
		servicio.getHorarios().forEach( System.out::println );
		
	}
	
	ServicioPK getServicioPK(){
		
		ServicioPK servicioPK = new ServicioPK();

		servicioPK.setSerEmpCodigo("IMQ");
		servicioPK.setSerLinCodigo("100R");
		servicioPK.setSerRefuerzo(0);
		
		servicioPK.setSerFechaHora( Utilities.stringToDate("01/12/2018 10:00", Utilities.FORMAT_DATEHOUR) );
		
		return servicioPK;		
	}
	
	VueltaDiagDTO crearVueltaDiagDTO(){
		
		VueltaDiagDTO  vueltaDiagDTO = new VueltaDiagDTO();
		vueltaDiagDTO.setEmpresa("IMQ");		
		
		ServicioDTO servicioIda = new ServicioDTO();
				
		ChoferEtapasDTO chofer = new ChoferEtapasDTO();
		chofer.setChoferPK( new  ChoferPK( "IMQ", 7)  );
		chofer.setEtaDesde( 2 );
		chofer.setEtaHasta( 3 );
		chofer.setTipoChofer( Chofer.AUXILIAR );
		
		servicioIda.getChoferes().add( chofer);
		
		
	    chofer = new ChoferEtapasDTO();
		chofer.setChoferPK( new  ChoferPK( "IMQ", 5)  );
		chofer.setEtaDesde( 4 );
		chofer.setEtaHasta( 5 );
		chofer.setTipoChofer( Chofer.CHOFER );
		
		servicioIda.getChoferes().add( chofer);
		
		
		
		VehiculoEtapaDTO vehi = new VehiculoEtapaDTO();
		
		vehi.setVehiculoPK( new VehiculoPK( "IMQ", 33 ) );
		vehi.setEtaDesde(1);
		vehi.setEtaHasta(5);
		
		servicioIda.getVehiculos().add( vehi );
		
		
		vueltaDiagDTO.setServIda(servicioIda);
		
		return vueltaDiagDTO;		
	}
	

}
