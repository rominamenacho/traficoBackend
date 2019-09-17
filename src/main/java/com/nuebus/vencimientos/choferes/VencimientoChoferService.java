package com.nuebus.vencimientos.choferes;


import com.nuebus.dto.ChoferDTO;
import com.nuebus.dto.VencimientosChoferDTO;
import com.nuebus.model.Vencimiento;
import com.nuebus.service.VencimientoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class VencimientoChoferService {
    
    @Autowired 
    @Qualifier("CarnetNacional")        
    VencimientoChoferCarnetNacional carnetNacional;
    
    @Autowired
    @Qualifier("CarnetProvincial") 
    VencimientoChoferCarnetProvincial carnetProvincial;
    
    @Autowired
    VencimientoService  vencimientoService; 
    
    public  List<VencimientosChoferDTO> calcularVencimientos( String empresa, int estadoChofer ){
        
        List<VencimientosChoferDTO> vencimientosChofer = new ArrayList<>();
        
        List<Vencimiento> vencimientos = vencimientoService
                        .getVencimientosByEmpresaAndNombreEntidad( empresa, 
                                VencimientoChofer.ENTIDAD_CARNET, 
                                VencimientoChofer.VENCIMIENTO_ACTIVO );
        
        for( Vencimiento venc: vencimientos){
            if (  venc.getTipoVencimiento().getNombreCampo().equals( VencimientoChofer.FECHA_VENC_CAMPO_CARNET_NAC ) ) {
                List<ChoferDTO> choferes =  carnetNacional.calcularVencimiento(empresa, estadoChofer, venc);
                if( !choferes.isEmpty()){
                    vencimientosChofer.add( new VencimientosChoferDTO( venc, choferes));
                }
            } else if( venc.getTipoVencimiento().getNombreCampo().equals( VencimientoChofer.FECHA_VENC_CAMPO_CARNET_PROV ) ) {
                 List<ChoferDTO> choferes =   carnetProvincial.calcularVencimiento(empresa, estadoChofer, venc);
                 if( !choferes.isEmpty()){
                    vencimientosChofer.add( new VencimientosChoferDTO( venc, choferes));
                 }
            }            
        }
        
        return vencimientosChofer;
    }
    
}
