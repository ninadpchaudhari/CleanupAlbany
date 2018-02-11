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
			System.out.println(descScore.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(descScore != null && descScore.containsKey("snow")) {
			if(Long.parseLong(descScore.get("snow")) >= 0.6) {
				System.out.println("Snow found");
				return true;
			}
			System.out.println("Snow found but low confidence");
			
		}
		
		
			System.out.println("Snow not found!");
			return false;
		
	}
}
