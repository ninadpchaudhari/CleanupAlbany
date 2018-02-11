package com.example.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.exmaple.misc.GoogleVision;

@Service
public class ImageClassificationService {

	public String getContentInImage(long issueId) {
		HashMap<String, Float> descScore = null;
		try {
			descScore = GoogleVision.getImageContent(issueId);
			System.out.println(descScore.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(descScore != null && descScore.containsKey("snow")) {
			if(descScore.get("snow") >= 0.6) {
				System.out.println("Snow found");
				return "snow";
			}
			System.out.println("Snow found but low confidence");
			
		}
		else if(descScore != null && descScore.containsKey("garbage")) {
			if(descScore.get("garbage") >=0.6) {
				System.out.println("Garbage Found");
				return "garbage";
			}
			System.out.println("Garbage found but low confidence");
			
		}
		
			System.out.println("Snow/Garbage not found!");
			return null;
		
	}
}
