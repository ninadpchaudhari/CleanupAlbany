package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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


@RestController
public class IssueController {

	@Autowired
	RegistrationService regService;
	@Autowired
	IssueService issueService;
	
	@PostMapping(value="/issue")
	@ResponseBody
	public Issue uploadRestaurantMenu(
			@RequestParam("image") MultipartFile image,
			@RequestParam String fcmtoken,
			@RequestParam String lat,
			@RequestParam String lng) {
		
		PersonalDevice pd = regService.findByFcmtoken(fcmtoken);
		if(pd == null) {
			//Device does not exist
			pd = regService.registerDevice(fcmtoken);
		}
		Issue i = issueService.saveIssue(pd.getId(), lat, lng, image);
		return i;
	}
}
