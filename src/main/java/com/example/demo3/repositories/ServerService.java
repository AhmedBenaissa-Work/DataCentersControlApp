package com.example.demo3.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo3.models.DataCenter;
import com.example.demo3.models.Server;

@Service
public class ServerService {
	@Autowired
	private DataCenterRepository dcr;
    @Autowired
	private ServerRepository sr;
    
    
    public List<Server> GetAllServers(){
    	
		return sr.findAll();
		
    	
    }
    
    public boolean addServer(Server server,Long data_center_id) {
    	try {
    		  
    		Optional<DataCenter> d=dcr.findById(data_center_id);
    		if (d.isPresent()) {
                DataCenter d1 = d.get();
                server.setData_center(d1);
                List<Server>l=d1.getServers();
                l.add(server);
                d1.setServers(l);
                sr.save(server);
                dcr.save(d1);
                return true;
            }
    		server.setData_center(null);
    		sr.save(server);
    		return true;
    		}
    		catch(Exception e) {
    			
    			return false;
    		  //  Block of code to handle errors
    		}
    	
    }
    public boolean updateServer(Server server,Long id) {
    	try {
    		Optional<Server> s = sr.findById(id);  
    		Server s1 = s.get();
    		s1.setId(id);
    		s1.setName(server.getName());
    		s1.setOS(server.getOS());
    		sr.save(s1);
    		return true;
    		
    		}
    		catch(Exception e) {
    			
    			return false;
    		  //  Block of code to handle errors
    		}
    	
    }
}
