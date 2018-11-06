package com.nuebus.seguridad.security.endpoint;

import com.nuebus.model.Usuario;
import com.nuebus.seguridad.entity.Role;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nuebus.seguridad.entity.User;
import com.nuebus.seguridad.entity.UserRole;
import com.nuebus.seguridad.security.UserService;
import com.nuebus.seguridad.security.auth.jwt.extractor.TokenExtractor;
import com.nuebus.seguridad.security.auth.jwt.verifier.TokenVerifier;
import com.nuebus.seguridad.security.config.JwtSettings;
import com.nuebus.seguridad.security.config.WebSecurityConfig;
import com.nuebus.seguridad.security.exceptions.InvalidJwtToken;
import com.nuebus.seguridad.security.model.UserContext;
import com.nuebus.seguridad.security.model.token.JwtToken;
import com.nuebus.seguridad.security.model.token.JwtTokenFactory;
import com.nuebus.seguridad.security.model.token.RawAccessJwtToken;
import com.nuebus.seguridad.security.model.token.RefreshToken;
import com.nuebus.service.UsuarioService;
import java.util.ArrayList;
import javax.inject.Inject;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * RefreshTokenEndpoint
 * 
 * @author vladimir.stankovic
 *
 * Aug 17, 2016
 */

@RestController
public class RefreshTokenEndpoint {
    @Autowired private JwtTokenFactory tokenFactory;
    @Autowired private JwtSettings jwtSettings;
    @Autowired private UserService userService;
    @Autowired private TokenVerifier tokenVerifier;
    @Autowired @Qualifier("jwtHeaderTokenExtractor") private TokenExtractor tokenExtractor;
    
    @Inject
    UsuarioService usuarioService;
    
    
    /*@RequestMapping(value="/api/auth/token", method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody JwtToken refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String tokenPayload = tokenExtractor.extract(request.getHeader(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM));
        
        RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
        RefreshToken refreshToken = RefreshToken.create(rawToken, jwtSettings.getTokenSigningKey()).orElseThrow(() -> new InvalidJwtToken());

        String jti = refreshToken.getJti();
        if (!tokenVerifier.verify(jti)) {
            throw new InvalidJwtToken();
        }

        String subject = refreshToken.getSubject();
        User user = userService.getByUsername(subject).orElseThrow(() -> new UsernameNotFoundException("User not found: " + subject));

        if (user.getRoles() == null) throw new InsufficientAuthenticationException("User has no roles assigned");
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
                .collect(Collectors.toList());

        UserContext userContext = UserContext.create(user.getUsername(), authorities);

        return tokenFactory.createAccessJwtToken(userContext);
    }*/
    
    @RequestMapping(value="/api/auth/token", method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody JwtToken refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String tokenPayload = tokenExtractor.extract(request.getHeader(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM));
        
        RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
        RefreshToken refreshToken = RefreshToken.create(rawToken, jwtSettings.getTokenSigningKey()).orElseThrow(() -> new InvalidJwtToken());

        String jti = refreshToken.getJti();
        if (!tokenVerifier.verify(jti)) {
            throw new InvalidJwtToken();
        }
   
        String subject = refreshToken.getSubject();
        Usuario user = usuarioService.findByLogin(subject).orElseThrow(() -> new UsernameNotFoundException("User not found: " + subject));
        
        /*if (!encoder.matches(password, user.getPass())) {
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }*/    

        //if (user.getRoles() == null) throw new InsufficientAuthenticationException("User has no roles assigned");
        
        List<GrantedAuthority> authorities = getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
                .collect(Collectors.toList());
        
        UserContext userContext = UserContext.create(user.getLogin(), 
                                    user.getUsuarioPk().getEmpresa(),
                                    user.getAgencia(),
                                    authorities);
        

        return tokenFactory.createAccessJwtToken(userContext);
    }
    
    public List<UserRole> getRoles() {
        long idUser=11;
        List<UserRole> roles = new ArrayList<UserRole>(); 
        roles.add( new UserRole(idUser, Role.ADMIN) );
                
        return roles;
    }
    
}
