package com.nuebus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.nuebus.auth.filter.JWTAuthenticationFilter;
import com.nuebus.auth.filter.JWTAuthorizationFilter;
import com.nuebus.auth.service.JWTService;
import com.nuebus.auth.service.JpaUserDetailsService;



@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	public static final String TOKEN_BASED_AUTH_ENTRY_POINT ="/api/**";
	public static final String ENTRY_POINT = "/api/login";
	public static final String ENTRY_POINT_UPLOADS_IMG = "/upload/**";
	public static final String ENTRY_POINT_VIEW_IMG = "/img/**";
	


	/*
	 * @Autowired
	private LoginSuccessHandler successHandler;
	*/
	
	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTService jwtService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers( ENTRY_POINT_UPLOADS_IMG ).permitAll()
		.antMatchers( ENTRY_POINT_VIEW_IMG ).permitAll()		
		.antMatchers( ENTRY_POINT ).permitAll()
			.and()		
		    	.authorizeRequests()
		        	.antMatchers(TOKEN_BASED_AUTH_ENTRY_POINT).authenticated()	
			.and()
				.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtService))
				.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtService))				
		.csrf().disable()
		.cors()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		

	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception
	{
		build.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);
	}
	
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
		
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration( TOKEN_BASED_AUTH_ENTRY_POINT , configuration );
        source.registerCorsConfiguration( ENTRY_POINT_UPLOADS_IMG , configuration );
        source.registerCorsConfiguration( ENTRY_POINT_VIEW_IMG , configuration );        
        return source;
    }
}
