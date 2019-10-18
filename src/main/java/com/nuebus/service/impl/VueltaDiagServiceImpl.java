package com.nuebus.service.impl;

import com.nuebus.dto.ServicioDTO;
import com.nuebus.dto.VehiculoOcupacionDTO;
import com.nuebus.dto.VueltaDiagDTO;
import com.nuebus.excepciones.ResourceNotFoundException;

import com.nuebus.model.HorarioServicio;
import com.nuebus.model.HorarioServicioPK;
import com.nuebus.model.Servicio;
import com.nuebus.model.VueltaDiag;
import com.nuebus.repository.HorarioServicioRepository;
import com.nuebus.repository.ServicioRepository;
import com.nuebus.repository.VueltaDiagRepository;
import com.nuebus.service.ServicioService;
import com.nuebus.service.VehiculoService;
import com.nuebus.service.VueltaDiagService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    
    @Autowired
    HorarioServicioRepository horarioServicioRepository;
    
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
        
        setPersonalYUnidadAHorariosServicio( serVt, vueltaDiagDTO.getServRet() );
        servicioRepository.save( serVt );      
        
        
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
    	
    	// nose si limpiar los horarios el servicios 
    	
    	List<HorarioServicio> horarios = new ArrayList<>();
    	
    	servicioDTO.getHorarios().forEach( h -> {
    		HorarioServicioPK horarioPK = new HorarioServicioPK( h.getCodigoEtapa(), servicio ); 
    		HorarioServicio unHorario = horarioServicioRepository.findById(horarioPK).orElse(null);
    		if( unHorario != null ){
    			
    			unHorario.setChofer1( h.getChofer1() );
    			unHorario.setChofer2( h.getChofer2() );
    			unHorario.setAuxiliar1( h.getAuxiliar1() );
    			unHorario.setAuxiliar2( h.getAuxiliar2() );
    			unHorario.setInterno( h.getInterno());
    			
    			horarios.add( unHorario );
    			
    		}
    	}); 
    	
		servicio.getHorarios().clear();
		servicio.setHorarios(horarios);		
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
		
		
		setPersonalYUnidadAHorariosServicio( serVt, vueltaDiagDTO.getServRet() );
		
		servicioRepository.save( serVt );
        
        
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