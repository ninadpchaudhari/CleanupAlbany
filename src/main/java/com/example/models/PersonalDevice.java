package com.example.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PersonalDevice {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	String fcmtoken;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFcmtoken() {
		return fcmtoken;
	}
	public void setFcmtoken(String fcmtoken) {
		this.fcmtoken = fcmtoken;
	}
	public PersonalDevice( String fcmtoken) {
		super();
		this.fcmtoken = fcmtoken;
	}
	public PersonalDevice() {
		super();
	}
	
	
}
