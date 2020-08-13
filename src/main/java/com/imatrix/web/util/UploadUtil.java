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
	
	public static String compress(Image image, double percentage, double k, AmazonClient client) throws Exception {
		int newK;
		if(percentage<0) newK = (int) k;
		else newK = (int)Percentage.convertFromDimensionToK(image.getWidth(),image.getHeight(),((double)(percentage/100)));
		
        String path = StringManipulation.finalize(image.getPath(), newK);
        String fileName = StringManipulation.getFileName(path);
        
        System.out.println("Started Compression");
		if(image.compressImage(newK) == Constants.SUCCESS) {
			System.out.println("Finished Compression");
        	if(image.updateImage(path) == Constants.SUCCESS) {
        		System.out.println("Finished update");
        		client.uploadFileToBucket(Constants.BUCKET_NAME,fileName , new File(path));
        		return client.downloadFileFromBucket(Constants.BUCKET_NAME, fileName);
        	}
        }
		return null;
	}
	private UploadUtil() {}
}
