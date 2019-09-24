package com.nuebus.builders;


import com.nuebus.dto.ChoferEtapasDTO;
import com.nuebus.dto.ServicioDTO;
import com.nuebus.dto.VehiculoEtapaDTO;
import static com.nuebus.model.Chofer.CHOFER;
import com.nuebus.model.ChoferPK;
import com.nuebus.model.ServicioPK;
import com.nuebus.model.VehiculoPK;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Valeria
 */
public class ServicioBuilder {
    
   public static int VISTA_CHOFER = 1;
   public static int VISTA_UNIDAD = 0;
    
    
    private List<Object[]> serviciosObj;
    private List<Object[]> choferesVehiculosObj;

    public ServicioBuilder( List<Object[]> serviciosObj, List<Object[]> choferesVehiculosObj  ) {
        this.serviciosObj = serviciosObj;
        this.choferesVehiculosObj = choferesVehiculosObj;        
    }
    
    public  List<ServicioDTO> build(){      
        
        List<ServicioDTO> serviciosDTO = new ArrayList<>();
        
        Map< ServicioPK, Set<VehiculoEtapaDTO>>  vehiculos = buildVehiculos();        
        Map< ServicioPK, Set<ChoferEtapasDTO> > choferes = buildChoferes();
        
        for( Object[] obj: this.serviciosObj ){            
            ServicioDTO servDTO = new ServicioDTO();
            servDTO.setServicioPK( buildServicioPk( obj ) );
            servDTO.setEstado( ((BigDecimal) obj[4]).intValue());
            servDTO.setFechaHoraSalida( (java.util.Date) obj[5] );
            servDTO.setEscSalida( (String) obj[6] );        
            servDTO.setFechaHoraLlegada((java.util.Date) obj[7] );
            servDTO.setEscLlegada((String) obj[8] );    

            servDTO.setEtaInicio( ((BigDecimal) obj[9]).intValue() );
            servDTO.setEtaFin( ((BigDecimal) obj[10]).intValue() );
            serviciosDTO.add( servDTO );   
            
            if( choferes.containsKey(  servDTO.getServicioPK() ) ){
                servDTO.setChoferes(  choferes.get( servDTO.getServicioPK() ) );
            }
            
            if( vehiculos.containsKey(  servDTO.getServicioPK()  ) ){
                servDTO.setVehiculos( vehiculos.get( servDTO.getServicioPK() ) );            
            }
            
        }        
        return serviciosDTO;
    }
    
    private ServicioPK buildServicioPk( Object[] obj ){
        
        ServicioPK servicioPK = new ServicioPK();
        servicioPK.setSerEmpCodigo( (String)obj[0] );
        servicioPK.setSerLinCodigo( (String) obj[1] );
        servicioPK.setSerFechaHora( (java.util.Date) obj[2] ); 
        servicioPK.setSerRefuerzo( ((BigDecimal) obj[3]).intValue() );        
        
        return servicioPK;
    }
    
   public Map< ServicioPK, Set<ChoferEtapasDTO> > buildChoferes(  ){
       
         Map< ServicioPK, Set<ChoferEtapasDTO> > choferes = new HashMap<>() ;        
         ServicioPK servicioPK;
         for( Object[] obj: this.choferesVehiculosObj ){
             
            if( ((BigDecimal) obj[7]).intValue() == VISTA_CHOFER  ){                
                servicioPK =  buildServicioPk( obj );   
                ChoferEtapasDTO cho = builUnChofer( obj );
                choferes.computeIfPresent( servicioPK,( key, value ) -> { value.add( cho ); return value; } );
                choferes.computeIfAbsent( servicioPK, key -> new HashSet<>()).add(cho );           
            }             
         }
     
        return choferes; 
    }
   
   private ChoferEtapasDTO builUnChofer( Object[] obj ){                  
         
         ChoferEtapasDTO chofer = new ChoferEtapasDTO();         
         
         ChoferPK choferPK = new ChoferPK();
         choferPK.setEmpCodigo( (String)obj[0]  );
         choferPK.setCodigo(((BigDecimal) obj[4]).intValue());       
         chofer.setChoferPK(choferPK);
         
         chofer.setEtaDesde( ((BigDecimal) obj[8]).intValue() );
         chofer.setEtaHasta( ((BigDecimal) obj[9]).intValue() );
         chofer.setNombre(((String) obj[10]));
         chofer.setTipoChofer(((BigDecimal) obj[11]).intValue());         
         String descTipo = ( chofer.getTipoChofer() == CHOFER )? "(CHO)":"(AUX)";
         chofer.setNombreConTipo( descTipo + chofer.getNombre() );          
         
         return chofer;                      
    }
   
   public Map< ServicioPK, Set<VehiculoEtapaDTO> > buildVehiculos(  ){
       
         Map< ServicioPK, Set<VehiculoEtapaDTO> > vehiculos = new HashMap<>() ;        
         ServicioPK servicioPK;
         for( Object[] obj: this.choferesVehiculosObj ){
             
            if( ((BigDecimal) obj[7]).intValue() == VISTA_UNIDAD  ){                
                servicioPK =  buildServicioPk( obj );   
                VehiculoEtapaDTO vehi = builUnVehiculo( obj );
                vehiculos.computeIfPresent( servicioPK,( key, value ) -> { value.add( vehi ); return value; } );
                vehiculos.computeIfAbsent( servicioPK, key -> new HashSet<>()).add(vehi );           
            }             
         }
     
        return vehiculos; 
    }
   
   private VehiculoEtapaDTO builUnVehiculo( Object[] obj ){                  
         
         VehiculoEtapaDTO vehi = new VehiculoEtapaDTO();         
         
         VehiculoPK vehiPK = new VehiculoPK();
         vehiPK.setVehEmpCodigo((String)obj[0] );
         vehiPK.setVehInterno(((BigDecimal) obj[4]).intValue());       
         vehi.setVehiculoPK(vehiPK);
         
         vehi.setEtaDesde( ((BigDecimal) obj[8]).intValue() );
         vehi.setEtaHasta( ((BigDecimal) obj[9]).intValue() );
         
         return vehi;                      
    }
    
    
}
