package com.nuebus.auth.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuebus.auth.repository.UsuarioRepository;
import com.nuebus.auth.service.GroupService;
import com.nuebus.auth.service.UserService;
import com.nuebus.dto.GroupDTO;
import com.nuebus.dto.UsuarioDTO;
import com.nuebus.mapper.UserMapper;
import com.nuebus.model.Group;
import com.nuebus.model.Usuario;



@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{
	
	@Autowired
	GroupService groupService;
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Autowired 
    UserMapper userMapper;

	@Override
	public Usuario findByUsername(String login) {
		return usuarioRepository.findByUsername(login);
	}

	@Override
	public Page<UsuarioDTO> findAllUsuarioByEmpresaConDatos( String empresa, Pageable pageable) {		
		return usuarioRepository.findAllByEmpresa( empresa, pageable).map( u -> userMapper.toDTO( u ) );							                   
	}

	@Override
	public Page<UsuarioDTO> findAllByEmpresaAndBusqueda(String empresa, String busqueda, Pageable pageable) {
		busqueda = busqueda.toUpperCase();
		return usuarioRepository.findAllByEmpresaAndBusqueda( empresa, busqueda, pageable).map( u -> userMapper.toDTO( u ) );
	}

	@Override
	@Transactional( readOnly=false)
	public void updateGrupoUsuario(String username, GroupDTO groupDTO) {

		Usuario usuario = findByUsername( username );		
		Group grupo = groupService.getById( groupDTO.getId() );
		
		usuario.setGroup( grupo );
		usuarioRepository.save(usuario );		
		
	}

	@Override
	public Page<UsuarioDTO> findAllUsuarioConDatos(Pageable pageable) {
		return usuarioRepository.findAll( pageable).map( u -> userMapper.toDTO( u ) );	
	}

	@Override
	public Page<UsuarioDTO> findAllByBusqueda(String busqueda, Pageable pageable) {
		busqueda = busqueda.toUpperCase();
		return usuarioRepository.findAllByBusqueda( busqueda, pageable).map( u -> userMapper.toDTO( u ) );	
	}
  
    
}
