package com.example.controllers;

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
import com.example.service.IssueService;
import com.example.service.RegistrationService;
import com.exmaple.misc.GoogleDistanceApi;


@RestController
public class IssueController {

	@Autowired
	RegistrationService regService;
	@Autowired
	IssueService issueService;
	
	@PostMapping(value="/issue")
	@ResponseBody
	public Issue uploadImage(
			@RequestParam("file") MultipartFile image,
			@RequestParam String fcmtoken,
			@RequestParam String lat,
			@RequestParam String lng) {
		
		PersonalDevice pd = regService.findByFcmtoken(fcmtoken);
		if(pd == null) {
			//Device does not exist
			pd = regService.registerDevice(fcmtoken);
		}
		Issue i = issueService.saveIssue(pd.getId(), lat, lng, image);
		if(i == null) {
			//Not Snow/Grabage...
			return new Issue();
		}
		if(i.getType().equals("snow") || i.getType().equals("garbage")) {
			issueService.assignTruck(i.getId());
		}
		return i;
	}
	
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
}
