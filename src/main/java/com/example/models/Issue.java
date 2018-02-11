package com.example.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Issue {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	long creator;
	long truckAssigned;
	String lat;
	String lng;
	
	public Issue(long creator, long truckAssigned, String lat, String lng) {
		super();
		this.creator = creator;
		this.truckAssigned = truckAssigned;
		this.lat = lat;
		this.lng = lng;
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCreator() {
		return creator;
	}
	public void setCreator(long creator) {
		this.creator = creator;
	}
	public long getTruckAssigned() {
		return truckAssigned;
	}
	public void setTruckAssigned(long truckAssigned) {
		this.truckAssigned = truckAssigned;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	
	
	
}
