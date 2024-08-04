package com.example.demo3.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo3.models.DataCenter;


@Service
public class DataCenterService {
	@Autowired 
	private DataCenterRepository dcs;
	
	public boolean addDataCenter(DataCenter dc) {
		try {
			dcs.save(dc);
			return true;
		}catch(Exception e) {
		 return false;	
		}
		
	}
	

}
