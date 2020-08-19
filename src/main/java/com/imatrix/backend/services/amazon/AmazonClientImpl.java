package com.imatrix.backend.services.amazon;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.annotation.PostConstruct;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.mediastoredata.model.PutObjectResult;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.imatrix.backend.util.resources.Constants;

/**
* <h1>Amazon Client Implementation</h1>
* <p>
* 	Implementation of this interface
* </p>
* 
* <p>
* 	This is the implementation of the AmazonClient interface.
*   @see {@interface AmazonClient} for more details
* </p>
* 
* <p>
* 	<b>This isn't the full implementation</b> of all functions
*   the Amazon interface allows us to utilize but only
*   necessary.
* </p>
* 
* @author  Amanuel Bogale
* @version 0.1
* @since   2020-08-20
* @see     AmazonClient
*/
@Service
public class AmazonClientImpl implements AmazonClient {

	/**
	 * Main S3 Object
	 */
	private AmazonS3 s3client;
	
	/**
	 * All values driven from the property file
	 * <pre> 'res/application.yml' </p>
	 */
	@Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    
	@Value("${amazonProperties.bucketName}")
    private String bucketName;
    
	@Value("${amazonProperties.accessKey}")
    private String accessKey;
    
	@Value("${amazonProperties.secretKey}")
    private String secretKey;
	
    @PostConstruct
    private void initalizeAccount() {
		AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
		this.s3client = new AmazonS3Client(credentials);
    }
	    
  /**
	* Creates a bucket from the name
	* detail explanation in interface
	* @see AmazonClient.java
	*/
    public int createBucket(String bucketName) {
    	if(s3client.doesBucketExist(bucketName)) {
    		System.err.println("BUCKET ALREADY EXISTS");
    		return Constants.FAILURE;
    	}
    	s3client.createBucket(new CreateBucketRequest(bucketName));
    	return Constants.SUCCESS;
    }
    
   /**
    * Prints all the buckets in the
    * s3 database
    * @see AmazonClient.java for more info
    */
    public void printBuckets() {
    	List<Bucket> buckets = s3client.listBuckets();
    	for(Bucket bucket: buckets) {
    		System.out.println(bucket.getName());
    	}
    }
    
   /**
    * Uploads specified file to the
    * specified bucket
    * @see AmazonClient.java for more info
    */
    public int uploadFileToBucket(String bucket, String fileName, File file) {
    	com.amazonaws.services.s3.model.PutObjectResult res = 
    	this.s3client.putObject(new PutObjectRequest(
    							 bucket, 
    							 fileName,
    							 file
    						   ));
    	if(res == null) return Constants.FAILURE;
    	return Constants.SUCCESS;
    }
    
   /**
    * Deletes specified file to the
    * specified bucket
    * @see AmazonClient.java for more info
    */
    public void deleteFileToBucket(String bucket, String fileName) {
    	this.s3client.deleteObject(new DeleteObjectRequest(
    								bucket,
    								fileName
    							  ));
    }
    
   /**
    * Downloads specified file to the
    * specified bucket
    * @see AmazonClient.java for more info
    */
    public String downloadFileFromBucket(String bucket, String fileName) throws IOException {    	
    	GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucket, fileName);
    	URL url = s3client.generatePresignedUrl(request);
    	return url.toString();
    }
    
}
