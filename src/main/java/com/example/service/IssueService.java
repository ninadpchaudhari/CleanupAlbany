package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.models.Issue;
import com.example.repos.IssueRepository;

@Service
public class IssueService {

	@Autowired
	IssueRepository issueRepo;
	@Autowired
	StorageService storageService;
	@Autowired
	ImageClassificationService imgClassify;
	
	public Issue saveIssue(long creatorId, String lat,String lng, MultipartFile image) {
		
		Issue i = new Issue(creatorId,Long.parseLong("0"),lat,lng,"");
		issueRepo.save(i);
		if(storageService.saveImage(i.getId(), image)) {
			String type = imgClassify.getContentInImage(i.getId());
			
			i.setType(type);
			issueRepo.save(i);
			
			return i;
		}
		else {
			return null;
		}
	}
	
}
