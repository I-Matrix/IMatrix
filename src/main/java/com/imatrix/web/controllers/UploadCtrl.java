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
import com.imatrix.web.util.UploadUtil;
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
        
        String quarter_url 		= UploadUtil.compress(image, 25,client);
        String half_url    		= UploadUtil.compress(image, 50,client);
        String quarter_half_url = UploadUtil.compress(image, 75,client);
        
        if(quarter_url==null || half_url==null || quarter_half_url==null) {
        	System.err.println("ERROR COMPRESSING!");
        	return mv;
        }
        
        mv.addObject("quarter_url"	   , quarter_url);
        mv.addObject("half_url"        , half_url);
        mv.addObject("quarter_half_url", quarter_half_url);
        
		return mv;
	}
}