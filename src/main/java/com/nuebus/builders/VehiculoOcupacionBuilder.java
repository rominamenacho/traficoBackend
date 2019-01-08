
package com.nuebus.builders;

import com.nuebus.dto.IncidenciaOcupacionDTO;
import com.nuebus.dto.ServicioOcupacionDTO;
import com.nuebus.dto.VehiculoOcupacionDTO;
import com.nuebus.dto.ViajeOcupacionDTO;
import com.nuebus.model.ServicioPK;
import com.nuebus.model.VehiculoPK;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Valeria
 */
public class VehiculoOcupacionBuilder extends OcupacionBuilder{
    
    public VehiculoOcupacionBuilder(  List<Object[]> listaObj ){
        this.listaObj = listaObj;        
    }
    
    public List<VehiculoOcupacionDTO>  build( ){
        
        Map< String, VehiculoOcupacionDTO> mapVehiculos = new HashMap<>();
        VehiculoOcupacionDTO vehiDTO = null;
        VehiculoPK vehiculoPK;
        String vehiculoPKStr;
        
        for ( Object[] obj:  this.listaObj ) {
            
            vehiculoPK = buildVehiculoPK( ((String) obj[0]).trim(),( (BigDecimal) obj[1]).intValue() );
            vehiculoPKStr = vehiculoPK.getVehEmpCodigo() + String.valueOf( vehiculoPK.getVehInterno() );
            
            vehiDTO = new VehiculoOcupacionDTO();
            vehiDTO.setVehiculoPK(vehiculoPK);
            vehiDTO.setPatente( ((String) obj[2]) );
            vehiDTO.setEstado(  ( (BigDecimal) obj[3]).intValue() );
            
            if( !mapVehiculos.containsKey(vehiculoPKStr) ){ mapVehiculos.put( vehiculoPKStr, vehiDTO ); }
            
            
            if( obj[4] != null ){               
               if( ((BigDecimal) obj[4]).intValue() == SERVICIOS ){ //servicios
                   ServicioOcupacionDTO servicio =   buildServicio( obj );               
                   mapVehiculos.computeIfPresent( vehiculoPKStr, ( key , value )  -> { value.getServicios().add(servicio); return value; } );                
               }else if( ((BigDecimal) obj[4]).intValue() == INCIDENCIAS ) {
                   IncidenciaOcupacionDTO inc =  buildIncidencia( obj );
                   mapVehiculos.computeIfPresent( vehiculoPKStr, ( key , value )  -> { value.getIncidencias().add(inc); return value; } );                
               }else if( ((BigDecimal) obj[4]).intValue() == VIAJES ) {
                   ViajeOcupacionDTO viaje = buildViaje( obj );
                   mapVehiculos.computeIfPresent( vehiculoPKStr, ( key , value )  -> { value.getViajes().add(viaje); return value; } );                
               }               
           }          
        }
        
        List<VehiculoOcupacionDTO> vehiculos = mapVehiculos.values().stream().collect( Collectors.toList() );        
        return vehiculos;
    }
    
    VehiculoPK buildVehiculoPK( String empresa, int vehInterno ){
        return new VehiculoPK( empresa, vehInterno );
    }
    
      private IncidenciaOcupacionDTO buildIncidencia( Object[] obj ){        
        IncidenciaOcupacionDTO inci = new IncidenciaOcupacionDTO();
        inci.setIdIncidencia(  ((BigDecimal)obj[5]).longValue()  );
        inci.setInicio((java.util.Date) obj[11] );
        inci.setFin((java.util.Date) obj[12] );
        return inci;
    }
    
    private ViajeOcupacionDTO buildViaje( Object[] obj ){
        
        ViajeOcupacionDTO viaje = new ViajeOcupacionDTO();
        viaje.setIdViaje(((BigDecimal)obj[5]).longValue() );
        viaje.setInicio((java.util.Date) obj[11] );
        viaje.setFin((java.util.Date) obj[12] );           
        return viaje;    
    }
    
    private ServicioOcupacionDTO buildServicio(  Object[] obj ){
        
        ServicioOcupacionDTO servicio = new ServicioOcupacionDTO();       
        
        ServicioPK servicioPK = new ServicioPK();
        servicioPK.setSerEmpCodigo( (String)obj[7] );
        servicioPK.setSerLinCodigo( (String) obj[8] );
        servicioPK.setSerFechaHora( (java.util.Date) obj[9] );   
        servicioPK.setSerRefuerzo( ((BigDecimal) obj[10]).intValue() );
        
        servicio.setServicioPK(servicioPK);
        servicio.setFechaHoraSalida( (java.util.Date) obj[11] );
        servicio.setFechaHoraLlegada((java.util.Date) obj[12] );        
        return   servicio;            
    }
    
    
    
}
