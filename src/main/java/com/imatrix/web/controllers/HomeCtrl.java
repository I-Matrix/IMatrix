package com.imatrix.web.controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.imatrix.backend.util.image.Image;
import com.imatrix.backend.util.resources.Constants;


@RestController
public class HomeCtrl {
	@GetMapping(path="/")  	
	public ModelAndView homePage() {
		ModelAndView mv = new ModelAndView("home");
		return mv;
	}
	
	@PostMapping(path="/upload")
	public ModelAndView homePage(@RequestParam("file") MultipartFile file) {
		ModelAndView mv = new ModelAndView("post");

        Path dirStore = Paths.get(Constants.UPLOAD_DIR, file.getOriginalFilename());
        try {
			Files.write(dirStore, file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("FAILED TO WRITE TO UPLOAD_DIR");
			e.printStackTrace();
		}
	    
        Image image = new Image(dirStore.toString());
        System.out.println("About to compress image");
		if(image.compressImage(100) == Constants.SUCCESS) {
			System.out.println("Sucessfully Compressed!");
			if(image.updateImage(image.getPath()) == Constants.SUCCESS) {
				System.out.println("Successfully Updated!");
			} else {
				System.out.println("Unsucessfull in Update");
			}
		}
		
		return mv;
	}
}
