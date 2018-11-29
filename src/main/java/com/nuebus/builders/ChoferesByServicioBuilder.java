
package com.nuebus.builders;

import static com.nuebus.builders.ChoferOcupacionBuilder.SERVICIOS;
import com.nuebus.dto.ChoferEtapasDTO;
import com.nuebus.dto.ChoferPKDTO;
import com.nuebus.model.ServicioPK;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Valeria
 */
public class ChoferesByServicioBuilder {
    
    static final int CHOFER = 1;
    static final int VEHICULO = 0;
    
    private List<Object[]> objetos;
    Map< ServicioPK, Set<ChoferEtapasDTO> > choferes = new HashMap<>() ;
     
    public ChoferesByServicioBuilder( List<Object[]> objetos  ){
         this.objetos = objetos;  
    }
     
    public Map< ServicioPK, Set<ChoferEtapasDTO> > build(){
        
         ServicioPK servicioPK;
         for( Object[] obj: this.objetos ){
             
            if( ((BigDecimal) obj[7]).intValue() == CHOFER  ){                
                servicioPK =  buildServicioPk( obj );   
                ChoferEtapasDTO cho = builChofer( obj );
                choferes.computeIfPresent( servicioPK,( key, value ) -> { value.add( cho ); return value; } );
                choferes.computeIfAbsent( servicioPK, key -> new HashSet<>()).add(cho );           
            }             
         }
     
        return choferes; 
    }
     
    private ServicioPK buildServicioPk( Object[] obj ){
        
        ServicioPK servicioPK = new ServicioPK();
        servicioPK.setSerEmpCodigo( (String)obj[0] );
        servicioPK.setSerLinCodigo( (String) obj[1] );
        servicioPK.setSerFechaHora( (java.util.Date) obj[2] ); 
        servicioPK.setSerRefuerzo( ((BigDecimal) obj[3]).intValue() );        
        
        return servicioPK;
    }
     
    private ChoferEtapasDTO builChofer( Object[] obj ){                  
         
         ChoferEtapasDTO chofer = new ChoferEtapasDTO();         
         
         ChoferPKDTO choferPK = new ChoferPKDTO();
         choferPK.setCho_emp_codigo( (String)obj[0]  );
         choferPK.setCho_codigo(((BigDecimal) obj[4]).intValue());       
         chofer.setChoferPK(choferPK);
         
         chofer.setEtaDesde( ((BigDecimal) obj[8]).intValue() );
         chofer.setEtaHasta( ((BigDecimal) obj[9]).intValue() );
         
         return chofer;                      
    }
    
}
