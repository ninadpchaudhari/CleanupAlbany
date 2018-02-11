package com.example.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.models.Issue;
import com.example.models.PersonalDevice;
import com.example.models.TruckDevice;
import com.example.repos.IssueRepository;
import com.example.repos.PersonalDeviceRepository;
import com.example.repos.TruckDeviceRepository;
import com.example.service.IssueService;
import com.example.service.NotificationService;
import com.example.service.RegistrationService;
import com.exmaple.misc.GoogleDistanceApi;


@RestController
public class IssueController {

	@Autowired
	RegistrationService regService;
	@Autowired
	IssueService issueService;
	@Autowired
	NotificationService notifications;

	@Autowired
	PersonalDeviceRepository personalRepo;
	@Autowired
	IssueRepository issueRepo;
	
	@Autowired
	TruckDeviceRepository truckRepo;
	
	@SuppressWarnings("unused")
	@PostMapping(value="/issue")
	@ResponseBody
	public Map<String,String> uploadImage(
			@RequestParam("file") MultipartFile image,
			@RequestParam String fcmtoken,
			@RequestParam String lat,
			@RequestParam String lng) {
		Map<String,String> myMap = new HashMap<String,String>();
		PersonalDevice pd = regService.findByFcmtoken(fcmtoken);
		
		if(pd == null) {
			//Device does not exist
			pd = regService.registerDevice(fcmtoken);
			System.out.println("Device does not exist, creating");
		}
		System.out.println("Request to add issue from : " + pd.getId());
		
		List<TruckDevice> trucks = truckRepo.findByBusyAndFcmtokenNotNull(0);
		if(trucks == null) {
			System.err.println("NO TRUCKS LOGGED IN!!");
			myMap.put("status", "error");
			return myMap;
		}
		Issue i = issueService.saveIssue(pd.getId(), lat, lng, image);
		System.out.println("i after saveImage" + i.getId());
		if(i == null) {
			//Not Snow/Grabage...
			myMap.put("status", "error");
			return myMap;
		}
		if(i.getType().equals("snow") || i.getType().equals("garbage")) {
			System.out.println("Before assignTruck");
			issueService.assignTruck(i.getId());
		}
		
		myMap.put("status", "ok");
		return myMap;
		
	}
	
	
	@PostMapping(value="/accept")
	public Map<String,String> acceptJob(@RequestParam String id,
			@RequestParam String issueId) {
		Map<String,String> myMap = new HashMap<String,String>();
		
		//send notification to user that it has been acceprd
		Issue i = issueRepo.findById(Long.parseLong(issueId));
		long creator = i.getCreator();
		
		PersonalDevice pd = personalRepo.findById(creator);
		
		notifications.sendThanks(pd.getFcmtoken());
		myMap.put("status", "ok");
		return myMap;
	}
	
	/*
	@PostMapping(value="/issueTest")
	@ResponseBody
	public Issue uploadTestIssue(
			@RequestParam("file") MultipartFile image) {
		String fcmtoken = "zzz";
		PersonalDevice pd = regService.findByFcmtoken(fcmtoken);
		if(pd == null) {
			//Device does not exist
			pd = regService.registerDevice(fcmtoken);
		}
		Issue i = issueService.saveIssue(pd.getId(), "nah", "nah", image);
		return i;
	}
	*/
}
