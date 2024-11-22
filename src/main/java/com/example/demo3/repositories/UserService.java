package com.example.demo3.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo3.controllers.MvCController;
import com.example.demo3.models.DataCenter;
import com.example.demo3.models.User;

import io.jsonwebtoken.lang.Collections;

@Service

public class UserService  implements UserDetailsService {
	@Autowired
	private UserRepository urs;
	List<GrantedAuthority> authorities = new ArrayList<>(); 
	public boolean addUser(User u) {
		try {
			urs.save(u);
			return true;
		}catch(Exception e) {
		 return false;	
		}
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		    authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		    User user = urs.findUserByUsernameAndEmail(username)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

	        return new org.springframework.security.core.userdetails.User(
	                user.getName(),
	                user.getPassword(),authorities      
	        );
		// TODO Auto-generated method stub
		
	}

}
