package com.example.demo3.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo3.models.DataCenter;

public interface DataCenterRepository extends JpaRepository<DataCenter, Long> {
	
	@Query(value="SELECT d.name as datacenter,d.Location as location  from DataCenter  d")
	List<Map<String,String>> GetDatacentersLocations();

}
