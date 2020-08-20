package com.imatrix.backend.services.cloudinary;

import java.io.File;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.imatrix.backend.services.amazon.AmazonClient;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.imatrix.backend.util.image.Image;
import com.imatrix.backend.util.resources.Constants;
import com.imatrix.backend.util.resources.StringManipulation;

/**
 * <h1>Cloudinary Client Implementation</h1>
 * <p>
 * 	Implementation of this interface
 * </p>
 *
 * <p>
 * 	This is the implementation of the @interface CloudinaryClient
 *   @see CloudinaryClient for more details
 * </p>
 *
 *
 * @author  Amanuel Bogale
 * @version 0.1
 * @since   2020-08-20
 * @see     CloudinaryClient
 */
@Service
public class CloudinaryClientImpl implements CloudinaryClient{

	/*variable used to store configuration*/
	private Map config;
	private static Cloudinary cloudinary = null;

	/**
	 * @POST_CONSTRUCT
	 * Used to initalize Cloudinary account
	 * before using the database
	 */
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
	 * @param  cloudinaryFile The file to upload into
	 * 		 the database
	 * @return a cloudinary map which has been described
	 * 		   thoroughly in interface
	 * @see    CloudinaryClient
	 * @throws IOException
	 */
	public Map uploadFile(File cloudinaryFile) throws IOException{

		return cloudinary.uploader().upload(cloudinaryFile, ObjectUtils.emptyMap());
	}
}
