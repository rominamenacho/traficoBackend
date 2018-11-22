package com.nuebus.builders;

import com.nuebus.dto.ChoferOcupacionDTO;
import com.nuebus.dto.IncidenciaOcupacionDTO;
import com.nuebus.dto.ServicioDTO;
import com.nuebus.dto.ViajeOcupacionDTO;
import com.nuebus.model.ChoferPK;
import com.nuebus.model.ServicioPK;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Valeria
 */

public class ChoferOcupacionBuilder {
    
    //private ChoferOcupacionDTO choferOcup;

    public static int SERVICIOS =1;
    public static int INCIDENCIAS =2;
    public static int VIAJES =3;
    
    List<Object[]> listaObj;

    public ChoferOcupacionBuilder( List<Object[]> listaObj ) {        
        this.listaObj = listaObj;        
    } 
    
    
    public List<ChoferOcupacionDTO>  build( ){
        
       ChoferPK  choferPK;     
       String  choferPKStr;     
       ChoferOcupacionDTO choferOcup;     
       Map< String, ChoferOcupacionDTO > mapaCho = new HashMap<>();
        
       for( Object[] obj: listaObj  ){
           choferPK =  buildChoferPK( ((String) obj[0]).trim(),( (BigDecimal) obj[1]).intValue() ); 
           choferPKStr = choferPK.getCho_emp_codigo() + String.valueOf( choferPK.getCho_codigo() ) ;
           
           choferOcup = new ChoferOcupacionDTO();
           choferOcup.setChoferPK(  choferPK  );
           choferOcup.setNombre( ((String) obj[2]).trim() );
           
           if( !mapaCho.containsKey(choferPKStr) ){ mapaCho.put( choferPKStr, choferOcup ); }          
           
           if( obj[3] != null ){               
               if( ((BigDecimal) obj[3]).intValue() == SERVICIOS ){ //servicios
                   ServicioDTO servicio =   buildServicio( obj );               
                   mapaCho.computeIfPresent( choferPKStr, ( key , value )  -> { value.getServicios().add(servicio); return value; } );                
               }else if( ((BigDecimal) obj[3]).intValue() == INCIDENCIAS ) {
                   IncidenciaOcupacionDTO inc =  buildIncidencia( obj );
                   mapaCho.computeIfPresent( choferPKStr, ( key , value )  -> { value.getIncidencias().add(inc); return value; } );                
               }else if( ((BigDecimal) obj[3]).intValue() == VIAJES ) {
                   ViajeOcupacionDTO viaje = buildViaje( obj );
                   mapaCho.computeIfPresent( choferPKStr, ( key , value )  -> { value.getViajes().add(viaje); return value; } );                
               }
               
           }                              
       }
       
       List<ChoferOcupacionDTO> lista =  mapaCho.values().stream().collect(Collectors.toList()) ;    
       
       return lista;
    }      
    
    ChoferPK buildChoferPK( String empresa, long choCodigo ){        
       return new ChoferPK( empresa, choCodigo);
    }   
   
    
    private IncidenciaOcupacionDTO buildIncidencia( Object[] obj ){        
        IncidenciaOcupacionDTO inci = new IncidenciaOcupacionDTO();
        inci.setIdIncidencia( ((BigDecimal)obj[4]).longValue()  );
        inci.setInicio((java.util.Date) obj[10] );
        inci.setFin((java.util.Date) obj[11] );
        return inci;
    }
    
    private ViajeOcupacionDTO buildViaje( Object[] obj ){
        
          /* "  cho.cho_emp_codigo, cho.cho_codigo, cho.cho_nombre, ocup.tipo, ocup.id, ocup.emp_codigo, ocup.ser_emp_codigo,"
                  + " ocup.ser_lin_codigo, ocup.ser_fecha_hora, ocup.ser_refuerzo, ocup.inicio, ocup.fin "*/
          
          ViajeOcupacionDTO viaje = new ViajeOcupacionDTO();
          viaje.setIdViaje(((BigDecimal)obj[4]).longValue() );
          viaje.setInicio((java.util.Date) obj[10] );
          viaje.setFin((java.util.Date) obj[11] ); 
          
          return viaje;    
    }
    
    private ServicioDTO buildServicio(  Object[] obj ){
        
        /* "  cho.cho_emp_codigo, cho.cho_codigo, cho.cho_nombre, ocup.tipo, ocup.id, ocup.emp_codigo, ocup.ser_emp_codigo,"
                  + " ocup.ser_lin_codigo, ocup.ser_fecha_hora, ocup.ser_refuerzo, ocup.inicio, ocup.fin "*/
        
        ServicioDTO servicio = new ServicioDTO();       
        
        ServicioPK servicioPK = new ServicioPK();
        servicioPK.setSerEmpCodigo( (String)obj[6] );
        servicioPK.setSerLinCodigo( (String) obj[7] );
        servicioPK.setSerFechaHora( (java.util.Date) obj[8] );   
        servicioPK.setSerRefuerzo( ((BigDecimal) obj[9]).intValue() );
        
        servicio.setServicioPK(servicioPK);
        servicio.setFechaHoraSalida( (java.util.Date) obj[10] );
        servicio.setFechaHoraLlegada((java.util.Date) obj[11] );
        
        return   servicio;        
    
    }
    
}
