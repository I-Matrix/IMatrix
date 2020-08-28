package com.imatrix.web.util;

import java.io.File;
import java.io.IOException;


import com.imatrix.backend.services.amazon.AmazonClient;
import com.imatrix.backend.util.image.Image;
import com.imatrix.backend.util.resources.Constants;
import com.imatrix.backend.util.resources.Percentage;
import com.imatrix.backend.util.resources.StringManipulation;

/**
 * <h1>UploadUtil</h1>
 * <p>
 * 	Upload main utility
 * </p>
 *
 * <p>
 * 	Used for compressing which also
 * 	uses subclasses like Matrix
 * </p>
 *
 *
 * @author  Amanuel Bogale
 * @version 0.1
 * @since   2020-08-20
 * @see com.imatrix.backend.util.matrix.Matrix
 * @see Image
 */
public class UploadUtil {

	/**
	 * Compresses an image by either a percentage or
	 * k. if percentage is -1 then we are using k
	 * else using percentage otherwise.
	 * @param image Image to compress
	 * @param percentage percentage to compress by
	 * @param k k value to compress by
	 * @param client Amazon client for saving image in bucket
	 * @return a path to the compressed image
	 * @throws Exception
	 */
	public static String compress(Image image, double percentage, double k, AmazonClient client) throws Exception {
		int newK;
		if(percentage<0) newK = (int) k;
		else newK = (int)Percentage.convertFromDimensionToK(image.getWidth(),image.getHeight(),((double)(percentage/100)));
		
        String path = StringManipulation.finalize(image.getPath(), newK);
        String fileName = StringManipulation.getFileName(path);

		if(image.compressImage(newK) == Constants.SUCCESS) {
        	if(image.updateImage(path) == Constants.SUCCESS) {
        		client.uploadFileToBucket(Constants.BUCKET_NAME,fileName , new File(path));
        		return client.downloadFileFromBucket(Constants.BUCKET_NAME, fileName);
        	}
        }
		return null;
	}
	private UploadUtil() {}
}
