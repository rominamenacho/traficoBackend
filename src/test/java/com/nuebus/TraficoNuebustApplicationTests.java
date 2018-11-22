package com.nuebus;

import com.nuebus.builders.ChoferOcupacionBuilder;
import com.nuebus.repository.ChoferRepository;
import com.nuebus.repository.EnlaceLineasRepository;
import com.nuebus.repository.IncidenciaRepository;
import com.nuebus.repository.LineaRepository;
import com.nuebus.repository.ServicioRepository;
import com.nuebus.repository.VehiculoRepository;
import com.nuebus.service.LineaService;
import com.nuebus.service.ServicioService;
import com.nuebus.service.ViajeEspecialService;
import com.nuebus.utilidades.Utilities;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

	@Test
        @Transactional
	public void contextLoads() {         
           
            //enlaceLineasRepository.findConfServicios( "IMQ", "200R" ).forEach( System.out::println  );
            
            java.util.Date inicio = Utilities.stringToDate( "21/11/2018", Utilities.FORMAT_DATE);
            
            java.util.Date fin =  Utilities.stringToDate( "22/11/2018", Utilities.FORMAT_DATE);
            
            /*servicioRepository.findServiciosByLineaAndFechas("IMQ", "100", inicio, fin).
                    forEach(  item -> 
                    {
                        System.out.println( new ServicioBuilder( item ).build().toString() );
                    });*/
            
            
            new ChoferOcupacionBuilder( choferRepository.ocupacionChoferes( "IMQ", inicio, fin ) )
                    .build().forEach( System.out::println);
            
            /*choferRepository.ocupacionChoferes( "IMQ", inicio, fin ).forEach( item ->{
                System.out.println( item[0] + " - " +  item[1] + " - " +  item[2] );
            });*/
            
            
            
            
	}
        
        
}
