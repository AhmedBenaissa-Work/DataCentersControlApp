package com.example.demo3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo3.repositories.UserService;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	 private final UserService userDetailsService;
	 private final JwtUtil jwtTokenProvider;
	 public SecurityConfig(UserService userDetailsService,JwtUtil jwtTokenProvider ) 
	 {
	        this.userDetailsService = userDetailsService;
	        this.jwtTokenProvider=jwtTokenProvider;
	 }
	 List<String> authorities = new ArrayList<>();
	 
	 @Bean
	    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		 authorities.add("ADMIN");   
		 return http.getSharedObject(AuthenticationManagerBuilder.class)
	                .userDetailsService(userDetailsService)
	                .passwordEncoder(passwordEncoder())
	                .and()
	                .build();
	    }
	    
	 
	 @Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			
			http
				.authorizeHttpRequests((requests) -> requests
					.requestMatchers("/api/**").permitAll()
					.requestMatchers("/datacenters/**").authenticated()
					.requestMatchers("/servers/**").authenticated()
					.requestMatchers("/auth/**").authenticated()
				)
				.formLogin((form) -> form
					
					.permitAll().defaultSuccessUrl("/home")
				)
				.logout((logout) -> logout.permitAll()).csrf().disable();

			return http.build();
		}	
	 private Filter jwtAuthenticationFilter() {
	        return new OncePerRequestFilter() {
	            @Override
	            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	                    throws ServletException, IOException {

	                String token = extractTokenFromRequest(request);
	                if (!request.getRequestURI().startsWith("/api/auth/")) {
	                    // Proceed with the filter chain for other requests
	                    filterChain.doFilter(request, response);
	                    if (token != null && jwtTokenProvider.validateToken(token, jwtTokenProvider.extractUsername(token))) {
		                    // Set authentication in Spring Security context if the token is valid
		                    String username = jwtTokenProvider.extractUsername(token);
		                    UsernamePasswordAuthenticationToken authentication = 
		                        new UsernamePasswordAuthenticationToken(username, null, null);
		                    SecurityContextHolder.getContext().setAuthentication(authentication);
		                }

		                // Continue with the filter chain
		                filterChain.doFilter(request, response);

	                    return;
	                }
	                else {
                                 System.out.print(response);
	                	                
	                }
	            }
	            @Override
	            protected boolean shouldNotFilter(HttpServletRequest request) {
	                // Skip this filter for /api/auth/** endpoints
	                return request.getRequestURI().startsWith("/api/auth/");
	            }
	        };
	        
	    }

	    // Extract the token from the Authorization header
	    private String extractTokenFromRequest(HttpServletRequest request) {
	        String header = request.getHeader("Authorization");
	        return (header != null && header.startsWith("Bearer ")) ? header.substring(7) : null;
	    }
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	    }
	 @Bean
	   
	    public UserDetailsService userDetailsService() {
	        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	    
	        manager.createUser(User.withUsername("user")
	            .password(passwordEncoder().encode("password"))
	            .roles("USER")
	            .build());
	        return manager;
	    }
	 
}