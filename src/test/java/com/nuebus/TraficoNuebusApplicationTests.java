package com.nuebus;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.nuebus.auth.repository.UsuarioRepository;



@RunWith(SpringRunner.class)
@SpringBootTest
public class TraficoNuebusApplicationTests {
	
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Test
	public void contextLoads() {		
	
		CharSequence pass = "string";
		
		System.out.println( passwordEncoder.encode( pass ) );
		
		
		System.out.println( usuarioRepository.findByUsername("CVP") );
		
	}

}
