/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.repository;

import com.nuebus.model.Usuario;
import com.nuebus.model.UsuarioPK;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Valeria
 */
public interface UsuarioRepository extends JpaRepository< Usuario, UsuarioPK> {
    
    @Query("select u from Usuario u where u.login = ?1 and u.pass = ?2 ")
    Usuario findByLoginAndPass(String login, String pass);
    @Query("Select u from Usuario u where u.login = ?1 ")
    Optional<Usuario> findByLogin( String login );
    
}
