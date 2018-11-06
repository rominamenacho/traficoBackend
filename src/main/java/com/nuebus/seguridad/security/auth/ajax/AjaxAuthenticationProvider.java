package com.nuebus.seguridad.security.auth.ajax;

import com.nuebus.model.Usuario;
import com.nuebus.seguridad.entity.Role;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.nuebus.seguridad.entity.User;
import com.nuebus.seguridad.entity.UserRole;
import com.nuebus.seguridad.security.model.UserContext;
import com.nuebus.seguridad.user.service.DatabaseUserService;
import com.nuebus.service.UsuarioService;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 * 
 * @author vladimir.stankovic
 *
 * Aug 3, 2016
 */
@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {
    private final BCryptPasswordEncoder encoder;    
    //private final DatabaseUserService userService;
    
    
    @Inject
    UsuarioService usuarioService;
    

    /*@Autowired
    public AjaxAuthenticationProvider(final DatabaseUserService userService, final BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }*/
    
    @Autowired
    public AjaxAuthenticationProvider( final BCryptPasswordEncoder encoder) {        
        this.encoder = encoder;
    }


    /*@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        User user = userService.getByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        
        if (!encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }

        if (user.getRoles() == null) throw new InsufficientAuthenticationException("User has no roles assigned");
        
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
                .collect(Collectors.toList());
        
        UserContext userContext = UserContext.create(user.getUsername(), authorities);
        
        return new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());
    }*/
    
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        Usuario user = usuarioService.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        
        /*if (!encoder.matches(password, user.getPass())) {
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }*/
        
        if ( !user.getPass().equals(password.toUpperCase()) ) {
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }

        //if (user.getRoles() == null) throw new InsufficientAuthenticationException("User has no roles assigned");
        
         List<GrantedAuthority> authorities = getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
                .collect(Collectors.toList());
        
        UserContext userContext = UserContext.create(user.getLogin(), 
                                                        user.getUsuarioPk().getEmpresa(),
                                                        user.getAgencia(),
                                                        authorities);
        
        return new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
    
    
    public List<UserRole> getRoles() {
        long idUser=11;
        List<UserRole> roles = new ArrayList<UserRole>(); 
        roles.add( new UserRole(idUser, Role.ADMIN) );
                
        return roles;
    }
}
