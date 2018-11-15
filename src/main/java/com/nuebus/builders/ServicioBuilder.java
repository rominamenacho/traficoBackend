package com.nuebus.builders;

import com.nuebus.dto.ServicioDTO;
import com.nuebus.model.ServicioPK;
import java.math.BigDecimal;

/**
 *
 * @author Valeria
 */
public class ServicioBuilder {
    
    private ServicioDTO servicioDTO;
    private Object[] servicioObject; 

    public ServicioBuilder( Object[] servicio ) {
        this.servicioObject = servicio;        
    }
    
    public ServicioDTO build(){
        
        this.servicioDTO = new ServicioDTO();
        buildServicioPk();
        this.servicioDTO.setEstado( ((BigDecimal) this.servicioObject[4]).intValue());
        this.servicioDTO.setFechaHoraSalida( (java.util.Date) this.servicioObject[5] );
        this.servicioDTO.setEscalaSalida( (String) this.servicioObject[6] );        
        this.servicioDTO.setFechaHoraLlegada((java.util.Date) this.servicioObject[7] );
        this.servicioDTO.setEscalaLlegada((String) this.servicioObject[8] );      
        
        return this.servicioDTO;
    }
    
    private void buildServicioPk(){
        
        ServicioPK servicioPK = new ServicioPK();
        servicioPK.setSerEmpCodigo( (String)this.servicioObject[0] );
        servicioPK.setSerLinCodigo( (String) this.servicioObject[1] );
        servicioPK.setSerFechaHora( (java.util.Date) this.servicioObject[2] );    ;
        servicioPK.setSerRefuerzo( ((BigDecimal) this.servicioObject[3]).intValue() );
        
        
         this.servicioDTO.setServicioPK(servicioPK);
        
    }
    
}
