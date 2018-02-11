package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.PersonalDevice;
import com.example.repos.PersonalDeviceRepository;

@Service
public class RegistrationService {

	@Autowired
	PersonalDeviceRepository personalRepo;
	
	
	public PersonalDevice registerDevice(String fcmtoken) {
		PersonalDevice pd = new PersonalDevice(fcmtoken);
		return personalRepo.saveAndFlush(pd);
	}
	public PersonalDevice findByFcmtoken(String fcmtoken) {
		return personalRepo.findByFcmtoken(fcmtoken);
	}
	public PersonalDevice findById(String id) {
		return personalRepo.findById(Long.parseLong(id));
	}
}
