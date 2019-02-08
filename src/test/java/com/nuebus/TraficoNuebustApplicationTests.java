package com.nuebus;


import com.nuebus.helpers.CtrlCheckVuelta;
import com.nuebus.model.Vehiculo;
import com.nuebus.model.Vencimiento;
import com.nuebus.repository.ChoferRepository;
import com.nuebus.repository.EnlaceLineasRepository;
import com.nuebus.repository.IncidenciaRepository;
import com.nuebus.repository.LineaRepository;
import com.nuebus.repository.ServicioRepository;
import com.nuebus.repository.VehiculoRepository;
import com.nuebus.repository.VencimientoRepository;
import com.nuebus.repository.VueltaDiagRepository;
import com.nuebus.service.LineaService;
import com.nuebus.service.ServicioService;
import com.nuebus.service.VehiculoService;
import com.nuebus.service.ViajeEspecialService;
import com.nuebus.vencimientos.VencimientosVehiculo;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
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
        
        
        @Autowired
        VencimientoRepository vencimientoRepository;
        
      
        @Autowired
        VencimientosVehiculo vencimientosVehiculo;
        

        @Test
        @Transactional       
		public void contextLoads() {            
        	
        	System.out.println( "haber" );
        	
        	System.out.println( vencimientosVehiculo.calcularAllVencimientosVehiculos("IMQ", 0) );
	         
	       
	   }          
        
        
}
