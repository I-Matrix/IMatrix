package com.imatrix.web.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.imatrix.backend.services.cloudinary.CloudinaryObj;
import com.imatrix.backend.util.image.Image;
import com.imatrix.backend.util.resources.Constants;
import com.imatrix.backend.util.resources.StringManipulation;

@RestController
public class UploadCtrl {
	@PostMapping(path="/upload")
	public ModelAndView homePage(@RequestParam("file") MultipartFile file) {
		ModelAndView mv = new ModelAndView("post");
		Image image = null;
        Path dirStore = Paths.get(Constants.UPLOAD_DIR, file.getOriginalFilename());
        CloudinaryObj.startCloudinary();
        try {
			Files.write(dirStore, file.getBytes());
	        image = new Image(dirStore.toString());
		} catch (IOException e) {
			System.err.println("FAILED TO WRITE TO UPLOAD_DIR");
			e.printStackTrace();
		}
		if(image.compressImage(100) == Constants.SUCCESS) {
			if(image.updateImage(StringManipulation.finalize(image.getPath(), 300)) == Constants.SUCCESS) {
				File cloudinaryFile = new File(StringManipulation.finalize(image.getPath(), 300));
				mv.addObject("name",CloudinaryObj.uploadFile(cloudinaryFile,image).get("secure_url"));
			} else {
				System.out.println("Unsucessfull In Update");
			}
		} else {
			System.out.println("Unsucessfull In Compression");
		}
		
		return mv;
	}
}
