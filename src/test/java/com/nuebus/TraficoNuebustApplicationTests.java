package com.nuebus;

import com.nuebus.dto.EnlaceLineasDTO;
import com.nuebus.dto.ViajeEspecialDTO;
import com.nuebus.model.Chofer;
import com.nuebus.model.ChoferIncidencia;
import com.nuebus.model.ChoferPK;
import com.nuebus.model.Incidencia;
import com.nuebus.model.LineaPK;
import com.nuebus.model.Servicio;
import com.nuebus.model.Vehiculo;
import com.nuebus.model.VehiculoIncidencia;
import com.nuebus.model.VehiculoPK;
import com.nuebus.model.ViajeEspecial;
import com.nuebus.repository.ChoferRepository;
import com.nuebus.repository.EnlaceLineasRepository;
import com.nuebus.repository.IncidenciaRepository;
import com.nuebus.repository.LineaRepository;
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
        
        @Autowired
        LineaRepository lineaRepository;
        
        @Autowired
        EnlaceLineasRepository  enlaceLineasRepository;
        

	@Test
        @Transactional
	public void contextLoads() {
            
            lineaRepository.findAllLineasByEmpresa("IMQ").forEach(System.out::println);
            
            EnlaceLineasDTO enlaceDTO = new EnlaceLineasDTO();
            
            LineaPK idaPk = new LineaPK( "IMQ", "800");
            LineaPK vtaPk = new LineaPK( "IMQ", "URU");
            
            enlaceDTO.setIdaPK(idaPk);
            enlaceDTO.setVueltaPK(vtaPk);
            
            lineaService.saveEnlaceLineas(enlaceDTO);
            
            enlaceLineasRepository.findAll().forEach( System.out::println );
            
            
	}
        
        
}
