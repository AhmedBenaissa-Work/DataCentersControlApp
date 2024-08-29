package com.example.demo3.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="DataCenters")

public class DataCenter {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY) 
      private Long DataCenterId;
      public Long getDataCenterId() {
		return DataCenterId;
	}
	public void setDataCenterId(Long dataCenterId) {
		DataCenterId = dataCenterId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public List<Server> getServers() {
		return servers;
	}
	public void setServers(List<Server> servers) {
		this.servers = servers;
	}
	private String name;
      private int capacity;
      private String Location;
      public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	 @JsonIgnore
	  @OneToMany(mappedBy = "DataCenter", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
      private List<Server> servers = new ArrayList<>();
}
