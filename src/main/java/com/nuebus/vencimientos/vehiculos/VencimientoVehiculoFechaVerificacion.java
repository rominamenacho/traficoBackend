
package com.nuebus.vencimientos.vehiculos;

import com.nuebus.dto.VehiculoDTO;
import com.nuebus.dto.VencimientoCalculadoDTO;
import com.nuebus.mapper.VehiculoMapper;
import com.nuebus.model.Vehiculo;
import com.nuebus.model.Vencimiento;
import com.nuebus.repository.VehiculoRepository;
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
@Qualifier("VerificacionTecnica")
public class VencimientoVehiculoFechaVerificacion implements VencimientoVehiculo{
    
    @Autowired
    VehiculoRepository vehiculoRepository;
    
    @Autowired
    VehiculoMapper vehiculoMapper;		
    
    @Override
    public List<VehiculoDTO> calcularAllVencimientosVehiculos(String vehEmpCodigo, int vehEstado, 
            Vencimiento vencimiento ) {
        
        List<Vehiculo> vehiculosVencidos = getVehiculosVencidos( vehEmpCodigo, vehEstado, vencimiento );
        
        List<VehiculoDTO> vehiculosDTO = new ArrayList<>();        
        
        vehiculosVencidos.forEach( vehiculo ->{
             VencimientoCalculadoDTO calculado = calcularVencimiento( vehiculo, vencimiento );
             if( calculado != null ){
                final VehiculoDTO vehiculoDTO = vehiculoMapper.toDTO(vehiculo);
                vehiculoDTO.getVencimientos().add( calculado );
                vehiculosDTO.add( vehiculoDTO );
             }            
        });
        return vehiculosDTO;
    }   
   
       
    @Override
    public VencimientoCalculadoDTO calcularVencimiento( Vehiculo vehiculo, Vencimiento vencimiento ) {	

        if( vehiculo == null || vehiculo.getVehVerificacionTecnicaVto() == null ) { return null;  }

        GregorianCalendar vtoFechaVerificacion = new GregorianCalendar();
        vtoFechaVerificacion.setTime( vehiculo.getVehVerificacionTecnicaVto() );   		    
        vtoFechaVerificacion.set(GregorianCalendar.HOUR_OF_DAY, 0);
        vtoFechaVerificacion.set(GregorianCalendar.MINUTE, 0);
        vtoFechaVerificacion.set(GregorianCalendar.SECOND, 0);
        vtoFechaVerificacion.set(GregorianCalendar.MILLISECOND, 0);  		 


        GregorianCalendar hoy = new GregorianCalendar();
        hoy.set(GregorianCalendar.HOUR_OF_DAY, 0);
        hoy.set(GregorianCalendar.MINUTE, 0);
        hoy.set(GregorianCalendar.SECOND, 0);
        hoy.set(GregorianCalendar.MILLISECOND, 0);  		 


        int diasAVencer= (int) ( ( vtoFechaVerificacion.getTime().getTime() -  hoy.getTime().getTime()   )/86400000);


        VencimientoCalculadoDTO calculado = new VencimientoCalculadoDTO();

        calculado.setId( vencimiento.getId() ); 
        calculado.setCantidadAnticipacion( vencimiento.getCantidadAnticipacion() ); 
        calculado.setNombreCampo( vencimiento.getTipoVencimiento().getNombreCampo() ); 	
        calculado.setDescNombreCampo( vencimiento.getTipoVencimiento().getDescNombreCampo() );		 		 
        calculado.setDiasAntesVencer(diasAVencer);
        calculado.setFechaVencimiento( vehiculo.getVehVerificacionTecnicaVto() );

       return calculado; 
    }

    @Override
    public  List<Vehiculo> getVehiculosVencidos( String empresa, int estado, Vencimiento vencimiento  ) {

        GregorianCalendar fechaControl = new GregorianCalendar();
        fechaControl.add( GregorianCalendar.DATE ,vencimiento.getCantidadAnticipacion() );   

        List<Vehiculo> vehiculos =  vehiculoRepository.getVencimientosByVerificacionTecnica(  
                                                                                        empresa, 
                                                                                        estado,
                                                                                        fechaControl.getTime() );	
        return vehiculos;
    }

  
    
}
