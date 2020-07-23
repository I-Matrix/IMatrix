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
import com.imatrix.backend.util.resources.Percentage;
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
        int quarter = (int)Percentage.convertPercentageToK(image, 25);
        int half = (int)Percentage.convertPercentageToK(image, 50);
        int quarterhalf = (int)Percentage.convertPercentageToK(image, 75);
		if(image.compressImage(100) == Constants.SUCCESS) {
			if(image.updateImage(StringManipulation.finalize(image.getPath(), quarter)) == Constants.SUCCESS) {
				if(image.updateImage(StringManipulation.finalize(image.getPath(), half)) == Constants.SUCCESS) {
					if(image.updateImage(StringManipulation.finalize(image.getPath(), quarterhalf)) == Constants.SUCCESS) {
						
						File cloudinaryFileQuarter = new File(StringManipulation.finalize(image.getPath(),quarter));
						File cloudinaryFileHalf = new File(StringManipulation.finalize(image.getPath(),half));
						File cloudinaryFileQuarterHalf = new File(StringManipulation.finalize(image.getPath(),quarterhalf));
						String quarter_image_url = (String)CloudinaryObj.uploadFile(cloudinaryFileQuarter,image).get("secure_url");
						mv.addObject("quarter_image",quarter_image_url);
						mv.addObject("half_image",CloudinaryObj.uploadFile(cloudinaryFileHalf,image).get("secure_url"));
						mv.addObject("quarter_and_half_image",CloudinaryObj.uploadFile(cloudinaryFileQuarterHalf,image).get("secure_url"));

					}
				}
			}
		}
		
		return mv;
	}
}
/**
				File cloudinaryFileQuarter = new File(StringManipulation.finalize(image.getPath(),));
				File cloudinaryFileHalf = new File(StringManipulation.finalize(image.getPath(),(int)Percentage.convertPercentageToK(image, 50)));
				File cloudinaryFileQuarterHalf = new File(StringManipulation.finalize(image.getPath(),(int)Percentage.convertPercentageToK(image, 75)));

				mv.addObject("quarter-image",CloudinaryObj.uploadFile(cloudinaryFileQuarter,image).get("secure_url"));
				mv.addObject("half-image",CloudinaryObj.uploadFile(cloudinaryFileHalf,image).get("secure_url"));
				mv.addObject("quarter-and-half-image",CloudinaryObj.uploadFile(cloudinaryFileQuarterHalf,image).get("secure_url"));
**/