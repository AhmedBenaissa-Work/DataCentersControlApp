package com.example.demo3.repositories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo3.controllers.MvCController;
import com.example.demo3.models.DataCenter;
import com.example.demo3.models.Server;

@Service
public class ServerService {
	 private static final Logger LOG = LoggerFactory.getLogger(MvCController.class);
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
                int capacity=d1.getCapacity();
                
                List<Server>l=d1.getServers();
                if(l.size()<capacity) {
                l.add(server);
                d1.setServers(l);
                sr.save(server);
                dcr.save(d1);
                return true;
                }
                else {
                	
                	LOG.info("no more space in datacenter");
                	return false;
                }
            }
    		else {
    			LOG.info("no match for datacenter");
    			return false;
    		}
    		
    		}
    		catch(Exception e) {
    			return false;
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
    		}
    	
    }
}
