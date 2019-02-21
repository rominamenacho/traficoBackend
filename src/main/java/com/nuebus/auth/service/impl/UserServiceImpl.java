package com.nuebus.auth.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuebus.auth.repository.UsuarioRepository;
import com.nuebus.auth.service.UserService;
import com.nuebus.dto.UserDTO;
import com.nuebus.mapper.UserMapper;
import com.nuebus.model.Usuario;



@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Autowired 
    UserMapper userMapper;

	@Override
	public Usuario findByUsername(String login) {
		return usuarioRepository.findByUsername(login);
	}

	@Override
	public Page<UserDTO> findAllUsuarioConDatos(Pageable pageable) {		
		return usuarioRepository.findAll(pageable).map( u -> userMapper.toDTO( u ) );							                   
	}
  
    
}
