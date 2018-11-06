package com.nuebus;

import com.nuebus.dto.ViajeEspecialDTO;
import com.nuebus.model.Chofer;
import com.nuebus.model.ChoferIncidencia;
import com.nuebus.model.ChoferPK;
import com.nuebus.model.Incidencia;
import com.nuebus.model.Servicio;
import com.nuebus.model.Vehiculo;
import com.nuebus.model.VehiculoIncidencia;
import com.nuebus.model.VehiculoPK;
import com.nuebus.model.ViajeEspecial;
import com.nuebus.repository.ChoferRepository;
import com.nuebus.repository.IncidenciaRepository;
import com.nuebus.repository.VehiculoRepository;
import com.nuebus.service.LineaService;
import com.nuebus.service.ServicioService;
import com.nuebus.service.VehiculoService;
import com.nuebus.service.ViajeEspecialService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.inject.Inject;
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

	@Test
        @Transactional
	public void contextLoads() {
            
            /*
            
            long idIncidencia = 16100;
            
            Incidencia incid = incidenciaRepository.findOne(idIncidencia); 
            
            System.out.println(incid.toString());
            
            
            ChoferPK idChofer = new ChoferPK();
            idChofer.setCho_emp_codigo("IMQ");
            idChofer.setCho_codigo(1);
            
            
            Chofer unChofer =  choferRepository.findOne(idChofer);
            
            
            System.out.println( unChofer.toString());
            
            
            ChoferIncidencia chInc = new ChoferIncidencia();
            
            chInc.setChofer(unChofer);
            chInc.setIncidencia(incid);
            
            chInc.setInicio(new java.util.Date());
            chInc.setFin(new java.util.Date());
            
            
            unChofer.getChoferIncidencias().add(chInc);
            
            
            //incidenciaRepository.save(incid);            
            choferRepository.save( unChofer );    
            
            /////            
            
            Chofer unChoferDOS =  choferRepository.findOne(idChofer);            
            System.out.println("Numero de inci " +  unChoferDOS.getChoferIncidencias().size());           
            
            ////////////////////////////////////////////
            
            VehiculoPK veh = new VehiculoPK();
            veh.setVehEmpCodigo("IMQ");
            veh.setVehInterno(2);
            
            Vehiculo vehiculo = vehiculoRepository.findOne(veh);
            
            VehiculoIncidencia vehInc = new VehiculoIncidencia();
            vehInc.setVehiculo(vehiculo);
            vehInc.setIncidencia(incid);
            vehInc.setInicio( new java.util.Date());
            vehInc.setFin( new java.util.Date() );
            
            vehiculo.getVehiculoIncidencias().add( vehInc );
            
           
                     
              */
	}
        
        
}
