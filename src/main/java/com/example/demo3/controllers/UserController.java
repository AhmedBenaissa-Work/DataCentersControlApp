package com.example.demo3.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo3.JwtUtil;
import com.example.demo3.models.User;
import com.example.demo3.repositories.ServerService;
import com.example.demo3.repositories.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
@RestController
@RequestMapping("/api/auth")
public class UserController {
	@Autowired
    UserService us;  
	@Autowired
	private  PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
    private JwtUtil jwtUtil;
	
	@PostMapping("/register")
	public String register(@RequestBody User user) {
	    user.setPassword(passwordEncoder.encode(user.getPassword()));
	    us.addUser(user);
	    return "User registered successfully";
	}
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/sign_in")
	
    public String login(@RequestBody User userLoginRequest) {
        System.out.println(userLoginRequest.getName());
        System.out.println(userLoginRequest.getPassword());
        try {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginRequest.getName(), userLoginRequest.getPassword())
       );
        
         String jwtToken = jwtUtil.generateToken(userLoginRequest.getName());
       // System.out.println(jwtToken);
         
        return jwtToken;
        }
        catch(Exception e) {
        	System.out.println(e);
        	return e.toString();
        }
        
        // If authentication is successful, generate the JWT token
       // String jwtToken = jwtUtil.generateToken(userLoginRequest.getName());
        //System.out.println(jwtToken);
        // Return the JWT token
        
    }
	
}
