package com.nuebus;



import com.nuebus.auth.service.UserService;
import com.nuebus.model.Vehiculo;
import com.nuebus.repository.IncidenciaRepository;
import com.nuebus.repository.MailSenderRepository;
import com.nuebus.repository.VehiculoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;




@RunWith(SpringRunner.class)
@SpringBootTest
public class TraficoNuebusApplicationTests {
    
    @Autowired
    UserService userService;
    
    @Autowired
    MailSenderRepository mailSenderRepository;
	
    @Autowired
    VehiculoRepository vehiculoRepository;
    
    @Autowired
    IncidenciaRepository incidenciaRepository;
	
    @Test
    public void contextLoads() {
      
        incidenciaRepository.findByEmpresaAndTipo("IMQ", 1 ).forEach( System.out::println );
      

    }
	
	

}
