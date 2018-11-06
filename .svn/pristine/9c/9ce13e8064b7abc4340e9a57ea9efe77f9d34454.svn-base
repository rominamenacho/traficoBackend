package com.nuebus.seguridad.security.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;

public class UserContext {
    private final String username;
    private final String empresa;
    private final int agencia;
    private final List<GrantedAuthority> authorities;

    private UserContext(String username, String empresa, int agencia, List<GrantedAuthority> authorities) {
        this.username = username;
        this.empresa = empresa;
        this.agencia = agencia;
        this.authorities = authorities;
    }
    
    public static UserContext create(String username, String empresa, int agencia, List<GrantedAuthority> authorities) {
        if (StringUtils.isBlank(username)) throw new IllegalArgumentException("Username is blank: " + username);
        return new UserContext(username, empresa, agencia, authorities);
    }

    public String getUsername() {
        return username;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getEmpresa() {
        return empresa;
    }

    public int getAgencia() {
        return agencia;
    }   
    
}
