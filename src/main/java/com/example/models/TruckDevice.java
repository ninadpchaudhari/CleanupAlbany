package com.example.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TruckDevice {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	long vehicleId;
	String type;
	String lat;
	String lng;
	String fcmtoken;
	int busy; // 0> free, 1>busy
	
	public TruckDevice( long vehicleId,String type, String lat, String lng,String fcmtoken,int busy) {
		super();
		this.vehicleId = vehicleId;
		this.type = type;
		this.lat = lat;
		this.lng = lng;
		this.fcmtoken = fcmtoken;
		this.busy = busy;
	}
	public TruckDevice( long vehicleId,String type, String lat, String lng) {
		super();
		this.vehicleId = vehicleId;
		this.type = type;
		this.lat = lat;
		this.lng = lng;
		
	}
	public TruckDevice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getFcmtoken() {
		return fcmtoken;
	}
	public void setFcmtoken(String fcmtoken) {
		this.fcmtoken = fcmtoken;
	}
	public int isBusy() {
		return busy;
	}
	public void setBusy(int busy) {
		this.busy = busy;
	}
	
}
