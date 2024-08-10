package com.example.demo3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/**").permitAll()
				.requestMatchers("/datacenters/**").authenticated()
				.requestMatchers("/servers/**").authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.permitAll().defaultSuccessUrl("/home")
			)
			.logout((logout) -> logout.permitAll()).csrf().disable();

		return http.build();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
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