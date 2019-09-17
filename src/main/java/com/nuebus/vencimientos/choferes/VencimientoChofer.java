
package com.nuebus.vencimientos.choferes;


import com.nuebus.dto.ChoferDTO;
import com.nuebus.model.Vencimiento;
import java.util.List;

/**
 *
 * @author Valeria
 */
public interface VencimientoChofer {
    
    public final static String ENTIDAD_CARNET = "Carnet";
    public final static String FECHA_VENC_CAMPO_CARNET_NAC = "fechaVencNac";
    public final static String FECHA_VENC_CAMPO_CARNET_PROV = "fechaVencProv";
    public final static boolean VENCIMIENTO_ACTIVO = true;	
    
    List<ChoferDTO> calcularVencimiento( String empresa, int estadoChofer, Vencimiento vencimiento );
    
}
