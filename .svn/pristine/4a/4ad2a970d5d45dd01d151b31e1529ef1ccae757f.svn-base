/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.service;

import com.nuebus.dto.UsuarioDTO;
import com.nuebus.model.Usuario;
import com.nuebus.model.UsuarioPK;
import java.util.Optional;

/**
 *
 * @author usuario
 */
public interface UsuarioService {
    
     public UsuarioDTO getUsuario(UsuarioPK id);
     
     public void findByLoginPass(UsuarioDTO usuarioDTO);
      
     public Optional<Usuario> findByLogin(String login);
}
