package com.imatrix.backend.services.cloudinary;

import java.io.File;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.imatrix.backend.util.image.Image;
import com.imatrix.backend.util.resources.Constants;
import com.imatrix.backend.util.resources.StringManipulation;

@Service
public class CloudinaryClientImpl implements CloudinaryClient{
	private Map config;
	private static Cloudinary cloudinary = null;
	private static Boolean instantiated = false;
	
	@PostConstruct
	private void initalizeAccount() {
		Map config = ObjectUtils.asMap(
                "cloud_name", Constants.Cloud[0],
                "api_key"   , Constants.Cloud[1],
                "api_secret", Constants.Cloud[2]);
		cloudinary = new Cloudinary(config);
	}
	
	/**
	 * 
	 * @param cloudinaryFile
	 * @param image
	 * @return MAP_EXAMPLE : 
				   { "public_id":"tquyfignx5bxcbsupr6a",
				    "version":1375302801,
				    "signature":"52ecf23eeb987b3b5a72fa4ade51b1c7a1426a97",
				    "width":1920,
				    "height":1200,
				    "format":"jpg",
				    "resource_type":"image",
				    "created_at":"2017-07-31T20:33:21Z",
				    "bytes":737633,
				    "type":"upload",
				    "url":
				        "https://res.cloudinary.com/demo/image/upload/v1375302801/tquyfignx5bxcbsupr6a.jpg",
				    "secure_url":
				        "https://res.cloudinary.com/demo/image/upload/v1375302801/tquyfignx5bxcbsupr6a.jpg",
				    "etag":"1adf8d2ad3954f6270d69860cb126b24"
				   }
	 */
	public Map uploadFile(File cloudinaryFile, Image image) {
		Map uploadResult = null;
		try {
			uploadResult = cloudinary.uploader().upload(cloudinaryFile, ObjectUtils.emptyMap());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("ERROR UPLOADING IMAGE TO CLOUD!");
		}
		return uploadResult;
	}
}
