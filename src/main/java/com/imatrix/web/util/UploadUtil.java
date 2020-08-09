package com.imatrix.web.util;

import java.io.File;
import java.io.IOException;


import com.imatrix.backend.services.amazon.AmazonClient;
import com.imatrix.backend.util.image.Image;
import com.imatrix.backend.util.resources.Constants;
import com.imatrix.backend.util.resources.Percentage;
import com.imatrix.backend.util.resources.StringManipulation;

/**
 * 
 * Singelton
 *
 */
public class UploadUtil {
	
	public static String compress(Image image, int percentage, AmazonClient client) throws IOException {
		
		int newPercentage = (int)Percentage.convertPercentageToK(image, percentage);
        String path = StringManipulation.finalize(image.getPath(), newPercentage);
        String fileName = StringManipulation.getFileName(path);
        
		if(image.compressImage(newPercentage) == Constants.SUCCESS) {
        	if(image.updateImage(path) == Constants.SUCCESS) {
        		client.uploadFileToBucket(Constants.BUCKET_NAME,fileName , new File(path));
        		return client.downloadFileFromBucket(Constants.BUCKET_NAME, fileName);
        	}
        }
		
		return null;
	}
	
	private UploadUtil() {}
}
