package com.nuebus;



import com.nuebus.auth.service.UserService;
import com.nuebus.model.MailConfig;
import com.nuebus.repository.MailSenderRepository;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;




@RunWith(SpringRunner.class)
@SpringBootTest
public class TraficoNuebusApplicationTests {
    
    @Autowired
    UserService userService;
    
    @Autowired
    MailSenderRepository mailSenderRepository;
	
	
    @Test
    public void contextLoads() {
        
       

    }
	
	

}
