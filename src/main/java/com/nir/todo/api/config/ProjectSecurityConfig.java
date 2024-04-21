package com.nir.todo.api.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class ProjectSecurityConfig {
	
	
	
	//VERSION 1:
	//ENABLE ALL REQUESTS METHOD TO ALL END POINTS - DISABLE CSRF - DEFINE CORS FOR CLIENTS!
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		  http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) 
		 	
		 	.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		 	//MUST SET THIS!!! BASIC HTTP AUTHENTICATION
		 	.httpBasic(Customizer.withDefaults())
		 	
		 	.csrf( csrf -> csrf.disable()) 
		 	.headers(headers -> headers.frameOptions().disable())
		 	
		 	.cors().configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                config.setAllowedOrigins(Collections.singletonList("http://localhost:1234"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                //THE BROWSER WILL CACHE THIS CONFIGURATION FOR ONE HOUR ONLY! ALLOW THE COMMUNICATION FOR MAX 1 HOUR
                config.setMaxAge(3600L);
                return config;
            }
        }); 
		  
		  
		  return http.build();
	}
	

}
