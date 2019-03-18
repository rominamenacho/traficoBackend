/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.utilidades;

import org.springframework.security.core.Authentication;

import com.nuebus.model.Usuario;

/**
 *
 * @author Valeria
 */

public interface IAuthenticationFacade {

    Authentication getAuthentication();
    String getUserName();
    String getEmpresa();
    Usuario getUsuario();

}