package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.models.Issue;
import com.example.models.TruckDevice;
import com.example.repos.IssueRepository;
import com.example.repos.TruckDeviceRepository;
import com.exmaple.misc.GoogleDistanceApi;
import com.google.maps.model.LatLng;

@Service
public class IssueService {

	@Autowired
	IssueRepository issueRepo;
	@Autowired
	StorageService storageService;
	@Autowired
	ImageClassificationService imgClassify;
	
	@Autowired
	TruckDeviceRepository truckRepo;
	
	public Issue saveIssue(long creatorId, String lat,String lng, MultipartFile image) {
		System.out.println("in issueSevice>saveIssue");
		Issue i = new Issue(creatorId,lat,lng);
		i = issueRepo.save(i);
		System.out.println("Issue saved 1 : id: "+ i.getId());
		if(storageService.saveImage(i.getId(), image)) {
			String type = imgClassify.getContentInImage(i.getId());
			
			i.setType(type);
			
			i = issueRepo.save(i);
			System.out.println("saved issue with type");
			return i;
		}
		else {
			System.out.println("storageServce save Image gave false! ");
			return null;
		}
	}
	
	public Issue assignTruck(long issueId) {
		Issue issue = issueRepo.findById(issueId);
		List<TruckDevice> trucks = truckRepo.findByTypeAndBusy(issue.getType(),false);
		LatLng[] origins = new LatLng[trucks.size()];
		for(int i=0;i<trucks.size();i++) {
			origins[i] = new LatLng(Double.parseDouble(trucks.get(i).getLat()),Double.parseDouble(trucks.get(i).getLng()));
		}
		
		LatLng destination = new LatLng(Double.parseDouble(issue.getLat()),Double.parseDouble(issue.getLng()));
		int truckId = GoogleDistanceApi.getTruckOverMinDistance(origins, destination);
		issue.setTruckAssigned((long)trucks.get(truckId).getId());
		TruckDevice td = trucks.get(truckId);
		td.setBusy(true);
		truckRepo.save(td);
		System.out.println("before notification");
		NotificationService.send(td.getFcmtoken(), issueId + "");
		return issueRepo.save(issue);
		
		
	}
	
}
