package com.nuebus.vencimientos.vehiculos;

import com.nuebus.dto.VehiculoDTO;
import com.nuebus.dto.VencimientoCalculadoDTO;
import com.nuebus.model.Vehiculo;
import com.nuebus.model.Vencimiento;
import java.util.List;

/**
 *
 * @author Valeria
 */
public interface VencimientoVehiculo {
    
    public final static String ENTITDAD_VEHICULO = "Vehiculo";	
    public final static String FECHA_VERIFICACION_CAMPO_VEHICULO = "vehVerificacionTecnica";
    public final static boolean VENCIMIENTO_ACTIVO = true;
    
    public VencimientoCalculadoDTO calcularVencimiento( Vehiculo vehiculo, Vencimiento vencimiento );	
    public List<Vehiculo> getVehiculosVencidos(  String empresa, int estado, Vencimiento vencimiento );
    public List<VehiculoDTO>  calcularAllVencimientosVehiculos( String vehEmpCodigo, int vehEstado, Vencimiento vencimiento);
    
    
}
