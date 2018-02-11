package com.example.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.exmaple.misc.GoogleVision;

@Service
public class ImageClassificationService {

	public boolean isSnow(long issueId) {
		HashMap<String, String> descScore = null;
		try {
			descScore = GoogleVision.getImageContent(issueId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(descScore != null && descScore.containsKey("snow")) return true;
		else return false;
	}
}
