package com.nuebus;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.nuebus.auth.repository.GroupRepository;
import com.nuebus.auth.repository.UsuarioRepository;
import com.nuebus.dto.GroupDTO;
import com.nuebus.mapper.GroupMapper;
import com.nuebus.model.Chofer;
import com.nuebus.model.Group;
import com.nuebus.model.Usuario;
import com.nuebus.repository.ChoferRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;



@RunWith(SpringRunner.class)
@SpringBootTest
public class TraficoNuebusApplicationTests {
	
	@Autowired 
	ChoferRepository choferRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	GroupRepository  groupRepository;
	
	
	@Autowired
    GroupMapper groupMapper;

	@Test
	public void contextLoads() {	
		
		Pageable pageable = PageRequest.of(0, 500);
		
		Page<Usuario> pageUsuario = usuarioRepository.findAllByEmpresaAndBusqueda("IMQ", "1", pageable);
		
		
		System.out.println( pageUsuario.getContent().stream().count() );
		
		pageUsuario.getContent().stream().forEach( u -> System.out.println( u.getNombre()  ));
		
		
	}	

}
