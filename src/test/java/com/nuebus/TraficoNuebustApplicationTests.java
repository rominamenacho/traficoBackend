package com.nuebus;

import com.nuebus.builders.ChoferOcupacionBuilder;
import com.nuebus.builders.ChoferesByServicioBuilder;
import com.nuebus.dto.ChoferEtapasDTO;
import com.nuebus.model.ServicioPK;

import com.nuebus.repository.ChoferRepository;
import com.nuebus.repository.EnlaceLineasRepository;
import com.nuebus.repository.IncidenciaRepository;
import com.nuebus.repository.LineaRepository;
import com.nuebus.repository.ServicioRepository;
import com.nuebus.repository.VehiculoRepository;
import com.nuebus.repository.VueltaDiagRepository;
import com.nuebus.service.LineaService;
import com.nuebus.service.ServicioService;
import com.nuebus.service.ViajeEspecialService;
import com.nuebus.utilidades.Utilities;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.query.Param;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TraficoNuebustApplicationTests {
    
        @Autowired 
        IncidenciaRepository incidenciaRepository ;
        
        @Autowired
        ChoferRepository choferRepository;
        
        @Autowired
        VehiculoRepository vehiculoRepository;            
        
        @Autowired
        LineaService lineaService;
        
        @Autowired
        ServicioService servicioService;
        
        
        @Autowired
        ViajeEspecialService  viajeEspecialService ;
        
        @Autowired
        LineaRepository lineaRepository;
        
        @Autowired
        EnlaceLineasRepository  enlaceLineasRepository;
        
        @Autowired
        ServicioRepository servicioRepository;
        
        @Autowired
        VueltaDiagRepository vueltaDiagRepository;

	@Test
        @Transactional
	public void contextLoads() {                   
          
            java.util.Date inicio = Utilities.stringToDate( "29/11/2018", Utilities.FORMAT_DATE ); 
            java.util.Date fin = Utilities.stringToDate( "01/12/2018", Utilities.FORMAT_DATE ); 
            
            /*java.util.Date fechaServ = Utilities.stringToDate( "30/11/2018 10:00", Utilities.FORMAT_DATEHOUR );            
            int cantHorarios = servicioRepository.updateChofer1( (long)4, "IMQ", "100", fechaServ, 0, 1, 30);                
            int cantReg = servicioRepository.updateEmpresa( "ACON", "ACON NOMBRE");         
            System.out.println( " ya paso " + cantReg + " - " + cantHorarios );*/
            
            
            //servicioService.findServiciosConHorarisoByFecha( "IMQ", "100", inicio, fin).forEach( System.out::println);
            
            
            List< Object[] > choferes =  servicioRepository.findChoferesYVehiculosByServiciosAndLineaAndFechas( "IMQ", "100", inicio, fin);
            
            ChoferesByServicioBuilder builder = new ChoferesByServicioBuilder( choferes );
            
            Map< ServicioPK, Set<ChoferEtapasDTO> > maps = builder.build();
            
            /*for (Entry<ServicioPK, Set<ChoferEtapasDTO>> chofer : maps.entrySet()){
                    ServicioPK clave = chofer.getKey();
                    Set<ChoferEtapasDTO> valor = chofer.getValue();
                    System.out.println(clave + "  ->  " + valor.toString());
            } */          
            
            servicioService.findServiciosConHorarisoByFecha( "IMQ", "100", inicio, fin ).forEach(System.out :: println);
          
            
	}
        
        
}
