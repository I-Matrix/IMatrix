package com.imatrix.web.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.imatrix.backend.services.amazon.AmazonClient;
import com.imatrix.backend.util.image.Image;
import com.imatrix.backend.util.resources.Constants;
import com.imatrix.web.util.UploadUtil;

@RestController
public class CustomCtrl {
	
	@Autowired
	private AmazonClient client;
	
	@GetMapping("/custom")
	public ModelAndView customGet() {
		return new ModelAndView("post").addObject("post", false).addObject("custom",true);
	}
	
	@PostMapping("/custom")
	public ModelAndView custom(@RequestParam("file") MultipartFile file, 
							   @RequestParam("kValue") int k) throws IOException {
		ModelAndView mv = new ModelAndView("post");
		Image image = null;

		
        Path dirStore = Paths.get(Constants.UPLOAD_DIR, file.getOriginalFilename()); // New PATH
        client.createBucket(Constants.BUCKET_NAME);
        
        try {
			Files.write(dirStore, file.getBytes()); // write to the new PATH
	        image = new Image(dirStore.toString());
         
		} catch (IOException e) {
			System.err.println("FAILED TO WRITE TO UPLOAD_DIR");
			e.printStackTrace();
		}

        String new_image_url	= UploadUtil.compress(image,-1.0,k,client);
        
        if(new_image_url==null) {
        	System.err.println("ERROR COMPRESSING!");
        	//int errr = 5/0; //Initate the error.html
        	return mv;
        }
        
        mv.addObject("new_image", new_image_url);
        
		
		return mv.addObject("post", true).addObject("custom",true);
	}
}
