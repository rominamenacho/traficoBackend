package com.nuebus.helpers;

import com.nuebus.dto.ChoferOcupacionDTO;
import com.nuebus.dto.ServicioDTO;
import com.nuebus.dto.VueltaDiagDTO;
import com.nuebus.service.ChoferService;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Valeria
 */

@Component
public class CheckVueltaChoferes extends CheckVuelta{  
    
    
    @Autowired
    ChoferService choferService;    
    public List<ChoferOcupacionDTO> choOcupacion;
    
    public CheckVueltaChoferes(){
        
    }   

    @Override
    void check(VueltaDiagDTO vuelta, Map<String, Set<String>> errores) {
        
        this.vuelta = vuelta;
        this.errores = errores;         
        this.choOcupacion = 
            this.choferService.findPersonalOcupacionByEmpresa( this.vuelta.getEmpresa(),
                                                               this.vuelta.getServIda().getFechaHoraSalida(),
                                                               this.vuelta.getServRet().getFechaHoraLlegada() );               
        
        checkChoferes(  vuelta.getServIda() );
        checkChoferes(  vuelta.getServRet() );        
    }
    
     public void checkChoferes(  ServicioDTO servDTO) {
        
                
        servDTO.getChoferes().forEach( cho ->{                  
            Optional<ChoferOcupacionDTO> chofer = choOcupacion.stream()
                    .filter( c -> c.getChoferPK().equals( cho.getChoferPK() ) )                   
                    .findAny();            
            
            if ( chofer.isPresent() ){ 
                
                ChoferOcupacionDTO ch = chofer.get();
                String descChofer = "Chofer => " + ch.getNombreConTipo();
                
                generarErroresServicio( descChofer,                             
                                                getServiciosConConflicto( servDTO.getServicioPK(),
                                                                       servDTO.getFechaHoraSalida(),
                                                                       servDTO.getFechaHoraLlegada(),
                                                                       ch.getServicios() ) );
                ////// Incidencias  Conflictos //////////////                
                generarErroresIncidencia( descChofer,                 
                                                getIncidConConflicto( servDTO.getFechaHoraSalida(),
                                                                      servDTO.getFechaHoraLlegada(),
                                                                      ch.getIncidencias() ) );
              
                ////// Viajes Especiales Conflictos ///////////////                
                generarErroresViajeEspecial( descChofer ,
                                                getViajesConConflicto( servDTO.getFechaHoraSalida(),
                                                                       servDTO.getFechaHoraLlegada(),
                                                                       ch.getViajes() ) );
            }
            
        });
    }   
    
}
