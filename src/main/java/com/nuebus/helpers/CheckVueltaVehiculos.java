
package com.nuebus.helpers;

import com.nuebus.dto.ServicioDTO;
import com.nuebus.dto.VehiculoOcupacionDTO;
import com.nuebus.dto.VueltaDiagDTO;
import com.nuebus.service.VehiculoService;
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
public class CheckVueltaVehiculos extends CheckVuelta {
    
    @Autowired
    VehiculoService vehiculoService;
   
    
    public List<VehiculoOcupacionDTO> vehiOcupacion;

    public CheckVueltaVehiculos() {
        super();
    }
    
    @Override
    public void check( VueltaDiagDTO vuelta, Map< String, Set<String>> errores ){
        
        this.vuelta = vuelta;
        this.errores = errores;            
        
        this.vehiOcupacion = vehiculoService.
                 findVehiculosOcupacionByEmpresa( this.vuelta.getEmpresa(),
                                                   this.vuelta.getServIda().getFechaHoraSalida(),
                                                   this.vuelta.getServRet().getFechaHoraLlegada() );               
        checkVehiculos( this.vuelta.getServIda() );
        checkVehiculos( this.vuelta.getServRet() );    
    }
    
    private void checkVehiculos( ServicioDTO servDTO ){
        
        servDTO.getVehiculos().forEach( veh ->{
        
            Optional<VehiculoOcupacionDTO> unVehi = vehiOcupacion
                    .stream()
                    .filter( resp -> resp.getVehiculoPK().equals( veh.getVehiculoPK() ) )
                    .findAny();
            
            if( unVehi.isPresent() ){
            
                VehiculoOcupacionDTO v = unVehi.get();   
                
                String descVehiculo = "Unidad => " + String.valueOf( v.getVehiculoPK().getVehInterno() );
                
                ////// Servicios Conflictos ///////////////
                generarErroresServicio( descVehiculo,                             
                                        getServiciosConConflicto( servDTO.getServicioPK(),
                                                                  servDTO.getFechaHoraSalida(),
                                                                  servDTO.getFechaHoraLlegada(),
                                                                  v.getServicios() ) );                   
                
                ////// Incidencias  Conflictos //////////////                
                generarErroresIncidencia( descVehiculo,                 
                                          getIncidConConflicto( servDTO.getFechaHoraSalida(),
                                                                servDTO.getFechaHoraLlegada(),
                                                                v.getIncidencias() ) );
                
                ////// Viajes Especiales Conflictos ///////////////                
                generarErroresViajeEspecial( descVehiculo,
                                                     getViajesConConflicto( servDTO.getFechaHoraSalida(),
                                                                            servDTO.getFechaHoraLlegada(),
                                                                            v.getViajes() ) );            
            }            
        });
    }
    
    
}
