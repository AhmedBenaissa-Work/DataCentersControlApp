package com.example.demo3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo3.models.Server;

import com.example.demo3.repositories.ServerService;
@RestController
@RequestMapping("/servers")
public class ServerController {
	@Autowired
    ServerService ss;
	 @PostMapping("/add")
	 @PreAuthorize("hasRole('USER')")
	  Server newDataCenter(@RequestBody Server newServer,@RequestParam("data_center_id") Long datacenterid) {
	    if(ss.addServer(newServer,datacenterid)==true) {
	    	return newServer;
	    }
	    else {
	    	return null;
	    }
}}
