package com.example.controllers;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectResult;

@Controller
public class AWSControllr {
	
	
@GetMapping(value="/upload")
public String renderUpload() {
	return "upload";
}
@PostMapping(value="/file")
public ModelAndView function1(@RequestParam("file") MultipartFile image) {
	BasicAWSCredentials credentials = new BasicAWSCredentials(
			  "AKIAI734MTY6LHYHFCNA", 
			  "hOS6HlfZccWHJr143GENVjMqq9F17tJNw/7w2Ah2"
			);
	
	
	
	try {
		File newFile = File.createTempFile(image.getOriginalFilename(),"");
		System.out.println("got file");
		image.transferTo(newFile);
		AmazonS3 s3client = AmazonS3ClientBuilder
				  .standard()
				  .withCredentials(new AWSStaticCredentialsProvider(credentials))
				  .withRegion(Regions.US_EAST_2)
				  .build();
		
		
		
		PutObjectResult lol = s3client.putObject(
				  "lolwaimagebucket", 
				  image.getOriginalFilename(), 
				  newFile
				);
		String srcUrl = "lolwaimagebucket" + ".s3.amazonaws.com" + "/" + image.getOriginalFilename();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		mv.addObject("srcUrl", srcUrl);
		return mv;
		
	} catch (IllegalStateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

		return new ModelAndView("index");

	}
}
/*
 * 
 * 
 * https://stackoverflow.com/questions/7933458/url-to-get-a-file-from-amazon-s3
 * http://www.baeldung.com/aws-s3-java
 * https://tiffanybbrown.com/2014/09/making-all-objects-in-an-s3-bucket-public-by-default/index.html
 */
