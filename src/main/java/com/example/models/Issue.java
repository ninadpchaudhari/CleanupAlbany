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
	String type; // snow, garbage
	public Issue( long creator, long truckAssigned, String lat, String lng, String type) {
		super();
		
		this.creator = creator;
		this.truckAssigned = truckAssigned;
		this.lat = lat;
		this.lng = lng;
		this.type = type;
	}
	public Issue(long creatorId, String lat,String lng) {
		this.creator = creatorId;
		this.lat = lat;
		this.lng = lng;
	}
	public Issue() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
