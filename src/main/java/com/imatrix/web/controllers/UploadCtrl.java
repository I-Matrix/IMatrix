package com.imatrix.web.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.imatrix.backend.services.amazon.AmazonClient;
import com.imatrix.backend.services.cloudinary.CloudinaryClientImpl;
import com.imatrix.backend.util.image.Image;
import com.imatrix.backend.util.resources.Constants;
import com.imatrix.backend.util.resources.Percentage;
import com.imatrix.backend.util.resources.StringManipulation;
import static com.imatrix.backend.util.resources.StringManipulation.getFileName;

@RestController
public class UploadCtrl {
	
	@Autowired
	private AmazonClient client;
	
	@PostMapping(path="/upload")
	public ModelAndView homePage(@RequestParam("file") MultipartFile file) throws IOException {
		ModelAndView mv = new ModelAndView("post");
		Image image = null;
		Image image2 = null;
		Image image3 = null;
		
		FileInputStream inputStream = null;
        Path dirStore = Paths.get(Constants.UPLOAD_DIR, file.getOriginalFilename()); // New PATH
        client.createBucket(Constants.BUCKET_NAME);
        
        try {
			Files.write(dirStore, file.getBytes()); // write to the new PATH
			
	        image = new Image(dirStore.toString());
	        image2 = new Image(dirStore.toString());
	        image3 = new Image(dirStore.toString());
         
		} catch (IOException e) {
			System.err.println("FAILED TO WRITE TO UPLOAD_DIR");
			e.printStackTrace();
		}
        
        int quarter = (int)Percentage.convertPercentageToK(image, 25);
        String quarterPath = StringManipulation.finalize(image.getPath(), quarter);
        String quarterFileName = StringManipulation.getFileName(quarterPath);
        
        if(image.compressImage(quarter) == Constants.SUCCESS) {
        	if(image.updateImage(quarterPath) == Constants.SUCCESS) {
        		client.uploadFileToBucket(Constants.BUCKET_NAME,quarterFileName , new File(quarterPath));
        		mv.addObject("quarter_url", client.downloadFileFromBucket(Constants.BUCKET_NAME, quarterFileName));
        	}
        }
        
		return mv;
	}
}