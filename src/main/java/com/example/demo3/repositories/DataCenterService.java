package com.example.demo3.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
	public List<Map<String,String>> GetDataCentersLocation()
	{
		return dcs.GetDatacentersLocations();
		
	}
	public List<Map<String,Object>> GetnBServersPerDataCenter(){
		
		List<DataCenter>datacenters=dcs.findAll();
		List<Map<String, Object>> l = new ArrayList<>();
		for (DataCenter datacenter : datacenters) {
			Map<String, Object> map = new HashMap<>();
		    map.put("datacenter_name", datacenter.getName().toString()); 
		    map.put("number_of_servers", datacenter.getServers().size());
			l.add(map);
			} 
		return l;
	}
	public List<DataCenter> GetDataCenters()
	{
	   return dcs.findAll();	
	}
	public Map<String, Float>  GetAvailability(Long data_center_id) {
		Optional<DataCenter> d=dcs.findById(data_center_id);
		if (d.isPresent()) {
            DataCenter d1 = d.get();
           
         float availability=(d1.getServers().size()/d1.getCapacity())*100;
         float nbServers=100-availability;
         Map<String, Float> status = new HashMap<>();
         status.put("availability", availability);
         status.put("nbServers", nbServers);
         return status;
		}
		else {
			return null;
		}
	}
	

}
