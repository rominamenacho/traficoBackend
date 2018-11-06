package com.nuebus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Sample application for demonstrating security with JWT Tokens
 * 
 * @author vladimir.stankovic
 *
 * Aug 3, 2016
 */
@SpringBootApplication
@EnableConfigurationProperties
public class TraficoNuebustApplication {
	public static void main(String[] args) {
		SpringApplication.run(TraficoNuebustApplication.class, args);
	}
        
        /*@Bean
        public WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurerAdapter() {
                        @Override
                        public void addCorsMappings(CorsRegistry registry) {
                        // we have to enable CORS to make requests from other domains work
                        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
                    }
                };
        }*/
        
        /*@Bean
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}*/
}
