package com.nuebus.service.impl;

import com.nuebus.dto.ChoferEtapasDTO;
import com.nuebus.dto.ServicioDTO;
import com.nuebus.dto.VehiculoEtapaDTO;
import com.nuebus.dto.VehiculoOcupacionDTO;
import com.nuebus.dto.VueltaDiagDTO;
import com.nuebus.excepciones.ResourceNotFoundException;
import static com.nuebus.model.Chofer.AUXILIAR;

import com.nuebus.model.Chofer;
import com.nuebus.model.HorarioServicio;
import com.nuebus.model.Servicio;
import com.nuebus.model.ServicioPK;
import com.nuebus.model.VueltaDiag;
import com.nuebus.repository.ServicioRepository;
import com.nuebus.repository.VueltaDiagRepository;
import com.nuebus.service.ServicioService;
import com.nuebus.service.VehiculoService;
import com.nuebus.service.VueltaDiagService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Valeria
 */

@Service
@Transactional( readOnly = true )
public class VueltaDiagServiceImpl implements VueltaDiagService {

    @Autowired
    VueltaDiagRepository vueltaDiagRepository;
    
    @Autowired
    ServicioService servicioService; 
    
    @Autowired
    ServicioRepository servicioRepository;
    
    @Autowired
    VehiculoService vehiculoService;
    
    @Transactional( readOnly = false)
    @Override    
    public VueltaDiag saveVueltaDiag( VueltaDiagDTO vueltaDiagDTO ) {        
        
        Servicio servIda = servicioService.findServicioById( vueltaDiagDTO
                                                             .getServIda()
                                                             .getServicioPK() );
        
        setPersonalYUnidadAHorariosServicio( servIda, vueltaDiagDTO.getServIda() );
        servicioRepository.save( servIda );
        
        
        Servicio serVt = servicioService.findServicioById( vueltaDiagDTO
                                                           .getServRet()
                                                           .getServicioPK() );
        servicioRepository.save( serVt );
        
        setPersonalYUnidadAHorariosServicio( serVt, vueltaDiagDTO.getServRet() );      
        
        
        
        VueltaDiag vuelta = new VueltaDiag();        
        vuelta.setEmpresa( vueltaDiagDTO.getEmpresa() );
        vuelta.setPeliIda( vueltaDiagDTO.getPeliIda() );
        vuelta.setVideoIda(vueltaDiagDTO.getVideoIda());
        vuelta.setPeliVta( vueltaDiagDTO.getPeliVta());
        vuelta.setVideoVta(vueltaDiagDTO.getVideoVta());
        vuelta.setServicio(servIda);
        vuelta.setServicioRet(serVt);
        
        vueltaDiagRepository.save( vuelta );    
        
        return vuelta;
    }
    
    
    
    void setPersonalYUnidadAHorariosServicio( Servicio servicio,  ServicioDTO servicioDTO ){
    	limpiarPersonalYUnidadDeHorariosSerivicio( servicio.getHorarios() );
		setChoferesAHorariosServicio( servicio,  servicioDTO.getChoferes() );
		setAuxiliaresAHorariosServicio( servicio,  servicioDTO.getChoferes() );
		setUnidadAHorariosServicio( servicio, servicioDTO.getVehiculos() );
		
	}			
		
	
	private void setUnidadAHorariosServicio( Servicio serv, Set<VehiculoEtapaDTO> vehiculos ) {
		
		vehiculos.forEach( v ->{
			serv.getHorarios().stream().filter( h -> v.getEtaDesde() <= h.getHorarioServicioPK().getEtaCodigo()
													&& h.getHorarioServicioPK().getEtaCodigo() <= v.getEtaHasta() )
									  .forEach( h -> h.setInterno( v.getVehiculoPK().getVehInterno() ) );
		} );	
		
	}	
	
	private void setChoferesAHorariosServicio( Servicio serv,  Set<ChoferEtapasDTO> choferes  ){
        
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
       
   }
	
	private void setAuxiliaresAHorariosServicio( Servicio serv,  Set<ChoferEtapasDTO> choferes  ){
        
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
       
   }
		
	
	void limpiarPersonalYUnidadDeHorariosSerivicio( List<HorarioServicio> horarios ){
		
		horarios.forEach( h ->{
			h.setChofer1(null);
			h.setChofer2(null);
			h.setAuxiliar1(null);
			h.setAuxiliar2(null);
			h.setInterno(null);
		});
		 		
	}	
    
    
    @Transactional( readOnly = false)    
    @Override
    public VueltaDiag modificarVueltaDiag(Long id, VueltaDiagDTO vueltaDiagDTO) {
        
        VueltaDiag vuelta = vueltaDiagRepository.findById( id ).orElse( null );  
        
        if( vuelta == null  ){
            throw new ResourceNotFoundException(id,"Vuelta No encontrada"); 
        }   
        
         
        Servicio servIda = servicioService.findServicioById( vueltaDiagDTO
                .getServIda()
                .getServicioPK() );

		setPersonalYUnidadAHorariosServicio( servIda, vueltaDiagDTO.getServIda() );
		servicioRepository.save( servIda );
		
		
		Servicio serVt = servicioService.findServicioById( vueltaDiagDTO
		              .getServRet()
		              .getServicioPK() );
		servicioRepository.save( serVt );
		
		setPersonalYUnidadAHorariosServicio( serVt, vueltaDiagDTO.getServRet() );
        
        
        vuelta.setEmpresa( vueltaDiagDTO.getEmpresa() );
        vuelta.setPeliIda( vueltaDiagDTO.getPeliIda() );
        vuelta.setVideoIda(vueltaDiagDTO.getVideoIda());
        vuelta.setPeliVta( vueltaDiagDTO.getPeliVta());
        vuelta.setVideoVta(vueltaDiagDTO.getVideoVta());
        vuelta.setServicioRet(serVt);        
        vueltaDiagRepository.save( vuelta );
     
        return vuelta;       
    }
    
    @Transactional( readOnly = false)    
    @Override
    public VueltaDiag deleteVueltaDiag(Long id) {
        VueltaDiag vuelta = vueltaDiagRepository.findById( id ).orElse( null ); 
        
        if( vuelta == null  ){
            throw new ResourceNotFoundException(id,"Vuelta No encontrada"); 
        }        
        vueltaDiagRepository.delete(vuelta);        
        return vuelta;
    }   
    
   

    @Override
    public List<VueltaDiag> getVueltas(String empresa, String linea, Date inicio, Date fin) {
        
        return vueltaDiagRepository.findByFechaServiciosIda(empresa, linea, inicio, fin);
        
    }

    @Override
    public void checkVueltaDiag( VueltaDiagDTO vueltaDiagDTO ) {
                
        Date inicio = vueltaDiagDTO.getServIda().getFechaHoraSalida();
        Date fin = vueltaDiagDTO.getServRet().getFechaHoraLlegada();
        
        List<VehiculoOcupacionDTO> vehiOcupacion 
                =  vehiculoService.findVehiculosOcupacionByEmpresa( vueltaDiagDTO.getEmpresa(),
                                                                    inicio, 
                                                                    fin );
        //vehiOcupacion.stream().filter( veh ->  )     
    }


    @Override
    public List<Object[]> getFullVueltas(String empresa, Date inicio, Date fin) {
        
        return vueltaDiagRepository.findAll(empresa, inicio, fin);
        
    }
    
  
   
    
}