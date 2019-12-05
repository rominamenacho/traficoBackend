package com.nuebus.builders;

import com.nuebus.dto.ChoferOcupacionDTO;
import com.nuebus.dto.IncidenciaOcupacionDTO;
import com.nuebus.dto.ServicioOcupacionDTO;
import com.nuebus.dto.ViajeOcupacionDTO;
import static com.nuebus.model.Chofer.CHOFER;
import com.nuebus.model.ChoferPK;
import com.nuebus.model.ServicioPK;
import com.nuebus.utilidades.Utilities;
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
       String descTipo; 
       for( Object[] obj: listaObj  ){
           
           choferPK =  buildChoferPK( Utilities.getString( obj[ Ocupacion.EMPRESA.pos ] ),
                                      Utilities.getInt( obj[Ocupacion.CODIGO.pos] ) ); 
           choferPKStr = choferPK.getEmpCodigo() + String.valueOf( choferPK.getCodigo() ) ;
           
           choferOcup = new ChoferOcupacionDTO();
           choferOcup.setChoferPK(  choferPK  );
           choferOcup.setNombre(  Utilities.getString( obj[Ocupacion.NOMBRE.pos] ) );
           choferOcup.setTipoChofer( Utilities.getInt( obj[Ocupacion.TIPO_CHOFER.pos] ) );
           choferOcup.setEstado( Utilities.getInt(obj[Ocupacion.ESTADO.pos] ) );
           descTipo = ( choferOcup.getTipoChofer() == CHOFER )? "(CHO)":"(AUX)";
           choferOcup.setNombreConTipo( descTipo + choferOcup.getNombre() );           
           choferOcup.setCho_id_aux( Utilities.getInt( obj[Ocupacion.ID_AUX.pos] ) );
           
           
            if( !mapaCho.containsKey(choferPKStr) ){ mapaCho.put( choferPKStr, choferOcup ); }          
           
            int tipo = Utilities.getInt( obj[Ocupacion.TIPO.pos] );             
               
            if( tipo == SERVICIOS ){ //servicios
                ServicioOcupacionDTO servicio =   buildServicio( obj );               
                mapaCho.computeIfPresent( choferPKStr, ( key , value )  -> { value.getServicios().add(servicio); return value; } );                
            }else if( tipo  == INCIDENCIAS ) {
                IncidenciaOcupacionDTO inc =  buildIncidencia( obj );
                mapaCho.computeIfPresent( choferPKStr, ( key , value )  -> { value.getIncidencias().add(inc); return value; } );                
            }else if( tipo == VIAJES ) {
                ViajeOcupacionDTO viaje = buildViaje( obj );
                mapaCho.computeIfPresent( choferPKStr, ( key , value )  -> { value.getViajes().add(viaje); return value; } );                
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
        inci.setIdIncidencia(  Utilities.getLong( obj[Ocupacion.ID.pos]) );
        inci.setInicio( Utilities.getDate( obj[Ocupacion.INICIO.pos] ) );
        inci.setFin( Utilities.getDate( obj[Ocupacion.FIN.pos] ) );
        return inci;
    }
    
    private ViajeOcupacionDTO buildViaje( Object[] obj ){
        
          /* "  cho.cho_emp_codigo, cho.cho_codigo, cho.cho_nombre, ocup.tipo, ocup.id, ocup.emp_codigo, ocup.ser_emp_codigo,"
                  + " ocup.ser_lin_codigo, ocup.ser_fecha_hora, ocup.ser_refuerzo, ocup.inicio, ocup.fin "*/
          
          ViajeOcupacionDTO viaje = new ViajeOcupacionDTO();
          viaje.setIdViaje( Utilities.getLong( obj[Ocupacion.ID.pos] ) );
          viaje.setInicio( Utilities.getDate( obj[Ocupacion.INICIO.pos] ) );
          viaje.setFin( Utilities.getDate( obj[Ocupacion.FIN.pos] ) ); 
          
          return viaje;    
    }
    
    private ServicioOcupacionDTO buildServicio(  Object[] obj ){
        
        /* "  cho.cho_emp_codigo, cho.cho_codigo, cho.cho_nombre, ocup.tipo, ocup.id, ocup.emp_codigo, ocup.ser_emp_codigo,"
                  + " ocup.ser_lin_codigo, ocup.ser_fecha_hora, ocup.ser_refuerzo, ocup.inicio, ocup.fin "*/
        
        ServicioOcupacionDTO servicio = new ServicioOcupacionDTO();       
        
        ServicioPK servicioPK = new ServicioPK();
        servicioPK.setSerEmpCodigo( Utilities.getString( obj[Ocupacion.EMPRESA_SERV.pos] ) );
        servicioPK.setSerLinCodigo( Utilities.getString( obj[Ocupacion.LINEA_SERV.pos] ) );
        servicioPK.setSerFechaHora( Utilities.getDate( obj[Ocupacion.FECHA_SERV.pos] ) );   
        servicioPK.setSerRefuerzo(  Utilities.getInt( obj[Ocupacion.REFUERZO_SERV.pos] ) );
        
        servicio.setServicioPK(servicioPK);
        servicio.setFechaHoraSalida( Utilities.getDate( obj[Ocupacion.INICIO.pos] ) );
        servicio.setFechaHoraLlegada( Utilities.getDate( obj[Ocupacion.FIN.pos] ) );
        
        return   servicio;        
    
    }
    
    private enum Ocupacion {
        EMPRESA(0), CODIGO(1), NOMBRE(2), TIPO_CHOFER(3), TIPO(4), ID(5),
        EMPRESA_SERV(7), LINEA_SERV(8), FECHA_SERV(9), REFUERZO_SERV(10),
        INICIO(11), FIN(12), ESTADO(13), ID_AUX(14);           
        
        private Ocupacion( int pos){
            this.pos = pos;
        }
        
        public int pos=-1;
    }
    
   
    
}
