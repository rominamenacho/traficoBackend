package com.nuebus;


import com.nuebus.helpers.CtrlCheckVuelta;
import com.nuebus.repository.ChoferRepository;
import com.nuebus.repository.EnlaceLineasRepository;
import com.nuebus.repository.IncidenciaRepository;
import com.nuebus.repository.LineaRepository;
import com.nuebus.repository.ServicioRepository;
import com.nuebus.repository.VehiculoRepository;
import com.nuebus.repository.VueltaDiagRepository;
import com.nuebus.service.LineaService;
import com.nuebus.service.ServicioService;
import com.nuebus.service.VehiculoService;
import com.nuebus.service.ViajeEspecialService;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        
        @Autowired
        VehiculoService vehiculoService;
        
        @Autowired
        CtrlCheckVuelta ctrlCheckVuelta;
        
        @Autowired
        BCryptPasswordEncoder passwordEncoder;

	@Test
        @Transactional       
       
        
	public void contextLoads() {    
            
            /*Map< String, Set<String>> errores = new HashMap<>();
          
            java.util.Date inicio = Utilities.stringToDate( "30/11/2018", Utilities.FORMAT_DATE ); 
            java.util.Date fin = Utilities.stringToDate( "02/12/2018", Utilities.FORMAT_DATE );         
                       
            vehiculoService.
                    findVehiculosOcupacionByEmpresa("IMQ", inicio, fin)
                    .stream()
                    .forEach( System.out::println); */     
            Map< String, Set<String>> errores = new HashMap<>();
            
            
            errores = ctrlCheckVuelta.checkVuelta( CtrlCheckVuelta.getServicioPrueba());
            
            //System.out.println("encode " + passwordEncoder.encode("1234"));
            //System.out.println("result " + passwordEncoder.matches("1234", passwordEncoder.encode("1234") ) );
            
            
            errores.forEach( ( k,v ) -> {
                            System.out.println("K " + k);
                            System.out.println("V " + v);
                         } );   
       
        }          
        
        
}
