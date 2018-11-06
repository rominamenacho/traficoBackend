/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.utilidades;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Valeria
 */


@Component
public class AuthenticationFacade implements IAuthenticationFacade {

    public AuthenticationFacade() {
        super();
    }

    // API

    @Override
    public final Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}