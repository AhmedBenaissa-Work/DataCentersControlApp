package com.example.demo3.models;
import java.util.ArrayList;
import java.util.List;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Users")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long UserID; 
    public Long getUserID() {
		return UserID;
	}
	public void setUserID(Long userID) {
		UserID = userID;
	}
	public List<DataCenter> getDatacenters() {
		return datacenters;
	}
	public void setDatacenters(List<DataCenter> datacenters) {
		this.datacenters = datacenters;
	}
	private String name; 
    private String email ; 
    private String password;
	public Long getId() {
		return UserID;
	}
	public void setId(Long id) {
		this.UserID = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@JsonIgnore
	@OneToMany(mappedBy = "User", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<DataCenter> datacenters = new ArrayList<>();
}
