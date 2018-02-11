package com.example.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("storageService")
public class StorageService {

	public boolean saveImage(Long IssueId,MultipartFile image) {
		Path resourceDirectory = Paths.get("src/main/webapp/resources");
		try {
			File newFile = new File(resourceDirectory.toAbsolutePath().toString());
			newFile.mkdirs();
			newFile = new File(resourceDirectory.toAbsolutePath().toString() + '/' + image.getOriginalFilename());
			image.transferTo(newFile);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("File write fail");
		}
		
		//Files.write(resourceDirectory, file);
		//ImageIO.write(file, "jpg", resourceDirectory.toFile());
		return true;
	}
}
