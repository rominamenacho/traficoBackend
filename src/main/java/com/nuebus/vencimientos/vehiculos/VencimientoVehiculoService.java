package com.nuebus.vencimientos.vehiculos;

import com.nuebus.dto.VehiculoDTO;
import com.nuebus.dto.VencimientoCalculadoDTO;
import com.nuebus.dto.VencimientosVehiculoDTO;
import com.nuebus.model.Vehiculo;
import com.nuebus.model.Vencimiento;
import com.nuebus.service.VencimientoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Valeria
 */
@Service
public class VencimientoVehiculoService {
    
    @Autowired    
    VencimientoVehiculoFechaVerificacion  verificacionTecnica;
        
    @Autowired
    VencimientoService vencimientoService;
    
    public List<VencimientosVehiculoDTO> calcularAllVencimientosVehiculos( String empresa, int estadoVehiculo ){
		 		 		
        List<VencimientosVehiculoDTO> vencimientosRespuestas = new ArrayList<>();
        List<Vencimiento> vencimientosVehiculos = getVencimientosByVehiculos( empresa,
                                                                              estadoVehiculo == Vehiculo.HABILITADO );
	for( Vencimiento vencimiento: vencimientosVehiculos ){
    		 
            if( isVencimientoFechaVerificacion( vencimiento ) ){
                
                 List<VehiculoDTO> vehiculosDTO = verificacionTecnica.calcularAllVencimientosVehiculos(
                                                    empresa, estadoVehiculo, vencimiento);
                 
                 if( !vehiculosDTO.isEmpty() ){
                      vencimientosRespuestas.add( new VencimientosVehiculoDTO( vencimiento, vehiculosDTO) );
                 }
            }
        }	
	return vencimientosRespuestas;
    }
   
       
    public  List<VencimientoCalculadoDTO> getVencimientosCalculado( List<Vencimiento> vencimientosVehiculos,
            Vehiculo vehiculo ){
        
        List<VencimientoCalculadoDTO> vencimientosCalculados = new ArrayList<>();
        
        for( Vencimiento vencimiento: vencimientosVehiculos ){
    		 
            if( isVencimientoFechaVerificacion( vencimiento ) ){
                
                 VencimientoCalculadoDTO calculado = verificacionTecnica.calcularVencimiento( vehiculo, vencimiento );                 
                 if( calculado != null ){
                      vencimientosCalculados.add( calculado );
                 }
            }
        }        
        return vencimientosCalculados;
    }
    
    
    public List<Vencimiento> getVencimientosByVehiculos( String empresa, boolean activo ) {
            List<Vencimiento> vencimientosVehiculos = vencimientoService
                              .getVencimientosByEmpresaAndNombreEntidad( empresa, 
                                                                         VencimientoVehiculo.ENTITDAD_VEHICULO,
                                                                          activo );
            return vencimientosVehiculos;		
    }
    
    public List<Vencimiento> getVencimientosActivosByVehiculos( String empresa ) {
         return getVencimientosByVehiculos( empresa, VencimientoVehiculo.VENCIMIENTO_ACTIVO );		
    }
    
    private boolean isVencimientoFechaVerificacion( Vencimiento vencimiento ){
        
        return    vencimiento != null 
                 && vencimiento.getTipoVencimiento() != null 
                 && vencimiento.getTipoVencimiento().getNombreCampo().equals( VencimientoVehiculo.FECHA_VERIFICACION_CAMPO_VEHICULO ); 
    
    }    
    
}
