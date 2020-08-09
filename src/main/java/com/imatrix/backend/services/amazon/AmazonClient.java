package com.imatrix.backend.services.amazon;

import java.io.File;
import java.io.IOException;

public interface AmazonClient {
	
	public void createBucket(String bucketName);
	
	public void printBuckets();
	
	public void uploadFileToBucket(String bucket, String fileName, File file);
	
	public void deleteFileToBucket(String fileName, String bucket);
	
	public String downloadFileFromBucket(String bucket, String fileName)throws IOException ;
	
}
