package com.nuebus.vencimientos.choferes;


import com.nuebus.dto.ChoferDTO;
import com.nuebus.dto.VencimientoCalculadoDTO;
import com.nuebus.enumeraciones.TipoCarnet;
import com.nuebus.mapper.ChoferMapper;
import com.nuebus.model.Carnet;
import com.nuebus.model.Chofer;
import com.nuebus.model.Vencimiento;
import com.nuebus.repository.ChoferRepository;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author Valeria
 */
@Component
@Qualifier("CarnetProvincial")
public class VencimientoChoferCarnetProvincial implements VencimientoChofer{

    @Autowired
    ChoferRepository choferRepository;
    
    @Autowired 
    ChoferMapper choferMapper;
    
    TipoCarnet tipoCarnet = TipoCarnet.PROVINCIAL;
       

    @Override
    public List<ChoferDTO> calcularVencimiento(String empresa, int estadoChofer, Vencimiento vencimiento ) {      
        
        GregorianCalendar fechaControl = new GregorianCalendar();
        fechaControl.add( GregorianCalendar.DATE , vencimiento.getCantidadAnticipacion() );

        List< Carnet > carnets = choferRepository.getCarnetsVencidosByTipo(  empresa,		       								 
                                                                             tipoCarnet.tipo,
                                                                             estadoChofer,
                                                                             fechaControl.getTime() );       

        List<ChoferDTO> choferesDTO = new ArrayList<>();

        carnets.forEach( carnet ->{                
            Chofer chofer = carnet.getChofer();
            if( chofer != null ) {                   
                VencimientoCalculadoDTO calculado = calcularVencimientos(  carnet,  vencimiento );                
                if( calculado != null ){
                     ChoferDTO choferDTO = choferMapper.toDTO( chofer );
                     choferDTO.getVencimientos().add(  calcularVencimientos(  carnet,  vencimiento )  );
                    choferesDTO.add( choferDTO );   
                }                             
            }
        });		   
        return  choferesDTO;
        
    }
    
    public VencimientoCalculadoDTO calcularVencimientos( Carnet carnet, Vencimiento vencimiento ) {       

        if( carnet == null || carnet.getFechaEmision() == null ) {
                return null;
        }		

         GregorianCalendar vencimientoCarnet = new GregorianCalendar();
         vencimientoCarnet.setTime( carnet.getFechaVenc() );   		    
         vencimientoCarnet.set(GregorianCalendar.HOUR_OF_DAY, 0);
         vencimientoCarnet.set(GregorianCalendar.MINUTE, 0);
         vencimientoCarnet.set(GregorianCalendar.SECOND, 0);
         vencimientoCarnet.set(GregorianCalendar.MILLISECOND, 0);	  		 

         GregorianCalendar hoy = new GregorianCalendar();
         hoy.set(GregorianCalendar.HOUR_OF_DAY, 0);
         hoy.set(GregorianCalendar.MINUTE, 0);
         hoy.set(GregorianCalendar.SECOND, 0);
         hoy.set(GregorianCalendar.MILLISECOND, 0);  		 		   		 

         int diasAVencer= (int) ( ( vencimientoCarnet.getTime().getTime() -  hoy.getTime().getTime()   )/86400000);   		

         VencimientoCalculadoDTO calculado = new VencimientoCalculadoDTO();

         calculado.setId( vencimiento.getId() ); 
         calculado.setCantidadAnticipacion( vencimiento.getCantidadAnticipacion() ); 
         calculado.setNombreCampo( vencimiento.getTipoVencimiento().getNombreCampo() ); 	
         calculado.setDescNombreCampo( vencimiento.getTipoVencimiento().getDescNombreCampo() );		 		 
         calculado.setDiasAntesVencer(diasAVencer);
         calculado.setFechaVencimiento( carnet.getFechaVenc() );
         
         return calculado;		
    }		   
    
}
