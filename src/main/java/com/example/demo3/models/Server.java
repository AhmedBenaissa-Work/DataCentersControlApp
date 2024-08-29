package com.example.demo3.models;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Servers")
public class Server {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	    private Long id; 
	    private String name; 
	    private String OS ; 
	   

	    @ManyToOne()
	    @JoinColumn(name = "DataCenterId", referencedColumnName = "DatacenterId")
	    private DataCenter DataCenter;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getOS() {
			return OS;
		}
		public void setOS(String oS) {
			OS = oS;
		}
		public DataCenter getData_center() {
			return DataCenter;
		}
		public void setData_center(DataCenter data_center) {
			this.DataCenter = data_center;
		} 

}
