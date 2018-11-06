/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.service;

import com.nuebus.dto.UsuarioDTO;
import com.nuebus.mapper.UsuarioMapper;
import com.nuebus.model.Usuario;
import com.nuebus.model.UsuarioPK;
import com.nuebus.repository.UsuarioRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author usuario
 */
@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService{  
    
    final static Logger LOG = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    UsuarioRepository usuarioRepository;    
 
    @Autowired
    UsuarioMapper usuarioMapper;
    

    @Override
    public UsuarioDTO getUsuario(UsuarioPK id) {
        
        Usuario usuario = usuarioRepository.getOne(id);
        if ( usuario == null ) {
            return null;
        } else {
            return usuarioMapper.toDTO(usuario);
        }        
    }

    @Override
    public void findByLoginPass(UsuarioDTO usuarioDTO) {
        
        System.out.println("usuarioDTO.getLogin()" + usuarioDTO.getLogin() + " , usuarioDTO.getPass()" +  usuarioDTO.getPass());
        
        Usuario usuario = usuarioRepository.findByLoginAndPass(usuarioDTO.getLogin().toUpperCase(), usuarioDTO.getPass().toUpperCase());       
        if( usuario != null){
            System.out.println(" usuario        " + usuario.getNombre());
        }else{
            System.out.println(" usuario nulo    ");
        }
    }

    @Override
    public Optional<Usuario> findByLogin(String login) {
        return  usuarioRepository.findByLogin( login.toUpperCase());         
    }  
    
   
}
