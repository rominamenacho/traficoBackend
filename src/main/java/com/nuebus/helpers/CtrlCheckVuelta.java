package com.nuebus.helpers;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nuebus.dto.VueltaDiagDTO;

/**
 *
 * @author Valeria
 */

@Component
public class CtrlCheckVuelta { 
   
    @Autowired
    CheckVueltaChoferes checkVueltaChoferes;    
    @Autowired 
    CheckVueltaVehiculos checkVueltaVehiculos;       
    
    public CtrlCheckVuelta(  ){           
        super();             
    }   
    
    public Map< String, Set<String>> checkVuelta( VueltaDiagDTO vuelta ){              
        Map< String, Set<String>> errores = new HashMap<>();
        checkVueltaVehiculos.check( vuelta  , errores);               
        checkVueltaChoferes.check(  vuelta  , errores);                                                       
        return errores;       
    }    
    

    /*public static VueltaDiagDTO getServicioPrueba(){       
        
        ////////////////////////// IDA //////////////////
       
        ServicioDTO servDTO = new ServicioDTO();

        ServicioPK servPK = new ServicioPK();
        servPK.setSerEmpCodigo("IMQ");
        servPK.setSerLinCodigo("100");
        servPK.setSerFechaHora( Utilities.stringToDate( "30/11/2018 10:00", Utilities.FORMAT_DATEHOUR ));
        servPK.setSerRefuerzo(0);

        java.util.Date inicioS = Utilities.stringToDate( "30/11/2018 10:00", Utilities.FORMAT_DATEHOUR ); 
        java.util.Date finS = Utilities.stringToDate( "30/11/2018 23:00", Utilities.FORMAT_DATEHOUR ); 

        servDTO.setServicioPK( servPK );
        servDTO.setFechaHoraSalida( inicioS );
        servDTO.setFechaHoraLlegada(finS);

        VehiculoPK vehiPK = new VehiculoPK();
        vehiPK.setVehEmpCodigo("IMQ");
        vehiPK.setVehInterno( 2 );   
        
        VehiculoEtapaDTO unVehi = new VehiculoEtapaDTO();
        unVehi.setVehiculoPK( vehiPK );
        
        servDTO.getVehiculos().add( unVehi );

        vehiPK = new VehiculoPK();
        vehiPK.setVehEmpCodigo("IMQ");
        vehiPK.setVehInterno( 3 ); 

        unVehi = new VehiculoEtapaDTO();
        unVehi.setVehiculoPK( vehiPK );
        
        servDTO.getVehiculos().add( unVehi );   
        
        
        ChoferPK choferPK = new ChoferPK();
        choferPK.setEmpCodigo("IMQ");
        choferPK.setCodigo(8);
                
        ChoferEtapasDTO cho = new ChoferEtapasDTO();
        cho.setChoferPK(choferPK);
        
        servDTO.getChoferes().add( cho );
        
        
        VueltaDiagDTO vuelta = new VueltaDiagDTO();  
        vuelta.setEmpresa("IMQ");
        vuelta.setServIda( servDTO );
        
        
        /////////////////////////////// Vta ////////////////////////
        
        servDTO = new ServicioDTO();

        servPK = new ServicioPK();
        servPK.setSerEmpCodigo("IMQ");
        servPK.setSerLinCodigo("100");
        servPK.setSerFechaHora( Utilities.stringToDate( "03/12/2019 07:00", Utilities.FORMAT_DATEHOUR ));
        servPK.setSerRefuerzo(0);

        inicioS = Utilities.stringToDate( "03/12/2019 07:00", Utilities.FORMAT_DATEHOUR ); 
        finS = Utilities.stringToDate( "03/12/2019 09:00", Utilities.FORMAT_DATEHOUR ); 

        servDTO.setServicioPK( servPK );
        servDTO.setFechaHoraSalida( inicioS );
        servDTO.setFechaHoraLlegada(finS);

        vehiPK = new VehiculoPK();
        vehiPK.setVehEmpCodigo("IMQ");
        vehiPK.setVehInterno( 2 );   
        
        unVehi = new VehiculoEtapaDTO();
        unVehi.setVehiculoPK( vehiPK );
        
        servDTO.getVehiculos().add( unVehi );

        vehiPK = new VehiculoPK();
        vehiPK.setVehEmpCodigo("IMQ");
        vehiPK.setVehInterno( 3 ); 

        unVehi = new VehiculoEtapaDTO();
        unVehi.setVehiculoPK( vehiPK );
        
        servDTO.getVehiculos().add( unVehi );   
        
        
        choferPK = new ChoferPK();
        choferPK.setEmpCodigo("IMQ");
        choferPK.setCodigo(8);
                
        cho = new ChoferEtapasDTO();
        cho.setChoferPK(choferPK);
        
        servDTO.getChoferes().add( cho );
        
        vuelta.setServRet(servDTO );


        return vuelta;

    } */               
    
    
}
