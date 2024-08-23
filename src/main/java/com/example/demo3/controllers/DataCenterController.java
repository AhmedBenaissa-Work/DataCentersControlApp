package com.example.demo3.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo3.models.DataCenter;
import com.example.demo3.repositories.DataCenterService;

@RestController
@RequestMapping("/api/datacenters")
public class DataCenterController {
	@Autowired
    DataCenterService dcs;
	 @PostMapping("/add")
	 @PreAuthorize("hasRole('USER')")
	  DataCenter newDataCenter(@RequestBody DataCenter newDataCenter) {
	    if(dcs.addDataCenter(newDataCenter)==true) {
	    	return newDataCenter;
	    }
	    else {
	    	return null;
	    }
	    
	  }
	 @GetMapping("/locations")
	 @PreAuthorize("hasRole('USER')")
	 List<Map<String,String>> locations() {
	    try {
	    	return dcs.GetDataCentersLocation();
	    }catch(Exception e) {
	    	return null;
	    }
	    
	  }
	 @GetMapping("/servers_number")
	 @PreAuthorize("hasRole('USER')")
	 List<Map<String,Object>> nbServers() {
	    try {
	    	return dcs.GetnBServersPerDataCenter();
	    }catch(Exception e) {
	    	return null;
	    }
	    
	  }
	 @GetMapping("/")
	 @PreAuthorize("hasRole('USER')")
	 List<DataCenter> Get() {
	    try {
	    	return dcs.GetDataCenters();
	    }catch(Exception e) {
	    	return null;
	    }
	    
	  }
	 @GetMapping("/availability/{data_center_id}")
	 @PreAuthorize("hasRole('USER')")
	 Map<String, Float> Availability(@PathVariable("data_center_id") Long datacenterid){
		 try {
		return dcs.GetAvailability(datacenterid);
		 }
		 catch(Exception e) {
		    	return null;
		    }
	 }
	 
}
